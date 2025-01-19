package com.tictactoe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LogicServlet", value = "/logic")
public class LogicServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HttpSession curentSession = req.getSession();

        Field field = extractField(curentSession);
        int index = getSelectedIndex(req);

        Sign currentSign = field.getField().get(index);

        if ( Sign.EMPTY != currentSign ) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req , resp);
            return;
        }

        field.getField().put(index , Sign.CROSS);

        if (checkWin(resp, curentSession, field)) {
            return;
        }

        int emptyFieldIndex = field.getEmptyFieldIndex();

        if ( emptyFieldIndex >= 0) {
            field.getField().put(emptyFieldIndex , Sign.NOUGHT);
            if (checkWin(resp, curentSession, field)) {
                return;
            }
        } else {
            curentSession.setAttribute("draw" , true);
            List<Sign> data = field.getFieldData();
            curentSession.setAttribute("data", data);
            resp.sendRedirect("/index.jsp");
            return;
        }

        List<Sign> data = field.getFieldData();
        curentSession.setAttribute("data", data);
        curentSession.setAttribute("field", field);
        resp.sendRedirect("/index.jsp");
    }

    private int getSelectedIndex(HttpServletRequest request) {
        String click = request.getParameter("click");
        boolean isNumeric = click.chars().allMatch(Character::isDigit);
        return isNumeric ? Integer.parseInt(click) : 0;
    }

    private Field extractField(HttpSession curentSession) {
        Object fieldAtribute = curentSession.getAttribute("field");
        if (Field.class != fieldAtribute.getClass()) {
            curentSession.invalidate();
            throw new RuntimeException("Session is broken, try one more time");
        }
        return (Field) fieldAtribute;
    }

    private boolean checkWin(HttpServletResponse response, HttpSession currentSession, Field field) throws IOException {
        Sign winner = field.checkWin();
        if (Sign.CROSS == winner || Sign.NOUGHT == winner) {
            currentSession.setAttribute("winner", winner);
            List<Sign> data = field.getFieldData();

            currentSession.setAttribute("data", data);
            response.sendRedirect("/index.jsp");
            return true;
        }
        return false;
    }
}

package action.actionImpl;

import action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.LOGIN_CUSTOMER;

public class LoginOnIndexPageAction implements Action {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN_CUSTOMER).forward(request, response);
    }
}

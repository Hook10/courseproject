package action.actionImpl;

import action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.CUSTOMER_REGISTRATION_PAGE;

public class RegistrationOnIndexPageAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(CUSTOMER_REGISTRATION_PAGE).forward(request, response);
    }
}

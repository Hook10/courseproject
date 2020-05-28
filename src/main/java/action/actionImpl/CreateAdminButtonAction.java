package action.actionImpl;

import action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.CREATE_ADMIN;
import static constants.ActionConstants.CUSTOMER_REGISTRATION_PAGE;

public class CreateAdminButtonAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(CREATE_ADMIN).forward(request, response);
    }
}

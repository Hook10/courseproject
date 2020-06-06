package action.actionImpl;

import action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.EDIT_CUSTOMER_DATA;

public class EditCustomerDataButtonAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id", request.getParameter("id"));
        request.getRequestDispatcher(EDIT_CUSTOMER_DATA)
                .forward(request, response);
    }
}

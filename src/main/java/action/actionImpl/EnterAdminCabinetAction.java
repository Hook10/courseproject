package action.actionImpl;

import action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.ADMIN_CABINET;
import static constants.ActionConstants.CUSTOMER_PERSONAL_ACCOUNT_PAGE;

public class EnterAdminCabinetAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(ADMIN_CABINET).forward(request, response);
    }
}

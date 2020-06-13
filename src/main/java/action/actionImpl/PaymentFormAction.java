package action.actionImpl;

import action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.EDIT_CUSTOMER_DATA;
import static constants.ActionConstants.PAY_INVOICE_FORM;

public class PaymentFormAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        request.getRequestDispatcher(PAY_INVOICE_FORM).forward(request, response);

    }
}

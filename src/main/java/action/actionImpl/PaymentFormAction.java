package action.actionImpl;

import action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.PAY_INVOICE_FORM;

public class PaymentFormAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentFormAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        request.getRequestDispatcher(PAY_INVOICE_FORM).forward(request, response);
    }
}

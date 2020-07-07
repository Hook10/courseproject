package action.actionImpl;

import action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.CUSTOMER_PERSONAL_ACCOUNT_PAGE;


public class EnterInCustomerPersonalAccountPageAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnterInCustomerPersonalAccountPageAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        request.getRequestDispatcher(CUSTOMER_PERSONAL_ACCOUNT_PAGE).forward(request, response);
    }
}

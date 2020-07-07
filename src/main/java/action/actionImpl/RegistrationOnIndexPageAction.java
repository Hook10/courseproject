package action.actionImpl;

import action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.CUSTOMER_REGISTRATION_PAGE;

public class RegistrationOnIndexPageAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationOnIndexPageAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        request.getRequestDispatcher(CUSTOMER_REGISTRATION_PAGE).forward(request, response);
    }
}

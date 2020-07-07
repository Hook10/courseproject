package action.actionImpl;

import action.Action;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static constants.ActionConstants.LOGIN_CUSTOMER;

public class LoginReferenceOnIndexPageAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginReferenceOnIndexPageAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

        request.getRequestDispatcher(LOGIN_CUSTOMER).forward(request, response);
    }
}

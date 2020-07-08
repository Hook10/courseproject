package action.actionImpl;

import action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.EDIT_CUSTOMER;
import static constants.ParamAndAttributeConstants.*;

public class EditCustomerButtonAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditCustomerButtonAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

        request.setAttribute(ID, request.getParameter(ID));
        request.setAttribute(FIRST_NAME, request.getParameter(FIRST_NAME));
        request.setAttribute(SURNAME, request.getParameter(SURNAME));
        request.setAttribute(EMAIL, request.getParameter(EMAIL));
        request.setAttribute(PASSWORD, request.getParameter(PASSWORD));
        request.setAttribute(CITY, request.getParameter(CITY));
        request.setAttribute(ADDRESS, request.getParameter(ADDRESS));
        request.setAttribute(IIN, request.getParameter(IIN));

        request.getRequestDispatcher(EDIT_CUSTOMER).forward(request, response);
    }
}
//
package action.actionImpl;

import action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.EDIT_CUSTOMER;
import static constants.ActionConstants.EDIT_CUSTOMER_DATA;

public class EditCustomerButtonAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditCustomerButtonAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("firstName", request.getParameter("firstName"));
        request.setAttribute("surname", request.getParameter("surname"));
        request.setAttribute("email", request.getParameter("email"));
        request.setAttribute("password", request.getParameter("password"));
        request.setAttribute("city", request.getParameter("city"));
        request.setAttribute("address", request.getParameter("address"));
        request.setAttribute("iin", request.getParameter("iin"));

        request.getRequestDispatcher(EDIT_CUSTOMER).forward(request, response);
    }
}

package action.actionImpl;

import action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.EDIT_CUSTOMER_DATA;


public class EditCustomerDataButtonAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditCustomerDataButtonAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("id_customer", request.getParameter("id_customer"));
        request.setAttribute("data", request.getParameter("data"));
        request.setAttribute("month", request.getParameter("month"));
        request.setAttribute("id_supplier", request.getParameter("id_supplier"));
        request.getRequestDispatcher(EDIT_CUSTOMER_DATA).forward(request, response);


    }
}

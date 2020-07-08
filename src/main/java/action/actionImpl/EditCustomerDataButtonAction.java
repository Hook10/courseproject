package action.actionImpl;

import action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.EDIT_CUSTOMER_DATA;
import static constants.ParamAndAttributeConstants.*;


public class EditCustomerDataButtonAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditCustomerDataButtonAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

        request.setAttribute(ID, request.getParameter(ID));
        request.setAttribute(ID_CUSTOMER, request.getParameter(ID_CUSTOMER));
        request.setAttribute(DATA, request.getParameter(DATA));
        request.setAttribute(MONTH, request.getParameter(MONTH));
        request.setAttribute(ID_SUPPLIER, request.getParameter(ID_SUPPLIER));
        request.getRequestDispatcher(EDIT_CUSTOMER_DATA).forward(request, response);


    }
}

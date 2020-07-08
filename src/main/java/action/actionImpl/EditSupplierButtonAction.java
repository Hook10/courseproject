package action.actionImpl;

import action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static constants.ActionConstants.EDIT_SUPPLIER;
import static constants.ParamAndAttributeConstants.COMPANY_NAME;
import static constants.ParamAndAttributeConstants.ID;
import static constants.ParamAndAttributeConstants.BIN;

public class EditSupplierButtonAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditSupplierButtonAction.class);



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

        request.setAttribute(ID, request.getParameter(ID));
        request.setAttribute(COMPANY_NAME, request.getParameter(COMPANY_NAME));
        request.setAttribute(BIN, request.getParameter(BIN));
        LOGGER.info(request.getParameter(COMPANY_NAME)+ " companyName EditSupplierButtonAction");

        request.getRequestDispatcher(EDIT_SUPPLIER).forward(request, response);
    }
}

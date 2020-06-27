package action.actionImpl;

import action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static constants.ActionConstants.EDIT_SUPPLIER;

public class EditSupplierButtonAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditSupplierButtonAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("companyName", request.getParameter("companyName"));
        request.setAttribute("bin", request.getParameter("bin"));
        System.out.println(request.getParameter("companyName")+ "companyName ");



        request.getRequestDispatcher(EDIT_SUPPLIER).forward(request, response);
    }
}

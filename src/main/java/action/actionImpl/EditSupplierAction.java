package action.actionImpl;

import action.Action;
import dao.impl.SupplierDaoImpl;
import entity.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import validation.IINValidation;
import validation.NameValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.ERROR_URL;
import static constants.ErrorConstants.*;
import static constants.ParamAndAttributeConstants.*;

public class EditSupplierAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditSupplierAction.class);
    NameValidation nameValidation = new NameValidation();
    IINValidation iinValidation = new IINValidation();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

        SupplierDaoImpl supplierDao = new SupplierDaoImpl();
        long id = Long.parseLong(String.valueOf(request.getParameter(ID)));
        String name = request.getParameter(COMPANY_NAME);
        String bin = request.getParameter(BIN);

        LOGGER.info(id + " id EditSupplierAction");
        LOGGER.info(name + " name EditSupplierAction");
        LOGGER.info(bin + " bin EditSupplierAction");

        if (name.isEmpty() || bin.isEmpty()) {
            request.setAttribute(ERROR_MESSAGE, EMPTY_FIELDS);
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
        }

        if (nameValidation.isNotValidUserName(name) || iinValidation.isNotValidIIN(bin)) {
            request.setAttribute(ERROR_MESSAGE, INCORRECT_INPUT);
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
        }

        Supplier supplier = new Supplier();
        supplier.setCompanyName(name);
        supplier.setBin(bin);

        supplierDao.update(id, supplier);

        new ShowAllSuppliersAction().execute(request, response);
    }
}

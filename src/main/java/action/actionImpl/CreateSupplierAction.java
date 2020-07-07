package action.actionImpl;

import action.Action;
import dao.impl.SupplierDaoImpl;
import entity.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import validation.IINValidation;
import validation.NameValidation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.ADMIN_CABINET;
import static constants.ActionConstants.ERROR_URL;
import static constants.ErrorConstants.*;

public class CreateSupplierAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateSupplierAction.class);
    private NameValidation nameValidation = new NameValidation();
    private IINValidation iinValidation = new IINValidation();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        SupplierDaoImpl supplierDao = new SupplierDaoImpl();

        String name = request.getParameter("name");
        String bin = request.getParameter("bin");

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


        supplierDao.add(supplier);

        RequestDispatcher dispatcher = request.getRequestDispatcher(ADMIN_CABINET);
        dispatcher.forward(request, response);

    }
}


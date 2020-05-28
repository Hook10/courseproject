package action.actionImpl;

import action.Action;
import dao.daoimpl.SupplierDaoImpl;
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
import java.sql.SQLException;

import static constants.ActionConstants.ADMIN_CABINET;
import static constants.ActionConstants.ERROR_URL;

public class CreateSupplierAction implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateSupplierAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        SupplierDaoImpl supplierDao = new SupplierDaoImpl();

        String name = request.getParameter("name");
        String bin = request.getParameter("bin");

        if(name.isEmpty() || bin.isEmpty()) {
            request.setAttribute("message", "empty fields");
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
        }

        if(!new NameValidation().isValidUserName(name) || !new IINValidation().isValidIIN(bin)) {
            request.setAttribute("message", "Incorrect input");
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
        }

        Supplier supplier = new Supplier();
        supplier.setCompanyName(name);
        supplier.setBin(bin);

        try {
            supplierDao.add(supplier);
        } catch (SQLException e ) {
            LOGGER.info("Supplier creating sql error");
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(ADMIN_CABINET);
        dispatcher.forward(request, response);

    }
}

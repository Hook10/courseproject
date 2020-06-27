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
import java.sql.SQLException;

import static constants.ActionConstants.ERROR_URL;

public class EditSupplierAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditSupplierAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

        SupplierDaoImpl supplierDao = new SupplierDaoImpl();
        long id = Long.parseLong(String.valueOf(request.getParameter("id")));
        String name = request.getParameter("companyName");
        String bin = request.getParameter("bin");
        System.out.println("this is id" + id);
        System.out.println("this is companyName" + name);
        System.out.println("this is bin" + bin);

        if (name.isEmpty() || bin.isEmpty()) {
            request.setAttribute("message", "empty fields");
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
        }

        if (!new NameValidation().isValidUserName(name) || !new IINValidation().isValidIIN(bin)) {
            request.setAttribute("message", "Incorrect input");
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
        }

        Supplier supplier = new Supplier();
        supplier.setCompanyName(name);
        supplier.setBin(bin);

        try {
            supplierDao.update(id,supplier);
        } catch(SQLException e){
            e.printStackTrace();
        }
        new ShowAllSuppliersAction().execute(request, response);
    }
}

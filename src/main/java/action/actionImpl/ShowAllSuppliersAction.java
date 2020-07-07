package action.actionImpl;

import action.Action;
import dao.impl.CustomerDaoImpl;
import dao.impl.SupplierDaoImpl;
import entity.Customer;
import entity.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import static constants.ActionConstants.SHOW_SUPPLIERS;

public class ShowAllSuppliersAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowAllSuppliersAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

        SupplierDaoImpl supplierDao = new SupplierDaoImpl();
        List<Supplier> supplierList = null;


        supplierList = supplierDao.getAll();

        request.setAttribute("supplierList", supplierList);
        request.getRequestDispatcher(SHOW_SUPPLIERS).forward(request, response);
    }
}

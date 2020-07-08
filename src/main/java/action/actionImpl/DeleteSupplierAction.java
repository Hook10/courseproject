package action.actionImpl;

import action.Action;
import dao.impl.SupplierDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static constants.ParamAndAttributeConstants.*;

public class DeleteSupplierAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteSupplierAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

        long id = Long.parseLong(request.getParameter(ID));
        SupplierDaoImpl supplierDao = new SupplierDaoImpl();

        LOGGER.info(id + " id supplier DeleteSupplierAction");

        supplierDao.removeOneById(id);

        new ShowAllSuppliersAction().execute(request, response);

    }
}

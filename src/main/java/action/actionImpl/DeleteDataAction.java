package action.actionImpl;

import action.Action;
import dao.impl.DataDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static action.actionImpl.ShowGasAction.GAS_SUPPLIER;

public class DeleteDataAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteDataAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        long id = Long.parseLong(request.getParameter("id"));
        int id_supplier = Integer.parseInt(request.getParameter("id_supplier"));
        DataDaoImpl dataDao = new DataDaoImpl();
        dataDao.removeOneById(id);

        EditCustomerDataAction.supplierForward(request, response, id_supplier, GAS_SUPPLIER);
    }
}

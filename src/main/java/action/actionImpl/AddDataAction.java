package action.actionImpl;

import action.Action;
import dao.impl.DataDaoImpl;
import entity.Customer;
import entity.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static action.actionImpl.EditCustomerDataAction.supplierForward;
import static constants.ActionConstants.*;
import static constants.ErrorConstants.*;
import static constants.ParamAndAttributeConstants.*;

public class AddDataAction implements Action {
    public static final int GAS_SUPPLIER = 1;
    private static final Logger LOGGER = LoggerFactory.getLogger(AddDataAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        Customer customer;

        if (request.getSession().getAttribute(CUSTOMER) instanceof Customer) {
            customer = (Customer) request.getSession().getAttribute(CUSTOMER);
        } else {
            request.setAttribute(ERROR_MESSAGE, NEED_REGISTRATION);
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }
        String month = request.getParameter(MONTH);
        long data = Long.parseLong(request.getParameter(GAS_DATA));
        long idCustomer = customer.getId();
        int idSupplier = Integer.parseInt(request.getParameter(ID_SUPPLIER));


        Data dataFromCustomer = new Data();
        DataDaoImpl dataDao = new DataDaoImpl();

        dataFromCustomer.setMonth(month);
        dataFromCustomer.setData(data);
        dataFromCustomer.setIdCustomer(idCustomer);
        dataFromCustomer.setIdSupplier(idSupplier);

        dataDao.add(dataFromCustomer);

        supplierForward(request, response, idSupplier, GAS_SUPPLIER);

    }
}

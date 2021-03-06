package action.actionImpl;

import action.Action;
import dao.impl.CustomerDaoImpl;
import entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static constants.ActionConstants.SHOW_CUSTOMERS;
import static constants.ParamAndAttributeConstants.CUSTOMER_LIST;

public class ShowAllCustomersAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowAllCustomersAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        List<Customer> customerList = customerDao.getAll();

        request.setAttribute(CUSTOMER_LIST, customerList);
        request.getRequestDispatcher(SHOW_CUSTOMERS).forward(request, response);
    }
}

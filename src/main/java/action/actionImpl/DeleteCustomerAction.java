package action.actionImpl;

import action.Action;
import dao.impl.CustomerDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCustomerAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteCustomerAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        long id = Long.parseLong(request.getParameter("id"));
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        LOGGER.info(id + " id method delete");
        customerDao.removeOneById(id);

        new ShowAllCustomersAction().execute(request, response);
    }
}

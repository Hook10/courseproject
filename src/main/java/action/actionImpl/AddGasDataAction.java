package action.actionImpl;

import action.Action;
import dao.daoimpl.DataDaoImpl;
import entity.Customer;
import entity.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static constants.ActionConstants.*;

public class AddGasDataAction implements Action {
    public static final int  GAS_SUPPLIER = 1;
    private static final Logger LOGGER = LoggerFactory.getLogger(AddGasDataButtonAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Customer customer= null;

        if(request.getSession().getAttribute("customer") instanceof Customer) {
            customer = (Customer) request.getSession().getAttribute("customer");
        } else {
            request.setAttribute("message", "Необходимо зарегистрироваться");
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }
        String month = request.getParameter("month");
        long data = Long.parseLong(request.getParameter("Gas_data"));
        long id_customer = customer.getId();
        int id_supplier = GAS_SUPPLIER;

        Data dataFromCustomer = new Data();
        DataDaoImpl dataDao = new DataDaoImpl();

        dataFromCustomer.setMonth(month);
        dataFromCustomer.setData(data);
        dataFromCustomer.setIdCustomer(id_customer);
        dataFromCustomer.setIdSupplier(id_supplier);
        try{
            dataDao.add(dataFromCustomer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//
        RequestDispatcher dispatcher =  request.getRequestDispatcher(FORWARD_TO_SHOW_DATA);
        dispatcher.forward(request, response);



 /*       LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        DataDaoImpl dataDao = new DataDaoImpl();

        String month = request.getParameter("month");
        Long data = Long.parseLong(request.getParameter("Gas_data"));
        Long idCustomer = Long.parseLong(request.getParameter("id_customer"));
        int idSupplier = Integer.parseInt(request.getParameter("id_supplier"));

        if(month.isEmpty() || request.getParameter("Gas_data").equals("") ||
        request.getParameter("id_customer").equals("") ||
        request.getParameter("id_supplier").equals("")) {
            request.setAttribute("message", "empty fields");
            request.getRequestDispatcher(ERROR_URL).forward(request,response);
        }

        Data data1 = new Data();
        data1.setMonth(month);
        data1.setData(data);
        data1.setIdCustomer(idCustomer);
        data1.setIdSupplier(idSupplier);

        try{
            dataDao.add(data1);
        } catch (SQLException e ) {
            LOGGER.info("Data creating sql error");
            e.printStackTrace();
        }
        RequestDispatcher dispatcher =  request.getRequestDispatcher(SHOW_DATA_PERSON_LIST);
        dispatcher.forward(request, response);

  */
    }
}

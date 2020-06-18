package action.actionImpl;

import action.Action;
import dao.daoimpl.CustomerDaoImpl;
import entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static constants.ActionConstants.SHOW_CUSTOMERS;

public class ShowAllCustomersAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        List<Customer> customerList=null;

        try {
            customerList= customerDao.getAll();
        } catch (SQLException e){
            e.printStackTrace();
        }
        request.setAttribute("customerList", customerList);
        request.getRequestDispatcher(SHOW_CUSTOMERS).forward(request, response);
    }
}

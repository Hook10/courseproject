package action.actionImpl;

import action.Action;
import dao.daoimpl.CustomerDaoImpl;
import entity.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static constants.ActionConstants.CUSTOMER_REGISTRATION_PAGE;
import static constants.ActionConstants.CUSTOMER_REGISTRATION_STATUS_PAGE;

public class RegisterCustomerAction implements Action {
    private CustomerDaoImpl customerDao = new CustomerDaoImpl();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(CUSTOMER_REGISTRATION_PAGE);
        dispatcher.forward(request, response);


        String firstName = request.getParameter("firstName");
        String surName = request.getParameter("surname");
        String login = request.getParameter("email");
        String password = request.getParameter("password");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String iin = request.getParameter("iin");

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setSurname(surName);
        customer.setEmail(login);
        customer.setPassword(password);
        customer.setCity(city);
        customer.setAddress(address);
        customer.setIin(iin);

        try {
            customerDao.add(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*RequestDispatcher*/ dispatcher  = request.getRequestDispatcher(CUSTOMER_REGISTRATION_STATUS_PAGE);
        dispatcher.forward(request, response);
    }
}
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String firstName = request.getParameter("firstName");
//        String surName = request.getParameter("surname");
//        String login = request.getParameter("email");
//        String password = request.getParameter("password");
//        String city = request.getParameter("city");
//        String address = request.getParameter("address");
//        String iin = request.getParameter("iin");
//
//        Customer customer = new Customer();
//        customer.setFirstName(firstName);
//        customer.setSurname(surName);
//        customer.setEmail(login);
//        customer.setPassword(password);
//        customer.setCity(city);
//        customer.setAddress(address);
//        customer.setIin(iin);
//
//        try {
//            customerDao.add(customer);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        RequestDispatcher dispatcher  = request.getRequestDispatcher("/WEB-INF/view/customerDetails.jsp");
//        dispatcher.forward(request, response);
//    }

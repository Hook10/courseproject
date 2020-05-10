package servlets;

import dao.daoimpl.CustomerDaoImpl;
import dao.services.LoginDao;
import entity.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class CustomerServlet extends HttpServlet {
    private CustomerDaoImpl customerDao = new CustomerDaoImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/customerregister.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        RequestDispatcher dispatcher  = request.getRequestDispatcher("/WEB-INF/view/customerdetails.jsp");
        dispatcher.forward(request, response);
    }
}



package action.actionImpl;

import Encoder.HashFunction;
import action.Action;
import dao.daoimpl.CustomerDaoImpl;
import entity.Customer;
import validation.EmailValidation;
import validation.IINValidation;
import validation.NameValidation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static constants.ActionConstants.*;

public class RegisterCustomerAction implements Action {
    private HashFunction hashFunction = new HashFunction();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerDaoImpl customerDao = new CustomerDaoImpl();

        String firstName = request.getParameter("firstName");
        String surName = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String iin = request.getParameter("iin");

        if (firstName.isEmpty() || surName.isEmpty() ||
                email.isEmpty() || password.isEmpty() ||
                city.isEmpty() || address.isEmpty() ||
                iin.isEmpty()) {
            request.setAttribute("message", "empty fields");
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }

        if (!new NameValidation().isValidUserName(firstName) || !new NameValidation().isValidUserName(surName) ||
                !new EmailValidation().isValidEmail(email) || !new IINValidation().isValidIIN(iin)) {
            request.setAttribute("message", "Incorrect  input");
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }

      password = hashFunction.getHashFunction(password);

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setSurname(surName);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setCity(city);
        customer.setAddress(address);
        customer.setIin(iin);

        try {
            customerDao.add(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_CUSTOMER);
        dispatcher.forward(request, response);
    }
}


package action.actionImpl;

import encoder.HashFunction;
import action.Action;
import dao.impl.CustomerDaoImpl;
import entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import validation.EmailValidation;
import validation.IINValidation;
import validation.NameValidation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.*;
import static constants.ErrorConstants.*;
import static constants.ParamAndAttributeConstants.*;

public class RegisterCustomerAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterCustomerAction.class);
    private final HashFunction hashFunction = new HashFunction();
    NameValidation nameValidation = new NameValidation();
    EmailValidation emailValidation = new EmailValidation();
    IINValidation iinValidation = new IINValidation();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        CustomerDaoImpl customerDao = new CustomerDaoImpl();

        String firstName = request.getParameter(FIRST_NAME);
        String surName = request.getParameter(SURNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String city = request.getParameter(CITY);
        String address = request.getParameter(ADDRESS);
        String iin = request.getParameter(IIN);

        if (firstName.isEmpty() || surName.isEmpty() ||
                email.isEmpty() || password.isEmpty() ||
                city.isEmpty() || address.isEmpty() ||
                iin.isEmpty()) {
            request.setAttribute(ERROR_MESSAGE, EMPTY_FIELDS);
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }

        if (nameValidation.isNotValidUserName(firstName) || nameValidation.isNotValidUserName(surName) ||
                emailValidation.isNotValidEmail(email) || iinValidation.isNotValidIIN(iin)) {
            request.setAttribute(ERROR_MESSAGE, INCORRECT_INPUT);
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


        customerDao.add(customer);


        RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_CUSTOMER);
        dispatcher.forward(request, response);
    }
}


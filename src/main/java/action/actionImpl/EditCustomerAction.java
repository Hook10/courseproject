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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.ERROR_URL;
import static constants.ErrorConstants.*;

public class EditCustomerAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditCustomerAction.class);
    private HashFunction hashFunction = new HashFunction();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

        CustomerDaoImpl customerDao = new CustomerDaoImpl();

        long id = Long.parseLong(String.valueOf(request.getParameter("id")));
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
            request.setAttribute(ERROR_MESSAGE, EMPTY_FIELDS);
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }
        if (!new NameValidation().isValidUserName(firstName) || !new NameValidation().isValidUserName(surName) ||
                !new EmailValidation().isValidEmail(email) || !new IINValidation().isValidIIN(iin)) {
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


            customerDao.update(id, customer);


        new ShowAllCustomersAction().execute(request, response);
    }
}

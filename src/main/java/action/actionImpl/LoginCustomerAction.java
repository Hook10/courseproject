package action.actionImpl;

import encoder.HashFunction;

import action.Action;
import dao.impl.CustomerDaoImpl;
import entity.Customer;
import entity.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import validation.EmailValidation;
import validation.PasswordValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


import static constants.ActionConstants.CUSTOMER_PERSONAL_ACCOUNT_PAGE;
import static constants.ActionConstants.ERROR_URL;
import static constants.ErrorConstants.*;
import static constants.ParamAndAttributeConstants.*;

public class LoginCustomerAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginCustomerAction.class);
    private String email;
    private String password;
    private HashFunction hashPassword = new HashFunction();
    private Customer customer;
    private EmailValidation emailValidation = new EmailValidation();
    private PasswordValidation passwordValidation = new PasswordValidation();
    CustomerDaoImpl customerDao;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        HttpSession session = request.getSession();
        email = request.getParameter(EMAIL);
        password = request.getParameter(PASSWORD);

        if (email.isEmpty() || password.isEmpty()) {
            request.setAttribute(ERROR_MESSAGE, EMPTY_FIELDS);
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }

        if (emailValidation.isNotValidEmail(email) || passwordValidation.isNotValidPassword(password)) {
            request.setAttribute(ERROR_MESSAGE, INCORRECT_INPUT);
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }

        password = hashPassword.getHashFunction(password);
        customerDao = new CustomerDaoImpl();
        customer = customerDao.getCustomerByEmailAndPassword(email, password);

        if (customer != null) {
            session.setAttribute(CUSTOMER, customer);
            session.setAttribute(USER_STATUS, UserStatus.CUSTOMER);
            session.setAttribute(ID, customer.getId());
            request.getRequestDispatcher(CUSTOMER_PERSONAL_ACCOUNT_PAGE).forward(request, response);
        } else {
            request.setAttribute(ERROR_MESSAGE, CUSTOMER_DOES_NOT_EXIST);
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
        }

    }
}

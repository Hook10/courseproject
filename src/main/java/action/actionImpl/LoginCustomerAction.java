package action.actionImpl;

import encoder.HashFunction;

import action.Action;
import dao.impl.CustomerDaoImpl;
import entity.Customer;
import entity.UserStatus;
import validation.EmailValidation;
import validation.PasswordValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


import static constants.ActionConstants.CUSTOMER_PERSONAL_ACCOUNT_PAGE;
import static constants.ActionConstants.ERROR_URL;

public class LoginCustomerAction implements Action {
    String email;
    String password;
    private HashFunction hashPassword = new HashFunction();
    private Customer customer;
    CustomerDaoImpl customerDao;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        email = request.getParameter("email");
        password = request.getParameter("password");

        if (email.isEmpty() || password.isEmpty()) {
            request.setAttribute("message", "Empty fields");
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }

        if (!new EmailValidation().isValidEmail(email) || !new PasswordValidation().isValidPassword(password)) {
            request.setAttribute("message", "incorrect input");
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }

        password = hashPassword.getHashFunction(password);
        customerDao = new CustomerDaoImpl();
        customer = customerDao.getCustomerByEmailAndPassword(email, password);

        if (customer != null) {
            session.setAttribute("customer", customer);
            session.setAttribute("status", UserStatus.CUSTOMER);
            session.setAttribute("id", customer.getId());
            request.getRequestDispatcher(CUSTOMER_PERSONAL_ACCOUNT_PAGE).forward(request, response);
        } else {
            request.setAttribute("message", "the customer does not exist");
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
        }

    }
}

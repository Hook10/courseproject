package action.actionImpl;

import Encoder.HashFunction;
import action.Action;
import dao.daoimpl.CustomerDaoImpl;
import dao.services.LoginDao;
import entity.Customer;
import validation.EmailValidation;
import validation.PasswordValidation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static constants.ActionConstants.CUSTOMER_PERSONAL_ACCOUNT_PAGE;
import static constants.ActionConstants.ERROR_URL;

public class LoginCustomerAction implements Action {
    private String email;
    private String password;
    private HashFunction hashPassword = new HashFunction();
    private CustomerDaoImpl customerDao;
    private Customer customer;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        email = request.getParameter("email");
        password = request.getParameter("password");
        customer.setEmail(email);
        customer.setPassword(password);

        if (email.isEmpty() || password.isEmpty()) {
            request.setAttribute("message", "Empty fields");
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }

        if (!new EmailValidation().isEmailValid(email) || !new PasswordValidation().isPasswordValid(password)) {
            request.setAttribute("message", "incorrect input");
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }

        password = HashFunction.getHash(password);
        customerDao = new CustomerDaoImpl();
        customer = customerDao.getCustomerByEmailAndPassword(email, password);

        if (customer != null) {
            session.setAttribute("customer", customer);
            request.getRequestDispatcher(CUSTOMER_PERSONAL_ACCOUNT_PAGE).forward(request, response);
        } else {
            request.setAttribute("message", "the customer does not exist");
        }

//
//        try {
//            if (loginDao.validate(customer)) {
//
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/loginsuccess.jsp");
//                dispatcher.forward(request, response);
//
//            } else {
//
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
//                dispatcher.forward(request, response);
//
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}

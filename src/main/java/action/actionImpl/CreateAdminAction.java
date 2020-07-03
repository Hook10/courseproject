package action.actionImpl;

import encoder.HashFunction;
import action.Action;
import dao.impl.AdminDaoImpl;
import entity.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import validation.EmailValidation;
import validation.NameValidation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.ERROR_URL;
import static constants.ActionConstants.LOGIN_ADMIN;
import static constants.ErrorConstants.*;

public class CreateAdminAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAdminAction.class);
    private HashFunction hashFunction = new HashFunction();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        AdminDaoImpl adminDao = new AdminDaoImpl();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Long supplier_id = Long.parseLong(request.getParameter("supplier_id"));
        String email = request.getParameter("email");
        String company_name = request.getParameter("company_name");

        if (login.isEmpty() || password.isEmpty() ||
                email.isEmpty() || company_name.isEmpty()) {
            request.setAttribute(ERROR_MESSAGE, EMPTY_FIELDS);
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }

        if (!new NameValidation().isValidUserName(login) || !new EmailValidation().isValidEmail(email)) {
            request.setAttribute(ERROR_MESSAGE, INCORRECT_INPUT);
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }

        Admin admin = new Admin();
        admin.setLogin(login);
        admin.setPassword(password);
        admin.setSupplier_id(supplier_id);
        admin.setEmail(email);
        admin.setCompanyName(company_name);


            adminDao.add(admin);

        RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_ADMIN);
        dispatcher.forward(request, response);


    }
}

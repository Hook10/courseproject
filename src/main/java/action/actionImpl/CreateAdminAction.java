package action.actionImpl;

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
import static constants.ParamAndAttributeConstants.*;

public class CreateAdminAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAdminAction.class);
    private final NameValidation nameValidation = new NameValidation();
    private final EmailValidation emailValidation = new EmailValidation();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        AdminDaoImpl adminDao = new AdminDaoImpl();

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        long supplierId = Long.parseLong(request.getParameter(ID_SUPPLIER));
        String email = request.getParameter(EMAIL);
        String companyName = request.getParameter(COMPANY_NAME);

        if (login.isEmpty() || password.isEmpty() ||
                email.isEmpty() || companyName.isEmpty()) {
            request.setAttribute(ERROR_MESSAGE, EMPTY_FIELDS);
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }

        if (nameValidation.isNotValidUserName(login) || emailValidation.isNotValidEmail(email)) {
            request.setAttribute(ERROR_MESSAGE, INCORRECT_INPUT);
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }


        Admin admin = new Admin();
        admin.setLogin(login);
        admin.setPassword(password);
        admin.setSupplierId(supplierId);
        admin.setEmail(email);
        admin.setCompanyName(companyName);

        adminDao.add(admin);

        RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_ADMIN);
        dispatcher.forward(request, response);


    }
}

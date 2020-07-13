package action.actionImpl;

import encoder.HashFunction;

import action.Action;
import dao.impl.AdminDaoImpl;
import entity.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import entity.UserStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static constants.ActionConstants.ADMIN_CABINET;
import static constants.ActionConstants.ERROR_URL;
import static constants.ErrorConstants.*;
import static constants.ParamAndAttributeConstants.*;

public class LoginAdminAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAdminAction.class);
    private String login;
    private String password;
    private HashFunction hashPassword = new HashFunction();
    private Admin admin;
    AdminDaoImpl adminDao;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

        HttpSession session = request.getSession();

        login = request.getParameter(LOGIN);
        password = request.getParameter(PASSWORD);

        if (login.isEmpty() || password.isEmpty()) {
            request.setAttribute(ERROR_MESSAGE, EMPTY_FIELDS);
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }

        password = hashPassword.getHashFunction(password);
        adminDao = new AdminDaoImpl();
        admin = adminDao.getAdminByLoginAndPassword(login, password);

        if (admin != null) {
            session.setAttribute(ADMIN, admin);
            session.setAttribute(USER_STATUS, UserStatus.WEBSITEADMIN);
            request.getRequestDispatcher(ADMIN_CABINET).forward(request, response);
        } else {
            request.setAttribute(ERROR_MESSAGE, ADMIN_DOES_NOT_EXIST);
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
        }
    }
}

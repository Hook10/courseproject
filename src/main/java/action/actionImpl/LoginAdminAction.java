package action.actionImpl;

import Encoder.HashFunction;
import action.Action;
import dao.daoimpl.AdminDaoImpl;
import dao.daoimpl.CustomerDaoImpl;
import entity.Admin;
import entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static constants.ActionConstants.ADMIN_CABINET;
import static constants.ActionConstants.ERROR_URL;

public class LoginAdminAction implements Action {
    String login ;
    String password;
    private HashFunction hashPassword = new HashFunction();
    private Admin admin;
    AdminDaoImpl adminDao;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        login = request.getParameter("login");
        password = request.getParameter("password");

        if(login.isEmpty() || password.isEmpty()) {
            request.setAttribute("message", "Empty fields");
            request.getRequestDispatcher(ERROR_URL).forward(request, response);
            return;
        }

        password = hashPassword.getHashFunction(password);
        adminDao = new AdminDaoImpl();
        admin = adminDao.getAdminByLoginAndPassword(login, password);

        if(admin != null)   {
            session.setAttribute("admin", admin);
            request.getRequestDispatcher(ADMIN_CABINET).forward(request,response);
        } else {
            request.setAttribute("message", "the admin does not exist");
            request.getRequestDispatcher(ERROR_URL).forward(request,response);
        }
    }
}

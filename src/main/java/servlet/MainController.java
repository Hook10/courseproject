package servlet;

import action.Action;
import action.ActionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MainController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getAction(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getAction(request, response);
    }


    private void getAction(HttpServletRequest request, HttpServletResponse response) {
        try {
            Action action = ActionFactory.getInstance().getAction(request);
            action.execute(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}

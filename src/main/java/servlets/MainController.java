package servlets;




import action.Action;
import action.ActionFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MainController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(MainController.class);


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
        } catch (ServletException e) {
            e.printStackTrace();
            LOGGER.error("Error in MainController");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

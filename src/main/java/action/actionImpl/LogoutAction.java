package action.actionImpl;

import action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static constants.ActionConstants.INDEX_URL;


public class LogoutAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();
        request.getRequestDispatcher(INDEX_URL).forward(request, response);
    }
}

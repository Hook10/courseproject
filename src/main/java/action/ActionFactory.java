package action;

import action.actionImpl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionFactory {

    private static Map<String, Action> actions = new ConcurrentHashMap<>();
    private static ActionFactory instance = null;

    private ActionFactory(){

    }

    static {
        actions.put("/index", new LandingPageAction());
        actions.put("/login", new LoginCustomerAction());
        actions.put("/login_button", new LoginReferenceOnIndexPageAction());
        actions.put("/register", new RegisterCustomerAction());
        actions.put("/register_button", new RegistrationOnIndexPageAction());
        actions.put("/customerPersonalAccountPage", new EnterInCustomerPersonalAccountPageAction());


    }

    public static ActionFactory getInstance() {
        if (instance == null) {
            instance = new ActionFactory();
        }
        return instance;
    }

    public Action getAction(HttpServletRequest request) {
        Action action = actions.get(request.getPathInfo());

        return action;
    }
}

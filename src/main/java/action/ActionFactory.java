package action;

import action.actionImpl.LandingPageAction;

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

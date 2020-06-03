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
        actions.put("/login_admin_button", new AdminLoginReferenceOnIndexPageAction());
        actions.put("/adminLogin", new LoginAdminAction());
        actions.put("/create_admin_button", new CreateAdminButtonAction());
        actions.put("/create_admin", new CreateAdminAction());
        actions.put("/create_supplier_button", new CreateSupplierButtonAction());
        actions.put("/create_supplier", new CreateSupplierAction());
        actions.put("/show_gas", new ShowGasAction());
        actions.put("/add_gas_data", new AddGasDataButtonAction());
        actions.put("/add_customer_gas_data", new AddGasDataAction());


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

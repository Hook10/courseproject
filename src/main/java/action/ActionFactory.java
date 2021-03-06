package action;

import action.actionImpl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionFactory {

    private static Map<String, Action> actions = new ConcurrentHashMap<>();
    private static ActionFactory instance = null;

    private ActionFactory() {

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
        actions.put("/add_data", new AddDataButtonAction());
        actions.put("/add_customer_data", new AddDataAction());
        actions.put("/show_water", new ShowWaterAction());
        actions.put("/show_electricity", new ShowElectrAction());
        actions.put("/logout", new LogoutAction());
        actions.put("/editCustomerDataButton", new EditCustomerDataButtonAction());
        actions.put("/editCustomerData", new EditCustomerDataAction());
        actions.put("/delete_data", new DeleteDataAction());
        actions.put("/payInvoiceButton", new PayInvoiceButtonAction());
        actions.put("/showInvoice", new ShowInvoiceAction());
        actions.put("/paymentForm", new PaymentFormAction());
        actions.put("/show_all_customers", new ShowAllCustomersAction());
        actions.put("/show_all_suppliers", new ShowAllSuppliersAction());
        actions.put("/delete_customer", new DeleteCustomerAction());
        actions.put("/editCustomerButton", new EditCustomerButtonAction());
        actions.put("/editCustomer", new EditCustomerAction());
        actions.put("/editSupplierButton", new EditSupplierButtonAction());
        actions.put("/editSupplier", new EditSupplierAction());
        actions.put("/delete_supplier", new DeleteSupplierAction());
        actions.put("/admin_cabinet", new EnterAdminCabinetAction());


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

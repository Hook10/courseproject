package action.actionImpl;

import action.Action;
import dao.impl.DataDaoImpl;
import entity.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static action.actionImpl.ShowElectrAction.ELECTRICITY_SUPPLIER;
import static action.actionImpl.ShowWaterAction.WATER_SUPPLIER;
import static constants.ActionConstants.*;
import static constants.ErrorConstants.WRONG_SUPPLIER;
import static constants.ErrorConstants.ERROR_MESSAGE;

public class EditCustomerDataAction implements Action {
    public static final int GAS_SUPPLIER = 1;
    private static final Logger LOGGER = LoggerFactory.getLogger(EditCustomerDataAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

        DataDaoImpl dataDao = new DataDaoImpl();

        long id = Long.parseLong(String.valueOf(request.getParameter("id")));
        System.out.println(id);

        String month = request.getParameter("month");
        long data = Long.parseLong(request.getParameter("data"));
        int id_supplier = Integer.parseInt(request.getParameter("id_supplier"));
        long id_customer = Long.parseLong(String.valueOf(request.getParameter("id_customer")));
        System.out.println(id_customer);

        Data dataFromCustomer = new Data();

        dataFromCustomer.setMonth(month);
        dataFromCustomer.setData(data);
        dataFromCustomer.setIdCustomer(id_customer);
        dataFromCustomer.setIdSupplier(id_supplier);


            dataDao.update(id, dataFromCustomer);

        supplierForward(request, response, id_supplier, GAS_SUPPLIER);
    }

    static void supplierForward(HttpServletRequest request, HttpServletResponse response, int id_supplier, int gasSupplier) throws ServletException, IOException {
        if(id_supplier == gasSupplier) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(FORWARD_TO_SHOW_GAS_DATA);
            dispatcher.forward(request, response);
        } else if (id_supplier == WATER_SUPPLIER) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(FORWARD_TO_SHOW_WATER_DATA);
            dispatcher.forward(request, response);
        } else if (id_supplier == ELECTRICITY_SUPPLIER) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(FORWARD_TO_SHOW_ELECTR_DATA);
            dispatcher.forward(request, response);
        } else {
            request.setAttribute(ERROR_MESSAGE, WRONG_SUPPLIER);
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_URL);
            dispatcher.forward(request, response);
        }
    }
}

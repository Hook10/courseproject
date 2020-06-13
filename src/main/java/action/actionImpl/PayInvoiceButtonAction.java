package action.actionImpl;

import action.Action;
import dao.daoimpl.InvoiceDaoImpl;
import entity.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import static action.actionImpl.AddDataAction.GAS_SUPPLIER;
import static action.actionImpl.ShowElectrAction.ELECTRICITY_SUPPLIER;
import static action.actionImpl.ShowWaterAction.WATER_SUPPLIER;
import static constants.ActionConstants.*;
import static constants.TariffConstants.*;

public class PayInvoiceButtonAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditCustomerDataButtonAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

//        request.setAttribute("id_data", request.getParameter("id"));
//        request.setAttribute("id_supplier", request.getParameter("id_supplier"));
//        request.setAttribute("id_customer", request.getParameter("id_customer"));
//        request.setAttribute("month", request.getParameter("month"));
//        request.setAttribute("data", request.getParameter("data"));

        Invoice invoice = new Invoice();
        long id_data = Long.parseLong(request.getParameter("id_data"));
        long id_supplier = Long.parseLong(request.getParameter("id_supplier"));
        long id_customer = Long.parseLong(request.getParameter("id_customer"));
        String month = request.getParameter("month");
        long data = Long.parseLong(String.valueOf(request.getParameter("data")));
        System.out.println(id_data);

        long cost = 0;
        if (id_supplier == GAS_SUPPLIER) {
           cost = data * GAS_TARIFF_FROM_11_06_2020_KZT_FOR_1_CUBIC_METER;
        } else if (id_supplier == WATER_SUPPLIER) {
           cost = data * WATER_TARIFF_FROM_11_06_2020_KZT_FOR_1_CUBIC_METER;
        } else if (id_supplier == ELECTRICITY_SUPPLIER) {
            cost = data * ELECTR_TARIFF_FROM_11_06_2020__KZT_FOR_1_KILOWATT;
        } else {
            request.setAttribute("message", "wrong supplier");
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_URL);
            dispatcher.forward(request, response);
        }

        invoice.setIdData(id_data);
        invoice.setIdSupplier(id_supplier);
        invoice.setIdCustomer(id_customer);
        invoice.setMonth(month);
        invoice.setData(data);
        invoice.setCost(cost);

        InvoiceDaoImpl invoiceDao = new InvoiceDaoImpl();
        try{
            invoiceDao.add(invoice);
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        InvoiceDaoImpl invoiceDao1 = new InvoiceDaoImpl();
//        List<Invoice> invoicesList=null;
//        try{
//            invoicesList = invoiceDao1.getAllBySupplierIdAndCustomerId(id_supplier, id_customer);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        request.setAttribute("id_data", request.getParameter("id"));
        request.setAttribute("id_supplier", request.getParameter("id_supplier"));
        request.setAttribute("id_customer", request.getParameter("id_customer"));
        request.setAttribute("month", request.getParameter("month"));
        request.setAttribute("data", request.getParameter("data"));

        new ShowInvoiceAction().execute(request, response);
///        request.setAttribute("invoicesList", invoicesList);
//        request.getRequestDispatcher(PAY_INVOICE).forward(request, response);
    }
}

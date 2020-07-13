package action.actionImpl;

import action.Action;
import dao.impl.InvoiceDaoImpl;
import entity.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static action.actionImpl.AddDataAction.GAS_SUPPLIER;
import static action.actionImpl.ShowElectrAction.ELECTRICITY_SUPPLIER;
import static action.actionImpl.ShowWaterAction.WATER_SUPPLIER;
import static constants.ActionConstants.*;
import static constants.ErrorConstants.WRONG_SUPPLIER;
import static constants.ErrorConstants.ERROR_MESSAGE;
import static constants.ParamAndAttributeConstants.*;
import static constants.TariffConstants.*;

public class PayInvoiceButtonAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayInvoiceButtonAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

        Invoice invoice = new Invoice();
        long idData = Long.parseLong(request.getParameter(ID_DATA));
        long idSupplier = Long.parseLong(request.getParameter(ID_SUPPLIER));
        long idCustomer = Long.parseLong(request.getParameter(ID_CUSTOMER));
        String month = request.getParameter("month");
        long dataFromCustomersWaterGasElectricityMeter = Long.parseLong(String.valueOf(request.getParameter(DATA)));
        LOGGER.info(idData + " id_data PayInvoiceButtonAction");

        long cost = 0;
        switch ((int) idSupplier) {
            case (GAS_SUPPLIER):
                cost = dataFromCustomersWaterGasElectricityMeter * GAS_TARIFF_FROM_11_06_2020_KZT_FOR_1_CUBIC_METER;
                break;
            case (WATER_SUPPLIER):
                cost = dataFromCustomersWaterGasElectricityMeter * WATER_TARIFF_FROM_11_06_2020_KZT_FOR_1_CUBIC_METER;
                break;
            case (ELECTRICITY_SUPPLIER):
                cost = dataFromCustomersWaterGasElectricityMeter * ELECTR_TARIFF_FROM_11_06_2020__KZT_FOR_1_KILOWATT;
                break;
            default:
                request.setAttribute(ERROR_MESSAGE, WRONG_SUPPLIER);
                RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_URL);
                dispatcher.forward(request, response);
        }


        invoice.setIdData(idData);
        invoice.setIdSupplier(idSupplier);
        invoice.setIdCustomer(idCustomer);
        invoice.setMonth(month);
        invoice.setData(dataFromCustomersWaterGasElectricityMeter);
        invoice.setCost(cost);

        InvoiceDaoImpl invoiceDao = new InvoiceDaoImpl();

        invoiceDao.add(invoice);


        request.setAttribute(ID_DATA, request.getParameter(ID_DATA));
        request.setAttribute(ID_SUPPLIER, request.getParameter(ID_SUPPLIER));
        request.setAttribute(ID_CUSTOMER, request.getParameter(ID_CUSTOMER));
        request.setAttribute(MONTH, request.getParameter(MONTH));
        request.setAttribute(DATA, request.getParameter(DATA));

        new ShowInvoiceAction().execute(request, response);
    }
}

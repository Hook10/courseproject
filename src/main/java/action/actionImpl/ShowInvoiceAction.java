package action.actionImpl;

import action.Action;
import dao.impl.InvoiceDaoImpl;
import entity.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static constants.ActionConstants.PAY_INVOICE;
import static constants.ParamAndAttributeConstants.*;

public class ShowInvoiceAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowInvoiceAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

        long id_supplier = Long.parseLong(request.getParameter(ID_SUPPLIER));
        long id_customer = Long.parseLong(request.getParameter(ID_CUSTOMER));
        long id_data = Long.parseLong(request.getParameter(ID_DATA));


        InvoiceDaoImpl invoiceDao1 = new InvoiceDaoImpl();

        List<Invoice> invoicesList = invoiceDao1.getAllByDataId(id_data);

        request.setAttribute(INVOICES_LIST, invoicesList);
        request.getRequestDispatcher(PAY_INVOICE).forward(request, response);
    }
}

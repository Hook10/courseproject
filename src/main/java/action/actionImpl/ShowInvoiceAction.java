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

        long idData = Long.parseLong(request.getParameter(ID_DATA));


        InvoiceDaoImpl invoiceDao1 = new InvoiceDaoImpl();

        List<Invoice> invoicesList = invoiceDao1.getAllByDataId(idData);

        request.setAttribute(INVOICES_LIST, invoicesList);
        request.getRequestDispatcher(PAY_INVOICE).forward(request, response);
    }
}

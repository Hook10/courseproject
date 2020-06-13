package action.actionImpl;

import action.Action;
import dao.daoimpl.InvoiceDaoImpl;
import entity.Invoice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static constants.ActionConstants.PAY_INVOICE;

public class ShowInvoiceAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        long id_data = Long.parseLong(request.getParameter("id_data"));
        long id_supplier = Long.parseLong(request.getParameter("id_supplier"));
        long id_customer = Long.parseLong(request.getParameter("id_customer"));
//        String month = request.getParameter("month");
//        long data = Long.parseLong(String.valueOf(request.getParameter("data")));

        InvoiceDaoImpl invoiceDao1 = new InvoiceDaoImpl();
        List<Invoice> invoicesList=null;
        try{
            invoicesList = invoiceDao1.getAllBySupplierIdAndCustomerId(id_supplier, id_customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("invoicesList", invoicesList);
        request.getRequestDispatcher(PAY_INVOICE).forward(request, response);
    }
}

package action.actionImpl;

import action.Action;
import dao.daoimpl.DataDaoImpl;
import entity.Data;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditCustomerDataAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     DataDaoImpl dataDao = new DataDaoImpl();
     int id = Integer.parseInt(request.getParameter("id"));
     String month = request.getParameter("month");
     int data = Integer.parseInt(request.getParameter("data"));
     int id_supplier = Integer.parseInt(request.getParameter("id_supplier"));
     int id_customer = Integer.parseInt(request.getParameter("id_customer"));
        Data data1 = new Data(id,month,data,id_customer,id_supplier);
        try {
            dataDao.update(data1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new EnterInCustomerPersonalAccountPageAction().execute(request,response);

    }
}

package action.actionImpl;

import action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.*;

public class AddGasDataButtonAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddGasDataButtonAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(CREATE_DATA_CUSTOMER).forward(request, response);

        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
//        DataDaoImpl dataDao = new DataDaoImpl();
//
//        String month = request.getParameter("month");
//        Long data = Long.parseLong(request.getParameter("Gas_data"));
//        Long idCustomer = Long.parseLong(request.getParameter("id_customer"));
//        int idSupplier = Integer.parseInt(request.getParameter("id_supplier"));
//
//        if(month.isEmpty() || request.getParameter("Gas_data").equals("") ||
//        request.getParameter("id_customer").equals("") ||
//        request.getParameter("id_supplier").equals("")) {
//            request.setAttribute("message", "empty fields");
//            request.getRequestDispatcher(ERROR_URL).forward(request,response);
//        }
//
//        Data data1 = new Data();
//        data1.setMonth(month);
//        data1.setData(data);
//        data1.setIdCustomer(idCustomer);
//        data1.setIdSupplier(idSupplier);
//
//        try{
//            dataDao.add(data1);
//        } catch (SQLException e ) {
//            LOGGER.info("Data creating sql error");
//            e.printStackTrace();
//        }
//        RequestDispatcher dispatcher =  request.getRequestDispatcher(CREATE_DATA_CUSTOMER);
//        dispatcher.forward(request, response);
    }
}

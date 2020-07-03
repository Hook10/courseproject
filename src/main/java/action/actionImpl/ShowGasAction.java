package action.actionImpl;

import action.Action;
import dao.impl.DataDaoImpl;
import entity.Data;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import static constants.ActionConstants.SHOW_GAS_DATA_PERSON_LIST;

public class ShowGasAction implements Action {
    public static final int GAS_SUPPLIER = 1;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showDataBySupplier(request, response, GAS_SUPPLIER, SHOW_GAS_DATA_PERSON_LIST);
    }

    static void showDataBySupplier(HttpServletRequest request, HttpServletResponse response, int gasSupplier, String showGasDataPersonList) throws ServletException, IOException {
        DataDaoImpl dataDao = new DataDaoImpl();

        List<Data> dataList = null;

            dataList = dataDao.getAllBySupplierId(gasSupplier);


        request.setAttribute("dataList", dataList);
        request.getRequestDispatcher(showGasDataPersonList).forward(request, response);
    }
}

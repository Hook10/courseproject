package action.actionImpl;

import action.Action;
import dao.impl.DataDaoImpl;
import entity.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


import static constants.ActionConstants.SHOW_GAS_DATA_PERSON_LIST;
import static constants.ParamAndAttributeConstants.DATA_LIST;

public class ShowGasAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowGasAction.class);
    public static final int GAS_SUPPLIER = 1;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        showDataBySupplier(request, response, GAS_SUPPLIER, SHOW_GAS_DATA_PERSON_LIST);
    }

    static void showDataBySupplier(HttpServletRequest request, HttpServletResponse response, int gasSupplier, String showGasDataPersonList) throws ServletException, IOException {
        DataDaoImpl dataDao = new DataDaoImpl();

        List<Data> dataList = dataDao.getAllBySupplierId(gasSupplier);

        request.setAttribute(DATA_LIST, dataList);
        request.getRequestDispatcher(showGasDataPersonList).forward(request, response);
    }
}

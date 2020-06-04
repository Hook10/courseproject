package action.actionImpl;

import action.Action;
import dao.daoimpl.DataDaoImpl;
import entity.Data;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static constants.ActionConstants.SHOW_GAS_DATA_PERSON_LIST;
import static constants.ActionConstants.SHOW_WATER_DATA_PERSON_LIST;

public class ShowElectrAction implements Action {
    public static final int ELECTRICITY_SUPPLIER = 3;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShowGasAction.showDataBySupplier(request, response, ELECTRICITY_SUPPLIER, SHOW_WATER_DATA_PERSON_LIST);
    }
}

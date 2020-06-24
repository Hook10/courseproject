package action.actionImpl;

import action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.SHOW_WATER_DATA_PERSON_LIST;


public class ShowWaterAction implements Action {
    public static final int WATER_SUPPLIER = 2;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShowGasAction.showDataBySupplier(request, response, WATER_SUPPLIER, SHOW_WATER_DATA_PERSON_LIST);
    }
}

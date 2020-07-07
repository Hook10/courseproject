package action.actionImpl;

import action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.SHOW_WATER_DATA_PERSON_LIST;


public class ShowWaterAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowWaterAction.class);
    public static final int WATER_SUPPLIER = 2;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        ShowGasAction.showDataBySupplier(request, response, WATER_SUPPLIER, SHOW_WATER_DATA_PERSON_LIST);
    }
}

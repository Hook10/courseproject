package action.actionImpl;

import action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.ActionConstants.*;

public class ShowElectrAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowElectrAction.class);
    public static final int ELECTRICITY_SUPPLIER = 3;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());
        ShowGasAction.showDataBySupplier(request, response, ELECTRICITY_SUPPLIER, SHOW_ELECTR_DATA_PERSON_LIST);
    }
}

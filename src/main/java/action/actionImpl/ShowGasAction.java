package action.actionImpl;

import action.Action;
import com.mysql.cj.Session;
import dao.daoimpl.DataDaoImpl;
import entity.Customer;
import entity.Data;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static constants.ActionConstants.SHOW_DATA_PERSON_LIST;

public class ShowGasAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataDaoImpl dataDao = new DataDaoImpl();
        List<Data> dataList = null;

        try {

            dataList = dataDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("dataList", dataList);
        request.getRequestDispatcher(SHOW_DATA_PERSON_LIST).forward(request, response);
    }
}

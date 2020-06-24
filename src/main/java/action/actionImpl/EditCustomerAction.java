package action.actionImpl;

import encoder.HashFunction;
import action.Action;
import dao.impl.CustomerDaoImpl;
import entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditCustomerAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditCustomerAction.class);
    private HashFunction hashFunction = new HashFunction();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Пришел запрос {} на URI: {}", request.getMethod(), request.getRequestURI());

        CustomerDaoImpl customerDao = new CustomerDaoImpl();

        long id = Long.parseLong(String.valueOf(request.getParameter("id")));
        System.out.println(id);
        String firstName=request.getParameter("firstName");
        String surName=request.getParameter("surname");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String city=request.getParameter("city");
        String address=request.getParameter("address");
        String iin=request.getParameter("iin");
        System.out.println("first_name="+firstName);

        password = hashFunction.getHashFunction(password);

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setSurname(surName);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setCity(city);
        customer.setAddress(address);
        customer.setIin(iin);

        try {
            customerDao.update(id, customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        request.getRequestDispatcher(SHOW_CUSTOMERS).forward(request, response);
        new ShowAllCustomersAction().execute(request,response);

//        firstName = request.getParameter("firstName");
//        surName = request.getParameter("surname");
//        email = request.getParameter("email");
//        city = request.getParameter("city");
//        password=request.getParameter("password")
//        address = request.getParameter("address");
//        iin = request.getParameter("iin");
    }
}

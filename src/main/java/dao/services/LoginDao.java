package dao.services;

import connectionpool.DBUtil;
import dao.daoimpl.SupplierDaoImpl;
import entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginDao.class);

    public boolean validate(Customer customer)  {

        boolean status = false;

        String sql = "select * from customers where email = ? and password = ? ";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, customer.getEmail());
            preparedStatement.setString(2, customer.getPassword());

            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            status = resultSet.next();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return status;
    }
}


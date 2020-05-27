import Encoder.HashFunction;
import connectionpool.DBUtil;
import dao.daoimpl.AdminDaoImpl;
import entity.Admin;
import entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class ClassA {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassA.class);
    public static void main(String[] args) {
        Admin admin = new Admin();




        HashFunction hashPassword = new HashFunction();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ADMIN(ID, LOGIN, PASSWORD, SUPPLIER_ID, EMAIL, COMPANY_NAME) " +
                             "VALUES(?,?,?,?,?,?)")) {
            preparedStatement.setLong(1, 1L);
            preparedStatement.setString(2, "admin1");
            preparedStatement.setString(3, hashPassword.getHashFunction("Admin12345678"));
            preparedStatement.setLong(4,1 );
            preparedStatement.setString(5, "mainadmin@gmail.com");
            preparedStatement.setString(6, "mainCompany");

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }

    }

}

import connectionpool.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ClassA {


    public static void main(String[] args) throws SQLException {

        String sql = "INSERT INTO CUSTOMERS(ID, FIRST_NAME, SURNAME, LOGIN, PASSWORD, CITY, ADDRESS, IIN) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, 2);
            preparedStatement.setString(2, "aasdf");
            preparedStatement.setString(3, "asdf");
            preparedStatement.setString(4, "asdfasdf");
            preparedStatement.setString(5, "asdfasdf");
            preparedStatement.setString(6, "asdfasdfadfa");
            preparedStatement.setString(7, "asdfasdfasdf");
            preparedStatement.setString(8, "adsfasdff");

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

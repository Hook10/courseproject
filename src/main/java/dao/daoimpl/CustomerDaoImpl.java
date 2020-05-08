package dao.daoimpl;

import connectionpool.DBUtil;
import dao.BaseDAO;
import entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements BaseDAO<Customer> {


    @Override
    public void add(Customer customer) throws SQLException {

        String sql = "INSERT INTO CUSTOMERS(ID, FIRST_NAME, SURNAME, EMAIL, PASSWORD, CITY, ADDRESS, IIN) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, customer.getId());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getSurname());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getPassword());
            preparedStatement.setString(6, customer.getCity());
            preparedStatement.setString(7, customer.getAddress());
            preparedStatement.setString(8, customer.getIin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> customerList = new ArrayList<>();

        String sql = "SELECT ID, FIRST_NAME, SURNAME, EMAIL, PASSWORD, CITY, ADDRESS, IIN FROM CUSTOMERS";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("ID"));
                customer.setFirstName(resultSet.getString("FIRST_NAME"));
                customer.setSurname(resultSet.getString("SURNAME"));
                customer.setEmail(resultSet.getString("EMAIL"));
                customer.setPassword(resultSet.getString("PASSWORD"));
                customer.setCity(resultSet.getString("CITY"));
                customer.setAddress(resultSet.getString("ADDRESS"));
                customer.setIin(resultSet.getString("Iin"));

                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    @Override
    public Customer getById(Long id) throws SQLException {

        String sql = "SELECT ID, FIRST_NAME, SURNAME, EMAIL, PASSWORD, CITY, ADDRESS, IIN FROM CUSTOMERS WHERE ID = ?";
        Customer customer = new Customer();
        try(Connection connection = DBUtil.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            customer.setId(resultSet.getLong("ID"));
            customer.setFirstName(resultSet.getString("FIRST_NAME"));
            customer.setSurname(resultSet.getString("SURNAME"));
            customer.setEmail(resultSet.getString("EMAIL"));
            customer.setPassword(resultSet.getString("PASSWORD"));
            customer.setCity(resultSet.getString("CITY"));
            customer.setAddress(resultSet.getString("ADDRESS"));
            customer.setIin(resultSet.getString("iin"));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void update(Customer customer) throws SQLException {

        String sql = "UPDATE CUSTOMERS SET FIRST_NAME =?, SURNAME=?,EMAIL=?, PASSWORD=?, CITY=?, ADDRESS=?, IIN=? WHERE ID = ?";
        try(Connection connection = DBUtil.getDataSource().getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(sql) ) {

            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPassword());
            preparedStatement.setString(5, customer.getCity());
            preparedStatement.setString(6, customer.getAddress());
            preparedStatement.setString(7, customer.getIin());
            preparedStatement.setLong(8, customer.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Customer customer) throws SQLException {

        String sql = "DELETE FROM CUSTOMERS WHERE ID=?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


package dao.impl;

import connectionpool.DBUtil;
import dao.BaseDAO;
import entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements BaseDAO<Customer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDaoImpl.class);
    private static final String ADD_CUSTOMER =  "INSERT INTO CUSTOMERS(ID, FIRST_NAME, SURNAME, EMAIL, PASSWORD, CITY, " +
            "ADDRESS, IIN) VALUES(?,?,?,?,?,?,?,?)";
    private static final String GET_ALL_CUSTOMERS = "SELECT ID, FIRST_NAME, SURNAME, EMAIL, PASSWORD, CITY, ADDRESS, " +
            "IIN FROM CUSTOMERS";
    private static final String GET_CUSTOMER_BY_ID = "SELECT ID, FIRST_NAME, SURNAME, EMAIL, PASSWORD, CITY, ADDRESS, " +
            "IIN FROM CUSTOMERS WHERE ID = ?";
    private static final String UPDATE_CUSTOMERS = "UPDATE CUSTOMERS SET FIRST_NAME =?, SURNAME=?, EMAIL=?, PASSWORD=?,  " +
            "CITY=?, ADDRESS=?, IIN=? WHERE ID = ? ";
    private static final String DELETE_CUSTOMER_BY_ID = "DELETE FROM CUSTOMERS WHERE ID=?";
    private static final String GET_CUSTOMER_BY_EMAIL_AND_PASSWORD = "select * from customers where email = ? and password = ? ";
//PASSWORD=?,
    @Override
    public void add(Customer customer) throws SQLException {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_CUSTOMER)) {
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
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> customerList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CUSTOMERS)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer();
                SetFields(resultSet, customer);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return customerList;
    }

    private void SetFields(ResultSet resultSet, Customer customer) throws SQLException {
        customer.setId(resultSet.getLong("ID"));
        customer.setFirstName(resultSet.getString("FIRST_NAME"));
        customer.setSurname(resultSet.getString("SURNAME"));
        customer.setEmail(resultSet.getString("EMAIL"));
        customer.setPassword(resultSet.getString("PASSWORD"));
        customer.setCity(resultSet.getString("CITY"));
        customer.setAddress(resultSet.getString("ADDRESS"));
        customer.setIin(resultSet.getString("IIN"));
    }

    @Override
    public Customer getById(Long id) throws SQLException {

        Customer customer = new Customer();
        try(Connection connection = DBUtil.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CUSTOMER_BY_ID)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            getCustomer(customer, resultSet);
            SetFields(resultSet, customer);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return customer;

    }

    public Customer getCustomer(Customer customer, ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            customer = new Customer();
            SetFields(resultSet, customer);
        }
        return customer;
    }

    @Override
    public void update(long id, Customer customer) throws SQLException {
        System.out.println("Update MySql Customer");
        try(Connection connection = DBUtil.getDataSource().getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(UPDATE_CUSTOMERS) ) {
            preparedStatement.setLong(8, id);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPassword());
            preparedStatement.setString(5, customer.getCity());
            preparedStatement.setString(6, customer.getAddress());
            preparedStatement.setString(7, customer.getIin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Customer customer) throws SQLException {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_BY_ID)) {
            preparedStatement.setLong(1, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
    public void removeOneById(long id) {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_BY_ID)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public Customer getCustomerByEmailAndPassword(String email, String password)  {

        Customer customer = null;

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CUSTOMER_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            return getCustomer(customer, preparedStatement.executeQuery());

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return customer;
    }

}
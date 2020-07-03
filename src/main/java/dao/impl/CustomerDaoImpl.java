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
    private static final String ADD_CUSTOMER = "INSERT INTO CUSTOMERS(ID, FIRST_NAME, SURNAME, EMAIL, PASSWORD, CITY, " +
            "ADDRESS, IIN) VALUES(?,?,?,?,?,?,?,?)";
    private static final String GET_ALL_CUSTOMERS = "SELECT ID, FIRST_NAME, SURNAME, EMAIL, PASSWORD, CITY, ADDRESS, " +
            "IIN FROM CUSTOMERS";
    private static final String GET_CUSTOMER_BY_ID = "SELECT ID, FIRST_NAME, SURNAME, EMAIL, PASSWORD, CITY, ADDRESS, " +
            "IIN FROM CUSTOMERS WHERE ID = ?";
    private static final String UPDATE_CUSTOMERS = "UPDATE CUSTOMERS SET FIRST_NAME =?, SURNAME=?, EMAIL=?, PASSWORD=?,  " +
            "CITY=?, ADDRESS=?, IIN=? WHERE ID = ? ";
    private static final String DELETE_CUSTOMER_BY_ID = "DELETE FROM CUSTOMERS WHERE ID=?";
    private static final String GET_CUSTOMER_BY_EMAIL_AND_PASSWORD = "select * from customers where email = ? and password = ? ";

    private static final String ID_PARAM = "ID";
    private static final String FIRST_NAME_PARAM = "FIRST_NAME";
    private static final String SURNAME_PARAM = "SURNAME";
    private static final String EMAIL_PARAM = "EMAIL";
    private static final String PASSWORD_PARAM = "PASSWORD";
    private static final String CITY_PARAM = "CITY";
    private static final String ADDRESS_PARAM = "ADDRESS";
    private static final String IIN_PARAM = "IIN";

    private static final int ID_PARAM_INDEX = 1;
    private static final int FIRST_NAME_PARAM_INDEX = 2;
    private static final int SURNAME_PARAM_INDEX = 3;
    private static final int EMAIL_PARAM_INDEX = 4;
    private static final int PASSWORD_PARAM_INDEX = 5;
    private static final int CITY_PARAM_INDEX = 6;
    private static final int ADDRESS_PARAM_INDEX = 7;
    private static final int IIN_PARAM_INDEX = 8;

    private static final int UPDATE_FIRST_NAME_PARAM_INDEX = 1;
    private static final int UPDATE_SURNAME_PARAM_INDEX = 2;
    private static final int UPDATE_EMAIL_PARAM_INDEX = 3;
    private static final int UPDATE_PASSWORD_PARAM_INDEX = 4;
    private static final int UPDATE_CITY_PARAM_INDEX = 5;
    private static final int UPDATE_ADDRESS_PARAM_INDEX = 6;
    private static final int UPDATE_IIN_PARAM_INDEX = 7;
    private static final int UPDATE_ID_PARAM_INDEX = 8;

    private static final int GET_CUSTOMER_BY_EMAIL_AND_PASSWORD_EMAIL_PARAM_INDEX = 1;
    private static final int GET_CUSTOMER_BY_EMAIL_AND_PASSWORD_PASSWORD_PARAM_INDEX = 2;



    @Override
    public void add(Customer customer) {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_CUSTOMER)) {
            preparedStatement.setLong(ID_PARAM_INDEX, customer.getId());
            preparedStatement.setString(FIRST_NAME_PARAM_INDEX, customer.getFirstName());
            preparedStatement.setString(SURNAME_PARAM_INDEX, customer.getSurname());
            preparedStatement.setString(EMAIL_PARAM_INDEX, customer.getEmail());
            preparedStatement.setString(PASSWORD_PARAM_INDEX, customer.getPassword());
            preparedStatement.setString(CITY_PARAM_INDEX, customer.getCity());
            preparedStatement.setString(ADDRESS_PARAM_INDEX, customer.getAddress());
            preparedStatement.setString(IIN_PARAM_INDEX, customer.getIin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Customer> getAll() {
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
        }
        return customerList;
    }


    private void SetFields(ResultSet resultSet, Customer customer) throws SQLException {
        customer.setId(resultSet.getLong(ID_PARAM));
        customer.setFirstName(resultSet.getString(FIRST_NAME_PARAM));
        customer.setSurname(resultSet.getString(SURNAME_PARAM));
        customer.setEmail(resultSet.getString(EMAIL_PARAM));
        customer.setPassword(resultSet.getString(PASSWORD_PARAM));
        customer.setCity(resultSet.getString(CITY_PARAM));
        customer.setAddress(resultSet.getString(ADDRESS_PARAM));
        customer.setIin(resultSet.getString(IIN_PARAM));
    }

    @Override
    public Customer getById(Long id) {

        Customer customer = new Customer();
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CUSTOMER_BY_ID)) {

            preparedStatement.setLong(ID_PARAM_INDEX, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            getCustomer(customer, resultSet);
            SetFields(resultSet, customer);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return customer;

    }

    private Customer getCustomer(Customer customer, ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            customer = new Customer();
            SetFields(resultSet, customer);
        }
        return customer;
    }


    @Override
    public void update(long id, Customer customer) {
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMERS)) {
            preparedStatement.setLong(UPDATE_ID_PARAM_INDEX, id);
            preparedStatement.setString(UPDATE_FIRST_NAME_PARAM_INDEX, customer.getFirstName());
            preparedStatement.setString(UPDATE_SURNAME_PARAM_INDEX, customer.getSurname());
            preparedStatement.setString(UPDATE_EMAIL_PARAM_INDEX, customer.getEmail());
            preparedStatement.setString(UPDATE_PASSWORD_PARAM_INDEX, customer.getPassword());
            preparedStatement.setString(UPDATE_CITY_PARAM_INDEX, customer.getCity());
            preparedStatement.setString(UPDATE_ADDRESS_PARAM_INDEX, customer.getAddress());
            preparedStatement.setString(UPDATE_IIN_PARAM_INDEX, customer.getIin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void remove(Customer customer) {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_BY_ID)) {
            preparedStatement.setLong(ID_PARAM_INDEX, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void removeOneById(long id) {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_BY_ID)) {

            preparedStatement.setLong(ID_PARAM_INDEX, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


    public Customer getCustomerByEmailAndPassword(String email, String password) {

        Customer customer = null;

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CUSTOMER_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(GET_CUSTOMER_BY_EMAIL_AND_PASSWORD_EMAIL_PARAM_INDEX, email);
            preparedStatement.setString(GET_CUSTOMER_BY_EMAIL_AND_PASSWORD_PASSWORD_PARAM_INDEX, password);
            return getCustomer(customer, preparedStatement.executeQuery());

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return customer;
    }

}
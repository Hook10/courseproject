package dao.impl;

import encoder.HashFunction;
import connectionpool.DBUtil;
import dao.BaseDAO;
import entity.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements BaseDAO<Admin> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDaoImpl.class);
    private static final String ADD_ADMIN = "INSERT INTO ADMIN(ID, LOGIN, PASSWORD, SUPPLIER_ID, EMAIL, COMPANY_NAME) " +
            "VALUES(?,?,?,?,?,?)";
    private static final String GET_ALL_ADMINS = "SELECT ID, LOGIN, PASSWORD, SUPPLIER_ID, EMAIL, COMPANY_NAME FROM ADMIN";
    private static final String GET_ADMIN_BY_ID = "SELECT ID, LOGIN, PASSWORD, SUPPLIER_ID, EMAIL, COMPANY_NAME FROM " +
            "ADMIN WHERE ID = ?";
    private static final String UPDATE_ADMIN = "UPDATE ADMIN SET LOGIN=?, PASSWORD=?, SUPPLIER_ID=?, EMAIL=?, " +
            "COMPANY_NAME=? WHERE ID = ?";
    private static final String DELETE_ADMIN_BY_ID = "DELETE FROM ADMIN WHERE ID = ?";
    private static final String GET_ADMIN_BY_LOGIN_AND_PASSWORD = "select * from ADMIN where login = ? and password = ? ";

    private static final int ID_PARAM_INDEX = 1;
    private static final int LOGIN_PARAM_INDEX = 2;
    private static final int PASSWORD_PARAM_INDEX = 3;
    private static final int SUPPLIER_ID_PARAM_INDEX = 4;
    private static final int EMAIL_PARAM_INDEX = 5;
    private static final int COMPANY_NAME_PARAM_INDEX = 6;

    private static final int UPDATE_ID_PARAM_INDEX = 6;
    private static final int UPDATE_LOGIN_PARAM_INDEX = 1;
    private static final int UPDATE_PASSWORD_PARAM_INDEX = 2;
    private static final int UPDATE_SUPPLIER_ID_PARAM_INDEX = 3;
    private static final int UPDATE_EMAIL_PARAM_INDEX = 4;
    private static final int UPDATE_COMPANY_NAME_PARAM_INDEX = 5;

    private static final int GET_ADMIN_BY_LOGIN_AND_PASSWORD_LOGIN_PARAM_INDEX = 1;
    private static final int GET_ADMIN_BY_LOGIN_AND_PASSWORD_PASSWORD_PARAM_INDEX = 2;

    private static final String ID_PARAM = "ID";
    private static final String LOGIN_PARAM = "LOGIN";
    private static final String PASSWORD_PARAM = "PASSWORD";
    private static final String SUPPLIER_ID_PARAM = "SUPPLIER_ID";
    private static final String EMAIL_PARAM = "EMAIL";
    private static final String COMPANY_NAME_PARAM = "COMPANY_NAME";


    private HashFunction hashPassword = new HashFunction();

    @Override
    public void add(Admin admin) {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_ADMIN)) {

            preparedStatement.setLong(ID_PARAM_INDEX, admin.getId());
            preparedStatement.setString(LOGIN_PARAM_INDEX, admin.getLogin());
            preparedStatement.setString(PASSWORD_PARAM_INDEX, hashPassword.getHashFunction(admin.getPassword()));
            preparedStatement.setLong(SUPPLIER_ID_PARAM_INDEX, admin.getSupplierId());
            preparedStatement.setString(EMAIL_PARAM_INDEX, admin.getEmail());
            preparedStatement.setString(COMPANY_NAME_PARAM_INDEX, admin.getCompanyName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Override
    public List<Admin> getAll() {
        List<Admin> adminList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_ADMINS);

            while (resultSet.next()) {
                Admin admin = new Admin();
                getAdmin(admin, resultSet);

                adminList.add(admin);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return adminList;
    }

    @Override
    public Admin getById(Long id) {

        Admin admin = new Admin();
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ADMIN_BY_ID)) {
            preparedStatement.setLong(ID_PARAM_INDEX, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            getAdmin(admin, resultSet);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return admin;
    }


    private Admin getAdmin(Admin admin, ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            admin = new Admin();
            admin.setId(resultSet.getLong(ID_PARAM));
            admin.setLogin(resultSet.getString(LOGIN_PARAM));
            admin.setPassword(resultSet.getString(PASSWORD_PARAM));
            admin.setSupplierId(resultSet.getLong(SUPPLIER_ID_PARAM));
            admin.setEmail(resultSet.getString(EMAIL_PARAM));
            admin.setCompanyName(resultSet.getString(COMPANY_NAME_PARAM));
        }
        return admin;
    }

    @Override
    public void update(long id, Admin admin) {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADMIN)) {
            preparedStatement.setLong(UPDATE_ID_PARAM_INDEX, id);
            preparedStatement.setString(UPDATE_LOGIN_PARAM_INDEX, admin.getLogin());
            preparedStatement.setString(UPDATE_PASSWORD_PARAM_INDEX, admin.getPassword());
            preparedStatement.setLong(UPDATE_SUPPLIER_ID_PARAM_INDEX, admin.getSupplierId());
            preparedStatement.setString(UPDATE_EMAIL_PARAM_INDEX, admin.getEmail());
            preparedStatement.setString(UPDATE_COMPANY_NAME_PARAM_INDEX, admin.getCompanyName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


    public Admin getAdminByLoginAndPassword(String login, String password) {

        Admin admin = null;

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ADMIN_BY_LOGIN_AND_PASSWORD)) {
            preparedStatement.setString(GET_ADMIN_BY_LOGIN_AND_PASSWORD_LOGIN_PARAM_INDEX, login);
            preparedStatement.setString(GET_ADMIN_BY_LOGIN_AND_PASSWORD_PASSWORD_PARAM_INDEX, password);
            return getAdmin(admin, preparedStatement.executeQuery());

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return admin;
    }

    @Override
    public void remove(Admin admin) {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADMIN_BY_ID)) {

            preparedStatement.setLong(ID_PARAM_INDEX, admin.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}


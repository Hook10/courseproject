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

    private HashFunction hashPassword = new HashFunction();



    @Override
    public void add(Admin admin) throws SQLException {


        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_ADMIN)) {

            preparedStatement.setLong(1, admin.getId());
            preparedStatement.setString(2, admin.getLogin());
            preparedStatement.setString(3, hashPassword.getHashFunction(admin.getPassword()));
            preparedStatement.setLong(4, admin.getSupplier_id());
            preparedStatement.setString(5, admin.getEmail());
            preparedStatement.setString(6, admin.getCompanyName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }

    }

    @Override
    public List<Admin> getAll() throws SQLException {
        List<Admin> adminList = new ArrayList<>();

        try ( Connection connection = DBUtil.getDataSource().getConnection();
              Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_ADMINS);

            while (resultSet.next()) {
                Admin admin = new Admin();
                getAdmin(admin, resultSet);

                adminList.add(admin);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return adminList;
    }

    @Override
    public Admin getById(Long id) throws SQLException {

        Admin admin = new Admin();
        try(Connection connection = DBUtil.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ADMIN_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            getAdmin(admin, resultSet);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return admin;
    }

    private Admin getAdmin(Admin admin, ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            admin = new Admin();
            admin.setId(resultSet.getLong("ID"));
            admin.setLogin(resultSet.getString("LOGIN"));
            admin.setPassword(resultSet.getString("PASSWORD"));
            admin.setSupplier_id(resultSet.getLong("SUPPLIER_ID"));
            admin.setEmail(resultSet.getString("EMAIL"));
            admin.setCompanyName(resultSet.getString("COMPANY_NAME"));
        }
        return admin;
    }

    @Override
    public void update(long id, Admin admin) throws SQLException {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADMIN)) {
            preparedStatement.setString(2, admin.getLogin());
            preparedStatement.setString(3, admin.getPassword());
            preparedStatement.setLong(4, admin.getSupplier_id());
            preparedStatement.setString(5, admin.getEmail());
            preparedStatement.setString(6, admin.getCompanyName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
    public Admin getAdminByLoginAndPassword(String login, String password)  {

        Admin admin = null;

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ADMIN_BY_LOGIN_AND_PASSWORD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            return getAdmin(admin, preparedStatement.executeQuery());

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public void remove(Admin admin) throws SQLException {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADMIN_BY_ID)) {

            preparedStatement.setLong(1, admin.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}


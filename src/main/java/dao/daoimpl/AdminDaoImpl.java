package dao.daoimpl;

import connectionpool.DBUtil;
import dao.BaseDAO;
import entity.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements BaseDAO<Admin> {



    @Override
    public void add(Admin admin) throws SQLException {

        String sql = "INSERT INTO ADMIN(ID, LOGIN, PASSWORD, SUPPLIER_ID, EMAIL, COMPANY_NAME) " +
                "VALUES(?,?,?,?,?,?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, admin.getId());
            preparedStatement.setString(2, admin.getLogin());
            preparedStatement.setString(3, admin.getPassword());
            preparedStatement.setLong(4, admin.getSupplier_id());
            preparedStatement.setString(5, admin.getEmail());
            preparedStatement.setString(6, admin.getCompanyName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Admin> getAll() throws SQLException {
        List<Admin> adminList = new ArrayList<>();

        String sql = "SELECT ID, LOGIN, PASSWORD, SUPPLIER_ID, EMAIL, COMPANY_NAME FROM ADMIN";
        try ( Connection connection = DBUtil.getDataSource().getConnection();
              Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getLong("ID"));
                admin.setLogin(resultSet.getString("LOGIN"));
                admin.setPassword(resultSet.getString("PASSWORD"));
                admin.setSupplier_id(resultSet.getInt("SUPPLIER_ID"));
                admin.setEmail(resultSet.getString("EMAIL"));
                admin.setCompanyName(resultSet.getString("COMPANY_NAME"));

                adminList.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }

    @Override
    public Admin getById(Long id) throws SQLException {

        String sql = "SELECT ID, LOGIN, PASSWORD, SUPPLIER_ID, EMAIL, COMPANY_NAME FROM ADMIN WHERE ID = ?";
        Admin admin = new Admin();
        try(Connection connection = DBUtil.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            admin.setId(resultSet.getLong("ID"));
            admin.setLogin(resultSet.getString("LOGIN"));
            admin.setPassword(resultSet.getString("PASSWORD"));
            admin.setSupplier_id(resultSet.getInt("SUPPLIER_ID"));
            admin.setEmail(resultSet.getString("EMAIL"));
            admin.setCompanyName(resultSet.getString("COMPANY_NAME"));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public void update(Admin admin) throws SQLException {

        String sql = "UPDATE ADMIN SET LOGIN=?, PASSWORD=?, SUPPLIER_ID=?, EMAIL=?, COMPANY_NAME=? WHERE ID = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(2, admin.getLogin());
            preparedStatement.setString(3, admin.getPassword());
            preparedStatement.setInt(4, admin.getSupplier_id());
            preparedStatement.setString(5, admin.getEmail());
            preparedStatement.setString(6, admin.getCompanyName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Admin admin) throws SQLException {

        String sql = "DELETE FROM ADMIN WHERE ID = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, admin.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


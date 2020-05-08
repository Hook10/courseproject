package dao.daoimpl;

import connectionpool.DBUtil;
import dao.BaseDAO;
import entity.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements BaseDAO<Supplier> {

    @Override
    public void add(Supplier supplier) throws SQLException {

        String sql = "INSERT INTO SUPPLIERS(ID, NAME, BIN)  VALUES(?,?,?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, supplier.getId());
            preparedStatement.setString(2, supplier.getCompanyName());
            preparedStatement.setString(3, supplier.getBin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        List<Supplier> supplierList = new ArrayList<>();

        String sql  = "SELECT ID, NAME, BIN FROM SUPPLIERS";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(resultSet.getLong("ID"));
                supplier.setCompanyName(resultSet.getString("NAME"));
                supplier.setBin(resultSet.getString("BIN"));

                supplierList.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplierList;
    }

    @Override
    public Supplier getById(Long id) throws SQLException {

        String sql = "SELECT ID, NAME, BIN FROM SUPPLIERS WHERE ID=?";
        Supplier supplier = new Supplier();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            supplier.setId(resultSet.getLong("ID"));
            supplier.setCompanyName(resultSet.getString("NAME"));
            supplier.setBin(resultSet.getString("BIN"));

            preparedStatement.executeUpdate();
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return supplier;
    }

    @Override
    public void update(Supplier supplier) throws SQLException {

        String sql = "UPDATE SUPPLIERS SET NAME=?, BIN=? WHERE ID=?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, supplier.getCompanyName());
            preparedStatement.setString(2, supplier.getBin());
            preparedStatement.setLong(3, supplier.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Supplier supplier) throws SQLException {

        String sql = "DELETE FROM SUPPLIERS WHERE ID = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, supplier.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package dao.daoimpl;

import connectionpool.DBUtil;
import dao.BaseDAO;
import entity.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements BaseDAO<Supplier> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierDaoImpl.class);
    private static final String ADD_SUPPLIER = "INSERT INTO SUPPLIERS(ID, NAME, BIN)  VALUES(?,?,?)";
    private static final String GET_ALL_SUPPLIERS = "SELECT ID, NAME, BIN FROM SUPPLIERS";
    private static final String GET_SUPPLIER_BY_ID = "SELECT ID, NAME, BIN FROM SUPPLIERS WHERE ID=?";
    private static final String UPDATE_SUPPLIER = "UPDATE SUPPLIERS SET NAME=?, BIN=? WHERE ID=?";
    private static final String DELETE_SUPPLIER_BY_ID = "DELETE FROM SUPPLIERS WHERE ID = ?";

    @Override
    public void add(Supplier supplier) throws SQLException {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_SUPPLIER)) {

            preparedStatement.setLong(1, supplier.getId());
            preparedStatement.setString(2, supplier.getCompanyName());
            preparedStatement.setString(3, supplier.getBin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        List<Supplier> supplierList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_SUPPLIERS);

            while (resultSet.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(resultSet.getLong("ID"));
                supplier.setCompanyName(resultSet.getString("NAME"));
                supplier.setBin(resultSet.getString("BIN"));

                supplierList.add(supplier);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return supplierList;
    }

    @Override
    public Supplier getById(Long id) throws SQLException {

        Supplier supplier = new Supplier();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_SUPPLIER_BY_ID)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            supplier.setId(resultSet.getLong("ID"));
            supplier.setCompanyName(resultSet.getString("NAME"));
            supplier.setBin(resultSet.getString("BIN"));

            preparedStatement.executeUpdate();
        } catch (SQLException e ) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return supplier;
    }

    @Override
    public void update(long id, Supplier supplier) throws SQLException {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SUPPLIER)) {
            preparedStatement.setString(1, supplier.getCompanyName());
            preparedStatement.setString(2, supplier.getBin());
            preparedStatement.setLong(3, supplier.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Supplier supplier) throws SQLException {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUPPLIER_BY_ID)) {
            preparedStatement.setLong(1, supplier.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}

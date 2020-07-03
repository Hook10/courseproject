package dao.impl;

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

    private static final String ID_PARAM = "ID";
    private static final String COMPANY_NAME_PARAM = "NAME";
    private static final String BIN_PARAM = "BIN";

    private static final int ID_PARAM_INDEX = 1;
    private static final int COMPANY_NAME_PARAM_INDEX = 2;
    private static final int BIN_PARAM_INDEX = 3;

    private static final int UPDATE_ID_PARAM_INDEX = 3;
    private static final int UPDATE_COMPANY_NAME_PARAM = 1;
    private static final int UPDATE_BIN_PARAM = 2;

    @Override
    public void add(Supplier supplier)  {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_SUPPLIER)) {

            preparedStatement.setLong(ID_PARAM_INDEX, supplier.getId());
            preparedStatement.setString(COMPANY_NAME_PARAM_INDEX, supplier.getCompanyName());
            preparedStatement.setString(BIN_PARAM_INDEX, supplier.getBin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> supplierList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_SUPPLIERS);

            while (resultSet.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(resultSet.getLong(ID_PARAM));
                supplier.setCompanyName(resultSet.getString(COMPANY_NAME_PARAM));
                supplier.setBin(resultSet.getString(BIN_PARAM));

                supplierList.add(supplier);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return supplierList;
    }

    @Override
    public Supplier getById(Long id)  {

        Supplier supplier = new Supplier();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_SUPPLIER_BY_ID)) {

            preparedStatement.setLong(ID_PARAM_INDEX, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            supplier.setId(resultSet.getLong(ID_PARAM));
            supplier.setCompanyName(resultSet.getString(COMPANY_NAME_PARAM));
            supplier.setBin(resultSet.getString(BIN_PARAM));

            preparedStatement.executeUpdate();
        } catch (SQLException e ) {
            LOGGER.error(e.getMessage(), e);
        }
        return supplier;
    }


    @Override
    public void update(long id, Supplier supplier)  {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SUPPLIER)) {
            preparedStatement.setLong(UPDATE_ID_PARAM_INDEX, id);
            preparedStatement.setString(UPDATE_COMPANY_NAME_PARAM, supplier.getCompanyName());
            preparedStatement.setString(UPDATE_BIN_PARAM, supplier.getBin());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void remove(Supplier supplier)  {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUPPLIER_BY_ID)) {
            preparedStatement.setLong(ID_PARAM_INDEX, supplier.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    public void removeOneById(long id) {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUPPLIER_BY_ID)) {

            preparedStatement.setLong(ID_PARAM_INDEX, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}

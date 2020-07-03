package dao.impl;

import connectionpool.DBUtil;
import dao.BaseDAO;
import entity.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataDaoImpl implements BaseDAO<Data> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataDaoImpl.class);
    private static final String ADD_DATA = "INSERT INTO data_table(ID, MONTH, DATA, ID_CUSTOMER, ID_SUPPLIER) " +
            "VALUES (?,?,?,?,?)";
    private static final String GET_ALL_DATA = "SELECT ID, MONTH, DATA, ID_CUSTOMER, ID_SUPPLIER FROM DATA_TABLE";
    private static final String GET_DATA_BY_ID = "SELECT ID, MONTH, DATA, ID_CUSTOMER, ID_SUPPLIER FROM DATA_TABLE" +
            " WHERE ID = ?";
    private static final String UPDATE_DATA = "UPDATE DATA_TABLE SET MONTH = ?, DATA = ?, ID_CUSTOMER = ?, ID_SUPPLIER = ?  WHERE ID = ? ";
    private static final String DELETE_DATA_BY_ID = "DELETE FROM DATA_TABLE WHERE ID=?";
    private static final String GET_ALL_DATA_BY_CUSTOMER_ID = "SELECT * FROM DATA_TABLE WHERE ID_CUSTOMER = ? ";
    private static final String GET_ALL_DATA_BY_SUPPLIER_ID = "SELECT * FROM DATA_TABLE WHERE ID_SUPPLIER = ?";
    private static final String GET_ALL_DATA_BY_SUPPLIER_ID_AND_CUSTOMER_ID = "SELECT * FROM DATA_TABLE WHERE ID_SUPPLIER = ? AND ID_CUSTOMER=?";

    private static final String ID_PARAM = "ID";
    private static final String MONTH_PARAM = "MONTH";
    private static final String DATA_PARAM = "DATA";
    private static final String ID_CUSTOMER_PARAM = "ID_CUSTOMER";
    private static final String ID_SUPPLIER_PARAM = "ID_SUPPLIER";

    private static final int ID_PARAM_INDEX = 1;
    private static final int MONTH_PARAM_INDEX = 2;
    private static final int DATA_PARAM_INDEX = 3;
    private static final int ID_CUSTOMER_PARAM_INDEX = 4;
    private static final int ID_SUPPLIER_PARAM_INDEX = 5;

    private static final int UPDATE_ID_PARAM_INDEX = 5;
    private static final int UPDATE_MONTH_PARAM_INDEX = 1;
    private static final int UPDATE_DATA_PARAM_INDEX = 2;
    private static final int UPDATE_ID_CUSTOMER_PARAM_INDEX = 3;
    private static final int UPDATE_ID_SUPPLIER_PARAM_INDEX = 4;

    private static final int GET_ALL_BY_SUPPLIER_ID_AND_CUSTOMER_ID_ID_SUPPLIER = 1;
    private static final int GET_ALL_BY_SUPPLIER_ID_AND_CUSTOMER_ID_ID_CUSTOMER = 2;

    @Override
    public void add(Data data)  {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_DATA)) {

            preparedStatement.setLong(ID_PARAM_INDEX, data.getId());
            preparedStatement.setString(MONTH_PARAM_INDEX, data.getMonth());
            preparedStatement.setLong(DATA_PARAM_INDEX, data.getData());
            preparedStatement.setLong(ID_CUSTOMER_PARAM_INDEX, data.getIdCustomer());
            preparedStatement.setInt(ID_SUPPLIER_PARAM_INDEX, data.getIdSupplier());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Data> getAll()  {
        List<Data> dataList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_DATA)) {

            SetFields(dataList, preparedStatement);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return dataList;
    }

    private void SetFields(List<Data> dataList, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        resultNext(dataList, resultSet);
    }


    private void resultNext(List<Data> dataList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Data data = new Data();
            data.setId(resultSet.getLong(ID_PARAM));
            data.setMonth(resultSet.getString(MONTH_PARAM));
            data.setData(resultSet.getLong(DATA_PARAM));
            data.setIdCustomer(resultSet.getLong(ID_CUSTOMER_PARAM));
            data.setIdSupplier(resultSet.getInt(ID_SUPPLIER_PARAM));

            dataList.add(data);
        }
    }

    @Override
    public Data getById(Long id)  {

        Data data = new Data();
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_DATA_BY_ID)) {

            preparedStatement.setLong(ID_PARAM_INDEX, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            data.setId(resultSet.getLong(ID_PARAM));
            data.setMonth(resultSet.getString(MONTH_PARAM));
            data.setData(resultSet.getLong(DATA_PARAM));
            data.setIdCustomer(resultSet.getLong(ID_CUSTOMER_PARAM));
            data.setIdSupplier(resultSet.getInt(ID_SUPPLIER_PARAM));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return data;
    }


    @Override
    public void update(long id, Data data)  {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DATA)) {
            preparedStatement.setLong(UPDATE_ID_PARAM_INDEX, id);
            preparedStatement.setString(UPDATE_MONTH_PARAM_INDEX, data.getMonth());
            preparedStatement.setLong(UPDATE_DATA_PARAM_INDEX, data.getData());
            preparedStatement.setLong(UPDATE_ID_CUSTOMER_PARAM_INDEX, data.getIdCustomer());
            preparedStatement.setInt(UPDATE_ID_SUPPLIER_PARAM_INDEX, data.getIdSupplier());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


    @Override
    public void remove(Data data)  {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DATA_BY_ID)) {
            preparedStatement.setLong(ID_PARAM_INDEX, data.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void removeOneById(long id) {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DATA_BY_ID)) {

            preparedStatement.setLong(ID_PARAM_INDEX, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


    public List<Data> getAllByCustomerId(long id)  {
        List<Data> dataList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(GET_ALL_DATA_BY_CUSTOMER_ID);
            resultNext(dataList, resultSet);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return dataList;
    }

    public List<Data> getAllBySupplierId(long id)  {
        List<Data> dataList = new ArrayList<>();


        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_DATA_BY_SUPPLIER_ID)) {

            preparedStatement.setLong(ID_PARAM_INDEX, id);

            SetFields(dataList, preparedStatement);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return dataList;
    }


    public List<Data> getAllBySupplierIdAndCustomerId(long id_supplier, long id_customer)  {
        List<Data> dataList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_DATA_BY_SUPPLIER_ID_AND_CUSTOMER_ID)) {

            preparedStatement.setLong(GET_ALL_BY_SUPPLIER_ID_AND_CUSTOMER_ID_ID_SUPPLIER, id_supplier);
            preparedStatement.setLong(GET_ALL_BY_SUPPLIER_ID_AND_CUSTOMER_ID_ID_CUSTOMER, id_customer);

            SetFields(dataList, preparedStatement);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return dataList;
    }
}


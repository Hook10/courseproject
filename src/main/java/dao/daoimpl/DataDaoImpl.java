package dao.daoimpl;

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
    private static final String UPDATE_DATA = "UPDATE DATA_TABLE SET MONTH=?, DATA=?, ID_CUSTOMER=?, ID_SUPPLIER=? " +
            "WHERE ID=?";
    private static final String DELETE_DATA_BY_ID = "DELETE FROM DATA_TABLE WHERE ID=?";
    private static final String GET_ALL_DATA_BY_CUSTOMER_ID = "SELECT * FROM DATA_TABLE WHERE ID_CUSTOMER = ? ";
    private static final String GET_ALL_DATA_BY_SUPPLIER_ID = "SELECT * FROM DATA_TABLE WHERE ID_SUPPLIER = ?";


    @Override
    public void add(Data data) throws SQLException {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_DATA)) {

            preparedStatement.setLong(1, data.getId());
            preparedStatement.setString(2, data.getMonth());
            preparedStatement.setLong(3, data.getData());
            preparedStatement.setLong(4, data.getIdCustomer());
            preparedStatement.setInt(5, data.getIdSupplier());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public List<Data> getAll() throws SQLException {
        List<Data> dataList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_DATA)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Data data = new Data();
                data.setId(resultSet.getLong("ID"));
                data.setMonth(resultSet.getString("MONTH"));
                data.setData(resultSet.getLong("DATA"));
                data.setIdCustomer(resultSet.getLong("ID_CUSTOMER"));
                data.setIdSupplier(resultSet.getInt("ID_SUPPLIER"));

                dataList.add(data);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return dataList;
    }

    @Override
    public Data getById(Long id) throws SQLException {

        Data data = new Data();
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_DATA_BY_ID)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            data.setId(resultSet.getLong("ID"));
            data.setMonth(resultSet.getString("MONTH"));
            data.setData(resultSet.getLong("DATA"));
            data.setIdCustomer(resultSet.getLong("ID_CUSTOMER"));
            data.setIdSupplier(resultSet.getInt("ID_SUPPLIER"));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void update(Data data) throws SQLException {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DATA)) {
            preparedStatement.setString(1, data.getMonth());
            preparedStatement.setLong(2, data.getData());
            preparedStatement.setLong(3, data.getIdCustomer());
            preparedStatement.setInt(4, data.getIdSupplier());
            preparedStatement.setLong(5, data.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Data data) throws SQLException {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DATA_BY_ID)) {
            preparedStatement.setLong(1, data.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public List<Data> getAllByCustomerId(long id) throws SQLException {
        List<Data> dataList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(GET_ALL_DATA_BY_CUSTOMER_ID);
            while (resultSet.next()) {
                Data data = new Data();
                data.setId(resultSet.getLong("ID"));
                data.setMonth(resultSet.getString("MONTH"));
                data.setData(resultSet.getLong("DATA"));
                data.setIdCustomer(resultSet.getLong("ID_CUSTOMER"));
                data.setIdSupplier(resultSet.getInt("ID_SUPPLIER"));

                dataList.add(data);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return dataList;
    }
    public List<Data> getAllBySupplierId(long id) throws SQLException {
        List<Data> dataList = new ArrayList<>();


        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_DATA_BY_SUPPLIER_ID)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Data data = new Data();
                data.setId(resultSet.getLong("ID"));
                data.setMonth(resultSet.getString("MONTH"));
                data.setData(resultSet.getLong("DATA"));
                data.setIdCustomer(resultSet.getLong("ID_CUSTOMER"));
                data.setIdSupplier(resultSet.getInt("ID_SUPPLIER"));

                dataList.add(data);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return dataList;
    }
}


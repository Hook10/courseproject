package dao.daoimpl;

import connectionpool.DBUtil;
import dao.BaseDAO;
import entity.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataDaoImpl implements BaseDAO<Data> {

    @Override
    public void add(Data data) throws SQLException {

        String sql = "INSERT INTO data_table(ID, MONTH, DATA, ID_CUSTOMER, ID_SUPPLIER) VALUES (?,?,?,?,?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, data.getId());
            preparedStatement.setString(2, data.getMonth());
            preparedStatement.setString(3, data.getData());
            preparedStatement.setLong(4, data.getIdCustomer());
            preparedStatement.setInt(5, data.getIdSupplier());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Data> getAll() throws SQLException {
        List<Data> dataList = new ArrayList<>();

        String sql = "SELECT ID, MONTH, DATA, ID_CUSTOMER, ID_SUPPLIER FROM DATA_TABLE";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Data data = new Data();
                data.setId(resultSet.getLong("ID"));
                data.setMonth(resultSet.getString("MONTH"));
                data.setData(resultSet.getString("DATA"));
                data.setIdCustomer(resultSet.getLong("ID_CUSTOMER"));
                data.setIdSupplier(resultSet.getInt("ID_SUPPLIER"));

                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    @Override
    public Data getById(Long id) throws SQLException {

        String sql = "SELECT ID, MONTH, DATA, ID_CUSTOMER, ID_SUPPLIER FROM DATA_TABLE WHERE ID = ?";
        Data data = new Data();
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            data.setId(resultSet.getLong("ID"));
            data.setMonth(resultSet.getString("MONTH"));
            data.setData(resultSet.getString("DATA"));
            data.setIdCustomer(resultSet.getLong("ID_CUSTOMER"));
            data.setIdSupplier(resultSet.getInt("ID_SUPPLIER"));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void update(Data data) throws SQLException {

        String sql = "UPDATE DATA_TABLE SET MONTH=?, DATA=?, ID_CUSTOMER=?, ID_SUPPLIER=? WHERE ID=?";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, data.getMonth());
            preparedStatement.setString(2, data.getData());
            preparedStatement.setLong(3, data.getIdCustomer());
            preparedStatement.setInt(4, data.getIdSupplier());
            preparedStatement.setLong(5, data.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Data data) throws SQLException {

        String sql = "DELETE FROM DATA_TABLE WHERE ID=?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, data.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


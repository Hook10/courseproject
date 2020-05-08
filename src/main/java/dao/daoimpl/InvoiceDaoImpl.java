package dao.daoimpl;

import connectionpool.DBUtil;
import dao.BaseDAO;
import entity.Invoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDaoImpl implements BaseDAO<Invoice> {

    @Override
    public void add(Invoice invoice) throws SQLException {

        String sql = "INSERT INTO INVOICES(id_invoice, ID_SUP, ID_CUST, MONTH, COST) VALUES(?,?,?,?,?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, invoice.getIdInvoice());
            preparedStatement.setLong(2, invoice.getIdSupplier());
            preparedStatement.setLong(3, invoice.getIdCustomer());
            preparedStatement.setString(4, invoice.getMonth());
            preparedStatement.setLong(5, invoice.getCost());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Invoice> getAll() throws SQLException {
        List<Invoice> invoicesList = new ArrayList<>();

        String sql = "SELECT ID_INVOICE, ID_SUP, ID_CUST, MONTH, COST FROM INVOICES";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setIdInvoice(resultSet.getLong("ID"));
                invoice.setIdSupplier(resultSet.getLong("ID_SUP"));
                invoice.setIdCustomer(resultSet.getLong("ID_CUST"));
                invoice.setMonth(resultSet.getString("MONTH"));
                invoice.setCost(resultSet.getLong("COST"));

                invoicesList.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoicesList;
    }

    @Override
    public Invoice getById(Long id) throws SQLException {

        String sql = "SELECT ID_INVOICE, ID_SUP, ID_CUST, MONTH, COST FROM INVOICES WHERE id_invoice=?";

        Invoice invoice = new Invoice();
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            invoice.setIdInvoice(resultSet.getLong("ID_INVOICE"));
            invoice.setIdSupplier(resultSet.getLong("ID_SUP"));
            invoice.setIdCustomer(resultSet.getLong("ID_CUST"));
            invoice.setMonth(resultSet.getString("MONTH"));
            invoice.setCost(resultSet.getLong("COST"));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }

    @Override
    public void update(Invoice invoice) throws SQLException {

        String sql = "UPDATE INVOICES SET ID_SUP=?, ID_CUST=?, MONTH=?, COST=? WHERE ID_INVOICE=?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, invoice.getIdSupplier());
            preparedStatement.setLong(2, invoice.getIdCustomer());
            preparedStatement.setString(3, invoice.getMonth());
            preparedStatement.setLong(4, invoice.getCost());
            preparedStatement.setLong(5, invoice.getIdInvoice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Invoice invoice) throws SQLException {

        String sql = "DELETE FROM INVOICES WHERE id_invoice = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, invoice.getIdInvoice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

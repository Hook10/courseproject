package dao.daoimpl;

import connectionpool.DBUtil;
import dao.BaseDAO;
import entity.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDaoImpl implements BaseDAO<Invoice> {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceDaoImpl.class);
    private static final String ADD_INVOICE = "INSERT INTO INVOICES(id_invoice, ID_SUP, ID_CUST, MONTH, COST) " +
            "VALUES(?,?,?,?,?)";
    private static final String GET_ALL_INVOICES = "SELECT ID_INVOICE, ID_SUP, ID_CUST, MONTH, COST FROM INVOICES";
    private static final String GET_INVOICE_BY_ID = "SELECT ID_INVOICE, ID_SUP, ID_CUST, MONTH, COST FROM INVOICES " +
            "WHERE id_invoice=?";
    private static final String UPDATE_INVOICE = "UPDATE INVOICES SET ID_SUP=?, ID_CUST=?, MONTH=?, COST=? " +
            "WHERE ID_INVOICE=?";
    private static final String DELETE_INVOICE_BY_ID = "DELETE FROM INVOICES WHERE id_invoice = ?";

    @Override
    public void add(Invoice invoice) throws SQLException {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_INVOICE)) {

            preparedStatement.setLong(1, invoice.getIdInvoice());
            preparedStatement.setLong(2, invoice.getIdSupplier());
            preparedStatement.setLong(3, invoice.getIdCustomer());
            preparedStatement.setString(4, invoice.getMonth());
            preparedStatement.setLong(5, invoice.getCost());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public List<Invoice> getAll() throws SQLException {
        List<Invoice> invoicesList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(GET_ALL_INVOICES);
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
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return invoicesList;
    }

    @Override
    public Invoice getById(Long id) throws SQLException {

        Invoice invoice = new Invoice();
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_INVOICE_BY_ID)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            invoice.setIdInvoice(resultSet.getLong("ID_INVOICE"));
            invoice.setIdSupplier(resultSet.getLong("ID_SUP"));
            invoice.setIdCustomer(resultSet.getLong("ID_CUST"));
            invoice.setMonth(resultSet.getString("MONTH"));
            invoice.setCost(resultSet.getLong("COST"));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return invoice;
    }

    @Override
    public void update(long id, Invoice invoice) throws SQLException {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVOICE)) {
            preparedStatement.setLong(1, invoice.getIdSupplier());
            preparedStatement.setLong(2, invoice.getIdCustomer());
            preparedStatement.setString(3, invoice.getMonth());
            preparedStatement.setLong(4, invoice.getCost());
            preparedStatement.setLong(5, invoice.getIdInvoice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Invoice invoice) throws SQLException {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INVOICE_BY_ID)) {
            preparedStatement.setLong(1, invoice.getIdInvoice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}

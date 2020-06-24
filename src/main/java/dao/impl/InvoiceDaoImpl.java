package dao.impl;

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
    private static final String ADD_INVOICE = "INSERT INTO INVOICES(ID_INVOICE,  ID_DATA,  ID_SUP, ID_CUST, MONTH, DATA, COST) " +
            "VALUES(?,?,?,?,?,?,?)";
    private static final String GET_ALL_INVOICES = "SELECT ID_INVOICE, ID_DATA,ID_SUP, ID_CUST, MONTH, DATA, COST FROM INVOICES";
    private static final String GET_INVOICE_BY_ID = "SELECT ID_INVOICE, ID_SUP, ID_CUST, MONTH, DATA, COST FROM INVOICES " +
            " WHERE id_invoice=?";
    private static final String GET_INVOICE_BY_DATA_ID = "SELECT ID_INVOICE,  ID_DATA, ID_SUP, ID_CUST, MONTH, DATA, COST FROM INVOICES " +
            " WHERE id_data=?";
    private static final String UPDATE_INVOICE = "UPDATE INVOICES SET ID_DATA, ID_SUP=?, ID_CUST=?, MONTH=?, DATA=?, COST=? " +
            " WHERE ID_INVOICE=?";
    private static final String DELETE_INVOICE_BY_ID = "DELETE FROM INVOICES WHERE id_invoice = ?";
    private static final String GET_ALL_INVOICES_BY_SUPPLIER_ID_AND_CUSTOMER_ID = "SELECT * FROM invoices WHERE " +
            "ID_SUP = ? AND ID_CUST=?";

    @Override
    public void add(Invoice invoice) throws SQLException {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_INVOICE)) {

            preparedStatement.setLong(1, invoice.getIdInvoice());
            preparedStatement.setLong(2, invoice.getIdData());
            preparedStatement.setLong(3, invoice.getIdSupplier());
            preparedStatement.setLong(4, invoice.getIdCustomer());
            preparedStatement.setString(5, invoice.getMonth());
            preparedStatement.setLong(6, invoice.getData());
            preparedStatement.setLong(7, invoice.getCost());

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
            resultSetNext(invoicesList, resultSet);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return invoicesList;
    }

    private void resultSetNext(List<Invoice> invoicesList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Invoice invoice = new Invoice();
            invoice.setIdInvoice(resultSet.getLong("ID_INVOICE"));
            invoice.setIdData(resultSet.getLong("ID_DATA"));
            invoice.setIdSupplier(resultSet.getLong("ID_SUP"));
            invoice.setIdCustomer(resultSet.getLong("ID_CUST"));
            invoice.setMonth(resultSet.getString("MONTH"));
            invoice.setData(resultSet.getLong("DATA"));
            invoice.setCost(resultSet.getLong("COST"));

            invoicesList.add(invoice);
        }
    }

    @Override
    public Invoice getById(Long id) throws SQLException {

        Invoice invoice = new Invoice();
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_INVOICE_BY_ID)) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            invoice.setIdInvoice(resultSet.getLong("ID_INVOICE"));
            invoice.setIdInvoice(resultSet.getLong("ID_DATA"));
            invoice.setIdSupplier(resultSet.getLong("ID_SUP"));
            invoice.setIdCustomer(resultSet.getLong("ID_CUST"));
            invoice.setMonth(resultSet.getString("MONTH"));
            invoice.setData(resultSet.getLong("DATA"));
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
            preparedStatement.setLong(7, id);
            preparedStatement.setLong(1, invoice.getIdData());
            preparedStatement.setLong(2, invoice.getIdSupplier());
            preparedStatement.setLong(3, invoice.getIdCustomer());
            preparedStatement.setString(4, invoice.getMonth());
            preparedStatement.setLong(5, invoice.getData());
            preparedStatement.setLong(6, invoice.getCost());

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
    public List<Invoice> getAllBySupplierIdAndCustomerId(long id_supplier, long id_customer) throws SQLException {
        List<Invoice> invoicesList = new ArrayList<>();


        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_INVOICES_BY_SUPPLIER_ID_AND_CUSTOMER_ID)) {

            preparedStatement.setLong(1, id_supplier);
            preparedStatement.setLong(2, id_customer);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSetNext(invoicesList, resultSet);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return invoicesList;
    }
    public List<Invoice> getAllByDataId(long id_data) throws SQLException {
        List<Invoice> invoicesList = new ArrayList<>();


        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_INVOICE_BY_DATA_ID)) {

            preparedStatement.setLong(1, id_data);


            ResultSet resultSet = preparedStatement.executeQuery();

            resultSetNext(invoicesList, resultSet);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return invoicesList;
    }
}
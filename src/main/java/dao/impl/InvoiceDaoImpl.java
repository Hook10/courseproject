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

    private static final String ID_INVOICE_PARAM="ID_INVOICE";
    private static final String ID_DATA_PARAM="ID_DATA";
    private static final String ID_SUPPLIER_PARAM="ID_SUP";
    private static final String ID_CUSTOMER_PARAM="ID_CUST";
    private static final String MONTH_PARAM="MONTH";
    private static final String DATA_PARAM="DATA";
    private static final String COST_PARAM="COST";

    private static final int ID_INVOICE_PARAM_INDEX = 1;
    private static final int ID_DATA_PARAM_INDEX = 2;
    private static final int ID_SUPPLIER_PARAM_INDEX = 3;
    private static final int ID_CUSTOMER_PARAM_INDEX = 4;
    private static final int MONTH_PARAM_INDEX = 5;
    private static final int DATA_PARAM_INDEX = 6;
    private static final int COST_PARAM_INDEX = 7;

    private static final int UPDATE_ID_INVOICE_PARAM_INDEX = 7;
    private static final int UPDATE_ID_DATA_PARAM_INDEX = 1;
    private static final int UPDATE_ID_SUPPLIER_PARAM_INDEX = 2;
    private static final int UPDATE_ID_CUSTOMER_PARAM_INDEX = 3;
    private static final int UPDATE_MONTH_PARAM_INDEX = 4;
    private static final int UPDATE_DATA_PARAM_INDEX = 5;
    private static final int UPDATE_COST_PARAM_INDEX = 6;

    private static final int GET_ALL_BY_SUPPLIER_ID_AND_CUSTOMER_ID_ID_SUPPLIER_PARAM_INDEX = 1;
    private static final int GET_ALL_BY_SUPPLIER_ID_AND_CUSTOMER_ID_ID_CUSTOMER_PARAM_INDEX = 2;

    @Override
    public void add(Invoice invoice) {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_INVOICE)) {

            preparedStatement.setLong(ID_INVOICE_PARAM_INDEX, invoice.getIdInvoice());
            preparedStatement.setLong(ID_DATA_PARAM_INDEX, invoice.getIdData());
            preparedStatement.setLong(ID_SUPPLIER_PARAM_INDEX, invoice.getIdSupplier());
            preparedStatement.setLong(ID_CUSTOMER_PARAM_INDEX, invoice.getIdCustomer());
            preparedStatement.setString(MONTH_PARAM_INDEX, invoice.getMonth());
            preparedStatement.setLong(DATA_PARAM_INDEX, invoice.getData());
            preparedStatement.setLong(COST_PARAM_INDEX, invoice.getCost());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Invoice> getAll() {
        List<Invoice> invoicesList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(GET_ALL_INVOICES);
            resultSetNext(invoicesList, resultSet);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return invoicesList;
    }


    private void resultSetNext(List<Invoice> invoicesList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Invoice invoice = new Invoice();
            invoice.setIdInvoice(resultSet.getLong(ID_INVOICE_PARAM));
            invoice.setIdData(resultSet.getLong(ID_DATA_PARAM));
            invoice.setIdSupplier(resultSet.getLong(ID_SUPPLIER_PARAM));
            invoice.setIdCustomer(resultSet.getLong(ID_CUSTOMER_PARAM));
            invoice.setMonth(resultSet.getString(MONTH_PARAM));
            invoice.setData(resultSet.getLong(DATA_PARAM));
            invoice.setCost(resultSet.getLong(COST_PARAM));

            invoicesList.add(invoice);
        }
    }

    @Override
    public Invoice getById(Long id)  {

        Invoice invoice = new Invoice();
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_INVOICE_BY_ID)) {

            preparedStatement.setLong(ID_INVOICE_PARAM_INDEX, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            invoice.setIdInvoice(resultSet.getLong(ID_INVOICE_PARAM));
            invoice.setIdInvoice(resultSet.getLong(ID_DATA_PARAM));
            invoice.setIdSupplier(resultSet.getLong(ID_SUPPLIER_PARAM));
            invoice.setIdCustomer(resultSet.getLong(ID_CUSTOMER_PARAM));
            invoice.setMonth(resultSet.getString(MONTH_PARAM));
            invoice.setData(resultSet.getLong(DATA_PARAM));
            invoice.setCost(resultSet.getLong(COST_PARAM));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return invoice;
    }



    @Override
    public void update(long id, Invoice invoice)  {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVOICE)) {
            preparedStatement.setLong(UPDATE_ID_INVOICE_PARAM_INDEX, id);
            preparedStatement.setLong(UPDATE_ID_DATA_PARAM_INDEX, invoice.getIdData());
            preparedStatement.setLong(UPDATE_ID_SUPPLIER_PARAM_INDEX, invoice.getIdSupplier());
            preparedStatement.setLong(UPDATE_ID_CUSTOMER_PARAM_INDEX, invoice.getIdCustomer());
            preparedStatement.setString(UPDATE_MONTH_PARAM_INDEX, invoice.getMonth());
            preparedStatement.setLong(UPDATE_DATA_PARAM_INDEX, invoice.getData());
            preparedStatement.setLong(UPDATE_COST_PARAM_INDEX, invoice.getCost());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void remove(Invoice invoice) {

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INVOICE_BY_ID)) {
            preparedStatement.setLong(ID_INVOICE_PARAM_INDEX, invoice.getIdInvoice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }



    public List<Invoice> getAllBySupplierIdAndCustomerId(long id_supplier, long id_customer)  {
        List<Invoice> invoicesList = new ArrayList<>();


        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_INVOICES_BY_SUPPLIER_ID_AND_CUSTOMER_ID)) {

            preparedStatement.setLong(GET_ALL_BY_SUPPLIER_ID_AND_CUSTOMER_ID_ID_SUPPLIER_PARAM_INDEX, id_supplier);
            preparedStatement.setLong(GET_ALL_BY_SUPPLIER_ID_AND_CUSTOMER_ID_ID_CUSTOMER_PARAM_INDEX, id_customer);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSetNext(invoicesList, resultSet);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return invoicesList;
    }

    public List<Invoice> getAllByDataId(long id_data) {
        List<Invoice> invoicesList = new ArrayList<>();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_INVOICE_BY_DATA_ID)) {

            preparedStatement.setLong(ID_INVOICE_PARAM_INDEX, id_data);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSetNext(invoicesList, resultSet);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return invoicesList;
    }
}

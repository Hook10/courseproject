package entity;

import java.io.Serializable;
import java.util.Objects;

public class Invoice implements Serializable {
    private long idInvoice;
    private long idData;
    private long idSupplier;
    private long idCustomer;
    private String month;
    private long data;
    private long cost;

    public Invoice(){

    }

    public Invoice(long idInvoice, long idData, long idSupplier, long idCustomer, String month, long data, long cost) {
        this.idInvoice = idInvoice;
        this.idData = idData;
        this.idSupplier = idSupplier;
        this.idCustomer = idCustomer;
        this.month = month;
        this.data = data;
        this.cost = cost;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public long getIdData() {
        return idData;
    }

    public void setIdData(long idData) {
        this.idData = idData;
    }

    public long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(long idInvoice) {
        this.idInvoice = idInvoice;
    }

    public long getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(long idSupplier) {
        this.idSupplier = idSupplier;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return idInvoice == invoice.idInvoice &&
                idData == invoice.idData &&
                idSupplier == invoice.idSupplier &&
                idCustomer == invoice.idCustomer &&
                data == invoice.data &&
                cost == invoice.cost &&
                Objects.equals(month, invoice.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInvoice, idData, idSupplier, idCustomer, month, data, cost);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "idInvoice=" + idInvoice +
                ", idData=" + idData +
                ", idSupplier=" + idSupplier +
                ", idCustomer=" + idCustomer +
                ", month='" + month + '\'' +
                ", data=" + data +
                ", cost=" + cost +
                '}';
    }
}


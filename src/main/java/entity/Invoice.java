package entity;

import java.io.Serializable;
import java.util.Objects;

public class Invoice implements Serializable {
    private long idInvoice;
    private long idSupplier;
    private long idCustomer;
    private String month;
    private long cost;

    public Invoice(){

    }

    public Invoice(int idInvoice, int idSupplier, int idCustomer, String month, long cost) {
        this.idInvoice = idInvoice;
        this.idSupplier = idSupplier;
        this.idCustomer = idCustomer;
        this.month = month;
        this.cost = cost;
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
                idSupplier == invoice.idSupplier &&
                idCustomer == invoice.idCustomer &&
                cost == invoice.cost &&
                month.equals(invoice.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInvoice, idSupplier, idCustomer, month, cost);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "idInvoice=" + idInvoice +
                ", idSupplier=" + idSupplier +
                ", idCustomer=" + idCustomer +
                ", month='" + month + '\'' +
                ", cost=" + cost +
                '}';
    }
}


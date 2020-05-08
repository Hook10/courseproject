package entity;

import java.io.Serializable;
import java.util.Objects;

public class Data implements Serializable {
    private long id;
    private String month;
    private String data;
    private long idCustomer;
    private int idSupplier;

    public Data(){

    }

    public Data(long id, String month, String data, long idCustomer, int idSupplier) {
        this.id = id;
        this.month = month;
        this.data = data;
        this.idCustomer = idCustomer;
        this.idSupplier = idSupplier;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data1 = (Data) o;
        return id == data1.id &&
                idCustomer == data1.idCustomer &&
                idSupplier == data1.idSupplier &&
                month.equals(data1.month) &&
                data.equals(data1.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, month, data, idCustomer, idSupplier);
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", month='" + month + '\'' +
                ", data='" + data + '\'' +
                ", idCustomer=" + idCustomer +
                ", idSupplier=" + idSupplier +
                '}';
    }
}

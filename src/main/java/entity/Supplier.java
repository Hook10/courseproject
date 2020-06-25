package entity;

import java.io.Serializable;
import java.util.Objects;

public class Supplier implements Serializable {
    private long id;
    private String companyName;
    private String bin;

    public Supplier() {

    }

    public Supplier(int id, String companyName, String bin) {
        this.id = id;
        this.companyName = companyName;
        this.bin = bin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return id == supplier.id &&
                companyName.equals(supplier.companyName) &&
                bin.equals(supplier.bin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, bin);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", bin='" + bin + '\'' +
                '}';
    }
}

package entity;

import java.io.Serializable;
import java.util.Objects;

public class Admin implements Serializable {
    private long id;
    private String login;
    private String password;
    private long supplier_id;
    private String email;
    private String companyName;

    public Admin() {
    }

    public Admin(long id, String login, String password, long supplier_id, String email, String companyName) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.supplier_id = supplier_id;
        this.email = email;
        this.companyName = companyName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(long supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return id == admin.id &&
                supplier_id == admin.supplier_id &&
                login.equals(admin.login) &&
                password.equals(admin.password) &&
                email.equals(admin.email) &&
                companyName.equals(admin.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, supplier_id, email, companyName);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", supplier_id=" + supplier_id +
                ", email='" + email + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}


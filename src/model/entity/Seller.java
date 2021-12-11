package model.entity;

import java.util.Objects;

public class Seller {

    private Long id;
    private String name;
    private String lastname;
    private String email;
    private Double salary;

    public Seller() {
    }

    public Seller(Long id, String name, String lastname, String email, Double salary) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return id.equals(seller.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Seller: " +
                "\n Id: " + id +
                "\n Name: " + name +
                "\n Sobrenome: " + lastname +
                "\n Salario: " + salary;
    }
}

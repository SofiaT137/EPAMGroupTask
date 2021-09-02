package com.epam.jwd.repository.model;

import java.util.Objects;

public class Manager extends Entity<Integer>{

    private String name;
    private String password;
    private String email;

    public Manager(Integer id, String name, String password, String email) {
        super(id);
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return name.equals(manager.name)
                && password.equals(manager.password)
                && email.equals(manager.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, email);
    }

    //Replace String with StringBuilder
    @Override
    public String toString() {
        return "Manager{" +
                "'id='" + super.getId() + "'" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

package com.epam.jwd.repository.model;

import java.util.Objects;

public class User extends Entity<Long> {

    private String name;
    private double balance;
    private int age;
    private String email;
    private String password;

    public User(Long id, String name, double balance, int age, String email, String password) {
        super(id);
        this.name = name;
        this.balance = balance;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(user.balance, balance) == 0
                && age == user.age
                && name.equals(user.name)
                && email.equals(user.email)
                && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, balance, age, email, password);
    }

    //Replace String with StringBuilder
    @Override
    public String toString() {
        return "User{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

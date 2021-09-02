package com.epam.jwd.repository.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User extends Entity<Long> {

    private String name;
    private double balance;
    private int age;
    private String email;
    private List<Ticket> tickets;

    public User(Long id, String name, double balance, int age, String email) {
        super(id);
        this.name = name;
        this.balance = balance;
        this.age = age;
        this.email = email;
        this.tickets = new ArrayList<>();
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(user.balance, balance) == 0
                && age == user.age
                && Objects.equals(name, user.name)
                && Objects.equals(email, user.email)
                && Objects.equals(tickets, user.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, balance, age, email, tickets);
    }

    //Replace String with StringBuilder
    @Override
    public String toString() {
        return "User{" +
                "id=" + super.getId() +
                "name='" + name + '\'' +
                ", balance=" + balance +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", tickets=" + tickets +
                '}';
    }
}

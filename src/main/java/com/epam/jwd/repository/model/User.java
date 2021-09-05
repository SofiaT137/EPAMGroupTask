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
    private boolean isActive;


    public User(Long id, String name, double balance, int age, String email, boolean isActive) {
        super(id);
        this.name = name;
        this.balance = balance;
        this.age = age;
        this.email = email;
        this.tickets = new ArrayList<>();
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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
        return Double.compare(user.balance, balance) == 0 &&
                age == user.age && isActive == user.isActive &&
                Objects.equals(name, user.name) && Objects.equals(email, user.email) &&
                Objects.equals(tickets, user.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, balance, age, email, tickets, isActive);
    }

    //Replace String with StringBuilder

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append(", balance=").append(balance);
        sb.append(", age=").append(age);
        sb.append(", email='").append(email).append('\'');
        sb.append(", tickets=").append(tickets);
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }
}

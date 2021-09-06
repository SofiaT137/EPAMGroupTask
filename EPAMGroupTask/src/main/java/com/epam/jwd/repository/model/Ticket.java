package com.epam.jwd.repository.model;

import java.util.Objects;

public class Ticket extends Entity<Long> {

    private String movieName;
    private double price;
    private boolean availableForKids;

    public Ticket(Long id, String movieName, double price, boolean availableForKids) {
        super(id);
        this.movieName = movieName;
        this.price = price;
        this.availableForKids = availableForKids;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailableForKids() {
        return availableForKids;
    }

    public void setAvailableForKids(boolean availableForKids) {
        this.availableForKids = availableForKids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(ticket.price, price) == 0
                && availableForKids == ticket.availableForKids
                && movieName.equals(ticket.movieName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName, price, availableForKids);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "'id='" + super.getId() + "'" +
                "movieName='" + movieName + '\'' +
                ", price=" + price +
                ", availableForKids=" + availableForKids +
                '}';
    }
}

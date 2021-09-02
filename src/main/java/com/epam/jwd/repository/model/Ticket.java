package com.epam.jwd.repository.model;

import java.util.Objects;

public class Ticket extends Entity<Long>{

    private String movieName;
    private double price;
    private int seat;
    private boolean available;
    private boolean availableForKids;

    public Ticket() {
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

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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
                && seat == ticket.seat
                && available == ticket.available
                && availableForKids == ticket.availableForKids
                && movieName.equals(ticket.movieName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName, price, seat, available, availableForKids);
    }

    //Replace String with StringBuilder
    @Override
    public String toString() {
        return "Ticket{" +
                "'id='" + super.getId() + "'" +
                ",movieName='" + movieName + '\'' +
                ", price=" + price +
                ", seat=" + seat +
                ", available=" + available +
                ", availableForKids=" + availableForKids +
                '}';
    }


}

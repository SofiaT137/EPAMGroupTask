package com.epam.jwd.repository.model;

import java.util.Objects;

public class Ticket extends Entity<Long>{

    private String movieName;
    private double price;
    private int seat;
    private int row;
    private boolean available;
    private boolean availableForKids;

    public Ticket(Long id, String movieName, double price, int seat, int row, boolean available, boolean availableForKids) {
        super(id);
        this.movieName = movieName;
        this.price = price;
        this.seat = seat;
        this.row = row;
        this.available = available;
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

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
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
                && row == ticket.row
                && available == ticket.available
                && availableForKids == ticket.availableForKids
                && movieName.equals(ticket.movieName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName, price, seat, row, available, availableForKids);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "'id='" + super.getId() + "'" +
                "movieName='" + movieName + '\'' +
                ", price=" + price +
                ", seat=" + seat +
                ", row=" + row +
                ", available=" + available +
                ", availableForKids=" + availableForKids +
                '}';
    }
}

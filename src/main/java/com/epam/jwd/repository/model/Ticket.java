package com.epam.jwd.repository.model;

import java.util.Objects;

public class Ticket extends Entity<Long> {

    private String movieName;
    private String movieGenre;
    private double price;
    private boolean available;
    private boolean availableForKids;


    public Ticket(Long id, String movieName, String movieGenre, double price, boolean available, boolean availableForKids) {
        super(id);
        this.movieName = movieName;
        this.movieGenre = movieGenre;
        this.price = price;
        this.available = available;
        this.availableForKids = availableForKids;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
                && available == ticket.available
                && availableForKids == ticket.availableForKids
                && movieName.equals(ticket.movieName)
                && movieGenre.equals(ticket.movieGenre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName, movieGenre, price, available, availableForKids);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "'id='" + super.getId() + "'" +
                "movieName='" + movieName + '\'' +
                ", price=" + price +
                ", genre=" + movieGenre +
                ", available=" + available +
                ", availableForKids=" + availableForKids +
                '}';
    }
}

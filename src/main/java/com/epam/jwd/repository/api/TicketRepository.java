package com.epam.jwd.repository.api;

import com.epam.jwd.repository.model.Entity;
import com.epam.jwd.repository.model.Ticket;

import java.util.List;

public interface TicketRepository<V, T extends Entity<V>> {

    void save(Ticket user);
    T findById(T id);
    T findByPosition(int row, int seat);
    T delete(Ticket ticket);
    List<T> findAllAvailable();
    List<T> findAll();
    List<T> findAllAvailableTicketsForKids();
}

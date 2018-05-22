package com.utn.tssi.tp5.ApiRestVuelos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue
    private long id;
    private Flight flight;
    private Cabin cabin;
    private String date;
    private double totalPrice;

    public Ticket(long id, Flight flight, Cabin cabin) {
        this.id = id;
        this.flight = flight;
        this.cabin = cabin;
        this.date = flight.getDate();
        calculateTotalPrice();
    }

    public Ticket(Flight flight, Cabin cabin) {
        this.flight = flight;
        this.cabin = cabin;
        this.date = flight.getDate();
        calculateTotalPrice();
    }

    public void calculateTotalPrice() {
        this.totalPrice = 0;
        double km = 0;
        int dist = 0;

        km = (this.cabin == null) ? 0 : this.cabin.getPriceKm();
        dist = (this.flight == null || this.flight.getRoute() == null) ? 0 : this.flight.getRoute().getDistance();

        this.totalPrice = km * dist;
    }

    @Override
    public String toString() {
        String to = "", flightString = "null", cabinString = "null";

        if(this.flight != null)
            flightString = this.flight.toString();

        if(this.cabin != null)
            cabinString = this.cabin.toString();

        to = flightString + " - Cabin: " + cabinString + " - Total Price: $" + this.totalPrice + " - Will be: " + this.date;

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;
        return this.id == ticket.getId() && this.flight.equals(ticket.getFlight()) && this.cabin.equals(ticket.getCabin()) && this.date.equals(ticket.getDate()) && this.totalPrice == ticket.getTotalPrice();
    }

    @Override
    public int hashCode() {
        int hash = 17;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.flight == null) ? 0 : this.flight.hashCode());
        hash = 31 * hash + ((this.cabin == null) ? 0 : this.cabin.hashCode());
        hash = 31 * hash + ((this.date == null) ? 0 : this.date.hashCode());
        hash = 31 * hash + (int) this.totalPrice;

        return hash;
    }
}

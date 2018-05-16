package com.utn.tssi.tp5.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class Flight {

    @Id
    @GeneratedValue
    private long id;
    private Route route;
    private Date date;
    private Cabin cabin;
    private double totalPrice;

    public Flight(long id, Route route, Date date, Cabin cabin) {
        this.id = id;
        this.route = route;
        this.date = date;
        this.cabin = cabin;
        calculateTotalPrice();
    }

    public Flight(Route route, Date date, Cabin cabin) {
        this.route = route;
        this.date = date;
        this.cabin = cabin;
        calculateTotalPrice();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void calculateTotalPrice() {
        double totalPrice = 0;
        totalPrice = getCabin().getPriceKm() * getRoute().getDistance();

        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        String to = "" ;
        to = "Flight N°" + getId() + " will be on " + getDate() + " - Price: " + getTotalPrice() + "- Route " + getRoute().toString();

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Flight)) return false;

        Flight flight = (Flight) o;
        return getId() == flight.getId() &&
                flight.getTotalPrice() == getTotalPrice() &&
                getRoute().equals(flight.getRoute()) &&
                getDate().equals(flight.getDate()) &&
                getCabin().equals(flight.getCabin());
    }

    @Override
    public int hashCode() {
        int hash = 17;

        hash = 31 * hash + (int) getId();
        hash = 31 * hash + getRoute().hashCode();
        hash = 31 * hash + getDate().hashCode();
        hash = 31 * hash + getCabin().hashCode();
        hash = 31 * hash + (int) getTotalPrice();

        return hash;
    }
}

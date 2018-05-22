package com.utn.tssi.tp5.ApiRestVuelos.model;

<<<<<<< HEAD
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

=======
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
>>>>>>> gianfrancostabile_Branch
public class Flight {

    @Id
    @GeneratedValue
    private long id;
    private Route route;
<<<<<<< HEAD
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
=======
    private String date;

    public Flight(long id, Route route, String date) {
        this.id = id;
        this.route = route;
        this.date = date;
    }

    public Flight(Route route, String date) {
        this.route = route;
        this.date = date;
>>>>>>> gianfrancostabile_Branch
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        String to = "" ;
        to = "Flight N°" + getId() + " will be on " + getDate() + " - Price: " + getTotalPrice() + "- Route " + getRoute().toString();
=======
        String to = "", routeString = "null";

        if(this.route != null)
            routeString = this.route.toString();

        to = "Flight N°" + this.id + " will be on " + this.date + " - Route " + routeString;
>>>>>>> gianfrancostabile_Branch

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Flight)) return false;

        Flight flight = (Flight) o;
<<<<<<< HEAD
        return getId() == flight.getId() &&
                flight.getTotalPrice() == getTotalPrice() &&
                getRoute().equals(flight.getRoute()) &&
                getDate().equals(flight.getDate()) &&
                getCabin().equals(flight.getCabin());
=======
        return this.id == flight.getId() && this.route.equals(flight.getRoute()) && this.date.equals(flight.getDate());
>>>>>>> gianfrancostabile_Branch
    }

    @Override
    public int hashCode() {
        int hash = 17;

<<<<<<< HEAD
        hash = 31 * hash + (int) getId();
        hash = 31 * hash + getRoute().hashCode();
        hash = 31 * hash + getDate().hashCode();
        hash = 31 * hash + getCabin().hashCode();
        hash = 31 * hash + (int) getTotalPrice();
=======
        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.route == null) ? 0 : this.route.hashCode());
        hash = 31 * hash + ((this.date == null) ? 0 : this.date.hashCode());
>>>>>>> gianfrancostabile_Branch

        return hash;
    }
}

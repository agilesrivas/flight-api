package com.utn.tssi.tp5.ApiRestVuelos.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
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
        this.calculateTotalPrice();
    }

    public Flight(Route route, Date date, Cabin cabin) {
        this.route = route;
        this.date = date;
        this.cabin = cabin;
        this.calculateTotalPrice();
    }

    public void calculateTotalPrice() {
        double totalPrice = 0;
        totalPrice = cabin.getPriceKm() * route.getDistance();

        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        String to = "" ;
        to = "Flight N°" + this.id + " will be on " + this.date + " - Price: " + this.totalPrice + "- Route " + this.route.toString();

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Flight)) return false;

        Flight flight = (Flight) o;
        return this.id == flight.getId() &&
                this.totalPrice == flight.getTotalPrice() &&
                this.route.equals(flight.getRoute()) &&
                this.date.equals(flight.getDate()) &&
                this.cabin.equals(flight.getCabin());
    }

    @Override
    public int hashCode() {
        int hash = 17;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.route == null) ? 0 : this.route.hashCode());
        hash = 31 * hash + ((this.date == null) ? 0 : this.date.hashCode());
        hash = 31 * hash + ((this.cabin == null) ? 0 : this.cabin.hashCode());
        hash = 31 * hash + (int) this.totalPrice;

        return hash;
    }
}

package com.utn.tssi.tp5.Models.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Flights")
public class Flight {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @JoinColumn(name = "id_Route", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;

    @Column(name = "date_Flight")
    private String date;

    public Flight(long id, Route route, String date) {
        this.id = id;
        this.route = route;
        this.date = date;
    }

    public Flight(Route route, String date) {
        this.route = route;
        this.date = date;
    }

    @Override
    public String toString() {
        String to = "", routeString = "null";

        if(this.route != null)
            routeString = this.route.toString();

        to = "Flight N°" + this.id + " will be on " + this.date + " - Route " + routeString;

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Flight)) return false;

        Flight flight = (Flight) o;
        return this.id == flight.getId() && this.route.equals(flight.getRoute()) && this.date.equals(flight.getDate());
    }

    @Override
    public int hashCode() {
        int hash = 17;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.route == null) ? 0 : this.route.hashCode());
        hash = 31 * hash + ((this.date == null) ? 0 : this.date.hashCode());

        return hash;
    }
}

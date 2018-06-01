package com.utn.tssi.tp5.Models.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Tickets")
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @JoinColumn(name = "id_Flight", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Flight flight;

    @JoinColumn(name = "id_Cabin", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Cabin cabin;

    @Column(name = "date_flight")
    private String date;

    @Column(name = "total_price", nullable = false)
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
<<<<<<< HEAD
        return "{" +
=======
<<<<<<< HEAD
        return "Ticket{" +
=======
        return "{" +
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
                "flight=" + flight +
                ", cabin=" + cabin +
                ", date='" + date + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
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
        int hash = 18;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.flight == null) ? 0 : this.flight.hashCode());
        hash = 31 * hash + ((this.cabin == null) ? 0 : this.cabin.hashCode());
        hash = 31 * hash + ((this.date == null) ? 0 : this.date.hashCode());
        hash = 31 * hash + (int) this.totalPrice;

        return hash;
    }
}

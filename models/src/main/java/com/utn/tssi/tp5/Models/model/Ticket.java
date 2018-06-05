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
public class Ticket implements ValidationInterface<Ticket>{

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @JoinColumn(name = "id_Flight", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Flight flight;

    @JoinColumn(name = "id_Cabin", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Price price;

    @JoinColumn(name = "id_User", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name = "date_flight")
    private String date;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    public Ticket(long id, Flight flight, Price price, User user) {
        this.id = id;
        this.flight = flight;
        this.price = price;
        this.date = flight.getDate();
        this.user = user;
        calculateTotalPrice();
    }

    public Ticket(Flight flight, Price price, User user) {
        this.flight = flight;
        this.price = price;
        this.date = flight.getDate();
        this.user = user;
        calculateTotalPrice();
    }

    public void calculateTotalPrice() {
        this.totalPrice = 0;
        double priceKm = 0;
        int dist = 0;

        priceKm = (this.price == null) ? 0 : this.price.getPrice();
        dist = (this.flight == null || this.flight.getRoute() == null) ? 0 : this.flight.getRoute().getDistance();

        this.totalPrice = priceKm * dist;
    }

    @Override
    public String toString() {
        return "{" +
                "flight=" + flight +
                ", price=" + price +
                ", user=" + user +
                ", date='" + date + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;
        return this.id == ticket.getId() && this.flight.equals(ticket.getFlight()) && this.price.equals(ticket.getPrice()) && this.user.equals(ticket.getUser()) && this.date.equals(ticket.getDate()) && this.totalPrice == ticket.getTotalPrice();
    }

    @Override
    public int hashCode() {
        int hash = 18;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.flight == null) ? 0 : this.flight.hashCode());
        hash = 31 * hash + ((this.price == null) ? 0 : this.price.hashCode());
        hash = 31 * hash + ((this.user == null) ? 0 : this.user.hashCode());
        hash = 31 * hash + ((this.date == null) ? 0 : this.date.hashCode());
        hash = 31 * hash + (int) this.totalPrice;

        return hash;
    }

    public boolean validateNullEmpty() {
        boolean bool = true;

        if(id >= 0 && flight != null && !(flight.validateNullEmpty()) && price != null && !(price.validateNullEmpty()) && user != null && !(user.validateNullEmpty()) && date != null && !(date.trim().equals("")) && totalPrice >= 0) {
            bool = false;
        }

        return bool;
    }

    public boolean validateNullEmptyIdentifier() {
        boolean bool = true;

        if(flight != null && !(flight.validateNullEmpty()) && user != null && !(user.validateNullEmpty()) && date != null && !(date.trim().equals(""))) {
            bool = false;
        }

        return bool;
    }
}

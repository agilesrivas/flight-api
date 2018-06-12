package com.utn.WebService.wrapper;

import com.utn.tssi.tp5.Models.model.Ticket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketWrapper{

    private FlightWrapper flightWrapper;
    private PriceWrapper priceWrapper;
    private UserWrapper userWrapper;
    private String date;
    private double totalPriceWrapper;

    public TicketWrapper(Ticket ticket) {
        if(ticket != null) {
            this.flightWrapper = new FlightWrapper(ticket.getFlight());
            this.priceWrapper = new PriceWrapper(ticket.getPrice());
            this.date = ticket.getFlight().getDate();
            this.userWrapper = new UserWrapper(ticket.getUser());
            this.totalPriceWrapper = ticket.getTotalPrice();

        } else {
            this.flightWrapper = new FlightWrapper(null);
            this.priceWrapper = new PriceWrapper(null);
            this.userWrapper = new UserWrapper(null);
        }
    }

    @Override
    public String toString() {
        return "{" +
                "flight=" + flightWrapper +
                ", price=" + priceWrapper +
                ", user=" + userWrapper +
                ", date='" + date + '\'' +
                ", totalPrice=" + totalPriceWrapper +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof TicketWrapper)) return false;

        TicketWrapper ticketWrapper = (TicketWrapper) o;
        return this.flightWrapper.equals(ticketWrapper.getFlightWrapper()) && this.priceWrapper.equals(ticketWrapper.getPriceWrapper()) && this.userWrapper.equals(ticketWrapper.getUserWrapper()) && this.date.equals(ticketWrapper.getDate()) && this.totalPriceWrapper == ticketWrapper.getTotalPriceWrapper();
    }

    @Override
    public int hashCode() {
        int hash = 18;

        hash = 31 * hash + ((this.flightWrapper == null) ? 0 : this.flightWrapper.hashCode());
        hash = 31 * hash + ((this.priceWrapper == null) ? 0 : this.priceWrapper.hashCode());
        hash = 31 * hash + ((this.userWrapper == null) ? 0 : this.userWrapper.hashCode());
        hash = 31 * hash + ((this.date == null) ? 0 : this.date.hashCode());
        hash = 31 * hash + (int) this.totalPriceWrapper;

        return hash;
    }
}

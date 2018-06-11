package com.utn.WebService.wrapper;

import com.utn.tssi.tp5.Models.model.Flight;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightWrapper {

    private RouteWrapper routeWrapper;
    private String date;

    public FlightWrapper(Flight flight) {
        this.routeWrapper = new RouteWrapper(flight.getRoute());
        this.date = flight.getDate();
    }

    @Override
    public String toString() {
        return "{" +
                "routeWrapper=" + routeWrapper +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof FlightWrapper)) return false;

        FlightWrapper flight = (FlightWrapper) o;
        return this.routeWrapper.equals(flight.getRouteWrapper()) && this.date.equals(flight.getDate());
    }

    @Override
    public int hashCode() {
        int hash = 17;

        hash = 31 * hash + ((this.routeWrapper == null) ? 0 : this.routeWrapper.hashCode());
        hash = 31 * hash + ((this.date == null) ? 0 : this.date.hashCode());

        return hash;
    }
}

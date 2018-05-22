package com.utn.tssi.tp5.ApiRestVuelos.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue
    private long id;
    private Airport airportBegin;
    private Airport airportEnd;
    private int distance;
    private int estimatedTime;

    public Route(long id, Airport airportBegin, Airport airportEnd, int distance, int estimatedTime) {
        this.id = id;
        this.airportBegin = airportBegin;
        this.airportEnd = airportEnd;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
    }

    public Route(Airport airportBegin, Airport airportEnd, int distance, int estimatedTime) {
        this.airportBegin = airportBegin;
        this.airportEnd = airportEnd;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
    }

    @Override
    public String toString() {
        String to = "", airportBeginString = "null", airportEndString = "null";

        if(this.airportBegin != null)
            airportBeginString = this.airportBegin.toString();

        if(this.airportEnd != null)
            airportEndString = this.airportEnd.toString();

        to = "{" + airportBeginString + "} to {" + airportEndString + "}";

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Route)) return false;

        Route route = (Route) o;
        return this.id == route.getId() && this.airportBegin.equals(route.getAirportBegin()) && this.airportEnd.equals(route.getAirportEnd()) && this.distance == route.getDistance() && this.estimatedTime == route.getEstimatedTime();
    }

    @Override
    public int hashCode() {
        int hash = 15;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.airportBegin == null) ? 0 : this.airportBegin.hashCode());
        hash = 31 * hash + ((this.airportEnd == null) ? 0 : this.airportEnd.hashCode());
        hash = 31 * hash + this.distance;
        hash = 31 * hash + this.estimatedTime;

        return hash;
    }
}

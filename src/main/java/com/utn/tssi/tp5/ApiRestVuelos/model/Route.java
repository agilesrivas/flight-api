package com.utn.tssi.tp5.ApiRestVuelos.model;

<<<<<<< HEAD
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

=======
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
>>>>>>> gianfrancostabile_Branch
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

<<<<<<< HEAD
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Airport getAirportBegin() {
        return airportBegin;
    }

    public void setAirportBegin(Airport airportBegin) {
        this.airportBegin = airportBegin;
    }

    public Airport getAirportEnd() {
        return airportEnd;
    }

    public void setAirportEnd(Airport airportEnd) {
        this.airportEnd = airportEnd;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    @Override
    public String toString() {
        String to = "";
        to = "{" + getAirportBegin().toString() + "} to {" + getAirportEnd().toString() + "}";
=======
    @Override
    public String toString() {
        String to = "", airportBeginString = "null", airportEndString = "null";

        if(this.airportBegin != null)
            airportBeginString = this.airportBegin.toString();

        if(this.airportEnd != null)
            airportEndString = this.airportEnd.toString();

        to = "{" + airportBeginString + "} to {" + airportEndString + "}";
>>>>>>> gianfrancostabile_Branch

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Route)) return false;

        Route route = (Route) o;
<<<<<<< HEAD
        return getId() == route.getId() &&
                getAirportBegin().equals(route.getAirportBegin()) &&
                getAirportEnd().equals(route.getAirportEnd()) &&
                getDistance() == route.getDistance() &&
                getEstimatedTime() == route.getEstimatedTime();
=======
        return this.id == route.getId() && this.airportBegin.equals(route.getAirportBegin()) && this.airportEnd.equals(route.getAirportEnd()) && this.distance == route.getDistance() && this.estimatedTime == route.getEstimatedTime();
>>>>>>> gianfrancostabile_Branch
    }

    @Override
    public int hashCode() {
        int hash = 15;

<<<<<<< HEAD
        hash = 31 * hash + (int) getId();
        hash = 31 * hash + getAirportBegin().hashCode();
        hash = 31 * hash + getAirportEnd().hashCode();
        hash = 31 * hash + getDistance();
        hash = 31 * hash + getEstimatedTime();
=======
        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.airportBegin == null) ? 0 : this.airportBegin.hashCode());
        hash = 31 * hash + ((this.airportEnd == null) ? 0 : this.airportEnd.hashCode());
        hash = 31 * hash + this.distance;
        hash = 31 * hash + this.estimatedTime;
>>>>>>> gianfrancostabile_Branch

        return hash;
    }
}

package com.utn.tssi.tp5.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Route)) return false;

        Route route = (Route) o;
        return getId() == route.getId() &&
                getAirportBegin().equals(route.getAirportBegin()) &&
                getAirportEnd().equals(route.getAirportEnd()) &&
                getDistance() == route.getDistance() &&
                getEstimatedTime() == route.getEstimatedTime();
    }

    @Override
    public int hashCode() {
        int hash = 15;

        hash = 31 * hash + (int) getId();
        hash = 31 * hash + getAirportBegin().hashCode();
        hash = 31 * hash + getAirportEnd().hashCode();
        hash = 31 * hash + getDistance();
        hash = 31 * hash + getEstimatedTime();

        return hash;
    }
}

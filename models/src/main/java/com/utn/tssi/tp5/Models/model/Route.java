package com.utn.tssi.tp5.Models.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Routes")
@NoArgsConstructor
public class Route implements ValidationInterface<Route>{

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @JoinColumn(name = "id_Airport_Begin", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Airport airportBegin;

    @JoinColumn(name = "id_Airport_End", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Airport airportEnd;

    @Column(name = "distance", nullable = false)
    private int distance;

    @Column(name = "estimated_time", nullable = false)
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
        return "{" +
                "airportBegin=" + airportBegin +
                ", airportEnd=" + airportEnd +
                ", distance=" + distance +
                ", estimatedTime=" + estimatedTime +
                '}';
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

    public boolean validateNullEmpty() {

        boolean bool = true;

        if(id >= 0 && airportBegin != null && !(airportBegin.validateNullEmpty()) && airportEnd != null && !(airportEnd.validateNullEmpty()) && distance >= 0 && estimatedTime >= 0) {
            bool = false;
        }

        return bool;
    }
}

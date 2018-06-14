package com.utn.WebService.wrapper;

import com.utn.tssi.tp5.Models.model.Route;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteWrapper{

    private AirportWrapper airportWrapperBegin;
    private AirportWrapper airportWrapperEnd;
    private int distance;
    private int estimatedTime;

    public RouteWrapper(Route route) {
        if(route != null) {
            this.airportWrapperBegin = new AirportWrapper(route.getAirportBegin());
            this.airportWrapperEnd = new AirportWrapper(route.getAirportEnd());
            this.distance = route.getDistance();
            this.estimatedTime = route.getEstimatedTime();

        }else {
            this.airportWrapperBegin = new AirportWrapper(null);
            this.airportWrapperEnd = new AirportWrapper(null);
        }
    }

    @Override
    public String toString() {
        return "{" +
                "airportBegin=" + airportWrapperBegin +
                ", airportEnd=" + airportWrapperEnd +
                ", distance=" + distance +
                ", estimatedTime=" + estimatedTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof RouteWrapper)) return false;

        RouteWrapper routeWrapper = (RouteWrapper) o;
        return this.airportWrapperBegin.equals(routeWrapper.getAirportWrapperBegin()) && this.airportWrapperEnd.equals(routeWrapper.getAirportWrapperEnd()) && this.distance == routeWrapper.getDistance() && this.estimatedTime == routeWrapper.getEstimatedTime();
    }

    @Override
    public int hashCode() {
        int hash = 15;

        hash = 31 * hash + ((this.airportWrapperBegin == null) ? 0 : this.airportWrapperBegin.hashCode());
        hash = 31 * hash + ((this.airportWrapperEnd == null) ? 0 : this.airportWrapperEnd.hashCode());
        hash = 31 * hash + this.distance;
        hash = 31 * hash + this.estimatedTime;

        return hash;
    }
}

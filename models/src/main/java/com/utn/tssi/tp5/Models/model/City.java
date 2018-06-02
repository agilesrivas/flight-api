package com.utn.tssi.tp5.Models.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Cities")
@NoArgsConstructor
public class City implements ValidationInterface<City>{

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name_City", nullable = false, unique = true)
    private String name;

    @Column(name = "iata", nullable = false, unique = true)
    private String iataCode;

    @JoinColumn(name = "id_State", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private State state;

    public City(long id, String name, String iataCode, State state) {
        this.id = id;
        this.name = name;
        this.iataCode = iataCode;
        this.state = state;
    }

    public City(String name, String iataCode, State state) {
        this.name = name;
        this.iataCode = iataCode;
        this.state = state;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", iataCode='" + iataCode + '\'' +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof City)) return false;

        City city = (City) o;
        return this.id == city.getId() && this.name.equals(city.getName()) && this.iataCode.equals(city.getIataCode()) && this.state.equals(city.getState());
    }

    @Override
    public int hashCode() {
        int hash = 13;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.name == null) ? 0 : this.name.hashCode());
        hash = 31 * hash + ((this.iataCode == null) ? 0 : this.iataCode.hashCode());
        hash = 31 * hash + ((this.state == null) ? 0 : this.state.hashCode());

        return hash;
    }

    public boolean validateNullEmpty() {

        boolean bool = true;

        if(id >= 0 && name != null && !(name.trim().equals("")) && iataCode != null && !(iataCode.trim().equals("")) && state != null && !(state.validateNullEmpty())) {
            bool = false;
        }

        return bool;
    }
}

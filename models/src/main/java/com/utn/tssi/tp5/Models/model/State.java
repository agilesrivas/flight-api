package com.utn.tssi.tp5.Models.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "States")
@NoArgsConstructor
public class State {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name_State", nullable = false, unique = true)
    private String name;

    @Column(name = "iata", nullable = false, unique = true)
    private String iataCode;

    @JoinColumn(name = "id_Country", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    public State(long id, String name, String iataCode, Country country) {
        this.id = id;
        this.name = name;
        this.iataCode = iataCode;
        this.country = country;
    }

    public State(String name, String iataCode, Country country) {

        this.name = name;
        this.iataCode = iataCode;
        this.country = country;
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "State{" +
=======
        return "{" +
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
                "name='" + name + '\'' +
                ", iataCode='" + iataCode + '\'' +
                ", country=" + country +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof State)) return false;

        State state = (State) o;
        return this.id == state.getId() && this.name.equals(state.getName()) && this.iataCode.equals(state.getIataCode()) && this.country.equals(state.getCountry());
    }

    @Override
    public int hashCode() {
        int hash = 12;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.name == null) ? 0 : this.name.hashCode());
        hash = 31 * hash + ((this.iataCode == null) ? 0 : this.iataCode.hashCode());
        hash = 31 * hash + ((this.country == null) ? 0 : this.country.hashCode());

        return hash;
    }
}

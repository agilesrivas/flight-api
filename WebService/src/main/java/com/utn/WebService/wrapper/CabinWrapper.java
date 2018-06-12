package com.utn.WebService.wrapper;

import com.utn.tssi.tp5.Models.model.Cabin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class CabinWrapper{

    private String name;

    public CabinWrapper(Cabin cabin) {
        if(cabin != null) {
            this.name = cabin.getName();
        }
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof CabinWrapper)) return false;
        CabinWrapper cabinWrapper = (CabinWrapper) o;
        return this.name.equals(cabinWrapper.getName());
    }

    @Override
    public int hashCode() {
        int hash = 16;

        hash = 31 * hash + ((this.name == null) ? 0 : this.name.hashCode());

        return hash;
    }
}

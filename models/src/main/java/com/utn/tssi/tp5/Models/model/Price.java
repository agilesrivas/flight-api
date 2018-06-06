package com.utn.tssi.tp5.Models.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Prices")
@NoArgsConstructor
public class Price implements ValidationInterface<Price>{

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "from_Date", nullable = false)
    private String from_Date;

    @Column(name = "to_Date")
    private String to_Date;

    @Column(name = "state_bool", nullable = false)
    private boolean state_bool;

    @JoinColumn(name = "id_Cabin", nullable = false)
    @OneToOne(fetch = FetchType.EAGER)
    private Cabin cabin;

    public Price(long id, float price, String fromDate, String toDate, boolean state_bool, Cabin cabin) {
        this.id = id;
        this.price = price;
        this.from_Date = fromDate;
        this.to_Date = toDate;
        this.state_bool = state_bool;
        this.cabin = cabin;
    }

    public Price(float price, String fromDate, String toDate, boolean state_bool, Cabin cabin) {
        this.price = price;
        this.from_Date = fromDate;
        this.to_Date = toDate;
        this.state_bool = state_bool;
        this.cabin = cabin;
    }

    @Override
    public String toString() {
        return "{" +
                "price=" + price +
                ", fromDate='" + from_Date + '\'' +
                ", toDate='" + to_Date + '\'' +
                ", state_bool=" + state_bool +
                ", cabin=" + cabin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Price)) return false;

        Price price= (Price) o;
        return this.id == price.getId() && this.price == price.getPrice() && this.from_Date.equals(price.getFrom_Date()) && this.to_Date.equals(price.getTo_Date()) && this.state_bool == price.isState_bool() && this.cabin.equals(price.getCabin());
    }

    @Override
    public int hashCode() {
        int hash = 15;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + (int) this.price;
        hash = 31 * hash + ((this.cabin == null) ? 0 : this.cabin.hashCode());
        hash = 31 * hash + ((this.from_Date == null) ? 0 : this.from_Date.hashCode());
        hash = 31 * hash + ((this.to_Date == null) ? 0 : this.to_Date.hashCode());

        return hash;
    }

    public boolean validateNullEmpty() {
        boolean bool = true;

        if(id >= 0 && price >= 0 && cabin != null && !(cabin.validateNullEmpty()) && from_Date != null && !(from_Date.trim().equals(""))) {
            bool = false;
        }

        return bool;
    }

    public boolean validateNullEmptyIdentifier() {
        boolean bool = true;

        if(cabin != null && !(cabin.validateNullEmptyIdentifier())) {
            bool = false;
        }

        return bool;
    }
}

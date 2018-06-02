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

    @Column(name = "fromDate", nullable = false)
    private String fromDate;

    @Column(name = "toDate")
    private String toDate;

    @Column(name = "state_bool", nullable = false)
    private boolean state_bool;

    @JoinColumn(name = "id_Cabin", nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Cabin cabin;

    public Price(long id, float price, String fromDate, String toDate, boolean state_bool, Cabin cabin) {
        this.id = id;
        this.price = price;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.state_bool = state_bool;
        this.cabin = cabin;
    }

    public Price(float price, String fromDate, String toDate, boolean state_bool, Cabin cabin) {
        this.price = price;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.state_bool = state_bool;
        this.cabin = cabin;
    }

    @Override
    public String toString() {
        return "{" +
                "price=" + price +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", state_bool=" + state_bool +
                ", cabin=" + cabin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Price)) return false;

        Price price= (Price) o;
        return this.id == price.getId() && this.price == price.getPrice() && this.fromDate.equals(price.getFromDate()) && this.toDate.equals(price.getToDate()) && this.state_bool == price.isState_bool() && this.cabin.equals(price.getCabin());
    }

    @Override
    public int hashCode() {
        int hash = 15;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + (int) this.price;
        hash = 31 * hash + ((this.cabin == null) ? 0 : this.cabin.hashCode());
        hash = 31 * hash + ((this.fromDate == null) ? 0 : this.fromDate.hashCode());
        hash = 31 * hash + ((this.toDate == null) ? 0 : this.toDate.hashCode());

        return hash;
    }

    public boolean validateNullEmpty() {
        boolean bool = true;

        if(id >= 0 && price >= 0 && cabin != null && !(cabin.validateNullEmpty()) && fromDate != null && !(fromDate.trim().equals(""))) {
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

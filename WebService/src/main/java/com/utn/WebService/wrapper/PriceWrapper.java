package com.utn.WebService.wrapper;

import com.utn.tssi.tp5.Models.model.Price;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceWrapper{

    private float price;
    private String from_Date;
    private String to_Date;
    private CabinWrapper cabinWrapper;

    public PriceWrapper(Price price) {
        if(price != null) {
            this.price = price.getPrice();
            this.from_Date = price.getFrom_Date();
            this.to_Date = price.getTo_Date();
            this.cabinWrapper = new CabinWrapper(price.getCabin());

        } else {
            this.cabinWrapper = new CabinWrapper(null);
        }
    }

    @Override
    public String toString() {
        return "{" +
                "price=" + price +
                ", fromDate='" + from_Date + '\'' +
                ", toDate='" + to_Date + '\'' +
                ", cabin=" + cabinWrapper +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof PriceWrapper)) return false;

        PriceWrapper priceWrapper = (PriceWrapper) o;

        return this.price == priceWrapper.getPrice() && this.from_Date.equals(priceWrapper.getFrom_Date()) && this.to_Date.equals(priceWrapper.getTo_Date()) && this.cabinWrapper.equals(priceWrapper.getCabinWrapper());
    }

    @Override
    public int hashCode() {
        int hash = 15;

        hash = 31 * hash + (int) this.price;
        hash = 31 * hash + ((this.cabinWrapper == null) ? 0 : this.cabinWrapper.hashCode());
        hash = 31 * hash + ((this.from_Date == null) ? 0 : this.from_Date.hashCode());
        hash = 31 * hash + ((this.to_Date == null) ? 0 : this.to_Date.hashCode());

        return hash;
    }
}

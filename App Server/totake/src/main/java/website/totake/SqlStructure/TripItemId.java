package website.totake.SqlStructure;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by meni on 04/06/17.
 */
@Embeddable
public class TripItemId implements Serializable{
    private Trip trip;
    private Item item;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public int hashCode() {
        return trip.hashCode()*item.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}

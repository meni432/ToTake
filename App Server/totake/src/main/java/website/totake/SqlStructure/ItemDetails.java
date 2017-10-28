package website.totake.SqlStructure;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by meni on 28/05/17.
 */

@Entity
@Table(name = "trips_items")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.trip",
        joinColumns = @JoinColumn(name = "trip_id")),
        @AssociationOverride(name = "primaryKey.item",
        joinColumns = @JoinColumn(name = "item_id"))
})
public class ItemDetails {
    private TripItemId primaryKey = new TripItemId();

    @Column(name = "amount")
    private int amount;
    @Column(name = "is_done")
    private int isDone;

    @EmbeddedId
    public TripItemId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(TripItemId primaryKey) {
        this.primaryKey = primaryKey;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getIsDone() {
        return isDone;
    }


    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    @Transient
    @JsonIgnore
    public Trip getTrip() {
        return getPrimaryKey().getTrip();
    }

    public void setTrip(Trip trip) {
        getPrimaryKey().setTrip(trip);
    }

    @Transient
//    @JsonIgnore
    public Item getItem() {
        return getPrimaryKey().getItem();
    }

    @Transient
    public String getItemInTripName() {
        return getItem().getItemEHName();
    }

    @Transient
    public long getItemInTripId() {
        return getItem().getItemID();
    }

    public void setItem(Item item) {
        getPrimaryKey().setItem(item);
    }

}

package website.totake.SqlStructure;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

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
public class SqlItemDetails {
    private SqlTripItemId primaryKey = new SqlTripItemId();

    @Column(name = "amount")
    private int amount;
    @Column(name = "is_done")
    private int isDone;

    @EmbeddedId
    public SqlTripItemId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(SqlTripItemId primaryKey) {
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
    public SqlTrip getTrip() {
        return getPrimaryKey().getTrip();
    }

    public void setTrip(SqlTrip trip) {
        getPrimaryKey().setTrip(trip);
    }

    @Transient
    public SqlItem getItem() {
        return getPrimaryKey().getItem();
    }

    public void setItem(SqlItem item) {
        getPrimaryKey().setItem(item);
    }


}

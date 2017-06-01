package website.totake.SqlStructure;

import website.totake.Item;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by meni on 26/05/17.
 */
@Entity
@Table(name = "trips")
public class SqlTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trip_id")
    private long tripId;
    @Column(name = "destination_en_name")
    private String destinationEnName;
    @Column(name = "destination_he_name")
    private String getDestinationHeName;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    @OneToMany
    @JoinTable(name = "trip_items",
            joinColumns = @JoinColumn(name = "trip_id", referencedColumnName = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "item_id", unique = true))
    private List<SqlItem> items;


    public SqlTrip() {
    }

    public void addItem(SqlItem item) {
        if (!items.contains(item)) {
            items.add(item);
            if (item.getOwner() != this) {
                item.setOwner(this);
            }
        }
    }

    public SqlTrip(String destinationEnName, String getDestinationHeName, Date startDate, Date endDate) {
        this.destinationEnName = destinationEnName;
        this.getDestinationHeName = getDestinationHeName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getTripId() {
        return tripId;
    }

    public String getDestinationEnName() {
        return destinationEnName;
    }

    public String getGetDestinationHeName() {
        return getDestinationHeName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public List<SqlItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "SqlTrip{" +
                "tripId=" + tripId +
                ", destinationEnName='" + destinationEnName + '\'' +
                ", getDestinationHeName='" + getDestinationHeName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", items=" + items +
                '}';
    }
}

package website.totake.SqlStructure;

import website.totake.Item;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "primaryKey.trip",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SqlItemDetails> sqlItemDetails = new HashSet<>();


    public Set<SqlItemDetails> getSqlItemDetails() {
        return sqlItemDetails;
    }


    public SqlTrip() {
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


    @Override
    public String toString() {
        return "SqlTrip{" +
                "tripId=" + tripId +
                ", destinationEnName='" + destinationEnName + '\'' +
                ", getDestinationHeName='" + getDestinationHeName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
//                ", items=" + items +
                '}';
    }
}

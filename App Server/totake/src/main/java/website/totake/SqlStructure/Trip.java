package website.totake.SqlStructure;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by meni on 26/05/17.
 */

@Entity
@Table(name = "trips")
public class Trip {
    public static final int TRIP_REGULAR = 0;
    public static final int TRIP_DELETED = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trip_id")
    private long tripId;
    @Column(name = "destination_en_name")
    private String destinationEnName;
    @Column(name = "destination_he_name")
    private String getDestinationHeName;
    @Column(name = "destination_google_id")
    private String destinationGoogleId;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "status", nullable = false)
    private Long status;

    @OneToMany(mappedBy = "primaryKey.trip",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ItemDetails> itemDetails = new HashSet<>();


//    @JsonIgnore
    public Set<ItemDetails> getItemDetails() {
        return itemDetails;
    }


    public Trip() {
        this.setStatus(TRIP_REGULAR);
    }

    public Trip(String destinationEnName, String getDestinationHeName, String googlePlaceId, Date startDate, Date endDate) {
        this();
        this.destinationEnName = destinationEnName;
        this.getDestinationHeName = getDestinationHeName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.destinationGoogleId = googlePlaceId;
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

    public String getDestinationGoogleId() {
        return destinationGoogleId;
    }

    public long getStatus() {
        if (status != null) {
            return status.longValue();
        }
        return -1;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripId=" + tripId +
                ", destinationEnName='" + destinationEnName + '\'' +
                ", getDestinationHeName='" + getDestinationHeName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
//                ", items=" + items +
                '}';
    }
}

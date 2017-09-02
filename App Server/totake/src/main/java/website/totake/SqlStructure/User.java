package website.totake.SqlStructure;

import javax.persistence.*;
import java.util.List;

/**
 * Created by meni on 04/06/17.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "email_address", unique = true)
    private String emailAddress;

    @OneToMany
    @JoinTable(name = "users_trips",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "trip_id", referencedColumnName = "trip_id", unique = true))
    private List<Trip> trips;

    public User() {

    }

    public User(String userName, String emailAddress) {
        this.userName = userName;
        this.emailAddress = emailAddress;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Trip findTripById(long tripId) {
        for (Trip trip: trips) {
            if (trip.getTripId() == tripId) {
                return trip;
            }
        }
        return null;
    }

    @Transient
    public List<Trip> getTrips() {
        return trips;
    }


    public void addTrip(Trip trip) {
        if (trips != null) {
            trips.add(trip);
        }
    }
}

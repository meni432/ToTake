package website.totake.SqlStructure;

import org.apache.catalina.User;
import website.totake.Trip;

import javax.persistence.*;
import java.util.List;

/**
 * Created by meni on 04/06/17.
 */
@Entity
@Table(name = "users")
public class SqlUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_name")
    private String userName;

    @OneToMany
    @JoinTable(name = "users_trips",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "trip_id", referencedColumnName = "trip_id", unique = true))
    private List<SqlTrip> trips;

    public SqlUser() {

    }

    public SqlUser (String userName) {
        this.userName = userName;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public List<SqlTrip> getTrips() {
        return trips;
    }
}

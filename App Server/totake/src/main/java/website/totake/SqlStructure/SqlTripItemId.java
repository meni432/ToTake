package website.totake.SqlStructure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.catalina.User;
import website.totake.Trip;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by meni on 04/06/17.
 */
@Embeddable
public class SqlTripItemId implements Serializable{
    private SqlTrip trip;
    private SqlItem item;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    public SqlTrip getTrip() {
        return trip;
    }

    public void setTrip(SqlTrip trip) {
        this.trip = trip;
    }

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    public SqlItem getItem() {
        return item;
    }

    public void setItem(SqlItem item) {
        this.item = item;
    }
}

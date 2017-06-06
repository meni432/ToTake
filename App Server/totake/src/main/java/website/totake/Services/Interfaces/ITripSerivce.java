package website.totake.Services.Interfaces;

import website.totake.SqlStructure.SqlTrip;

import java.util.Date;

/**
 * Created by meni on 28/05/17.
 */
public interface ITripSerivce {
    public SqlTrip getTrip(long tripId);
    public Iterable<SqlTrip> getAllTrips();
    public SqlTrip addNewTrip(String destinationEnName, String getDestinationHeName, Date startDate, Date endDate);
    public SqlTrip save(SqlTrip sqlTrip);
}

package website.totake.Services.Interfaces;

import website.totake.SqlStructure.Trip;

import java.util.Date;

/**
 * Created by meni on 28/05/17.
 */
public interface ITripSerivce {
    public Trip getTrip(long tripId);
    public Iterable<Trip> getAllTrips();
    public Trip addNewTrip(String destinationEnName, String getDestinationHeName, String destinationGoogleId, Date startDate, Date endDate);
    public Trip save(Trip trip);
}

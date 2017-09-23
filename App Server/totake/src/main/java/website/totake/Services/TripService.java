package website.totake.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import website.totake.Repositories.SqlTripRepository;
import website.totake.Services.Interfaces.ITripSerivce;
import website.totake.SqlStructure.Trip;

import java.util.Date;

/**
 * Created by meni on 28/05/17.
 */
@Service
@Transactional
public class TripService implements ITripSerivce {
    @Autowired
    private SqlTripRepository sqlTripRepository;

    public Trip getTrip(long tripId) {
        Trip trip = sqlTripRepository.findTripByTripId(tripId);
        if (trip != null) {
            if (trip.getStatus() == Trip.TRIP_REGULAR) {
                return trip;
            }
        }

        return null;
    }

    public Iterable<Trip> getAllTrips() {
        return sqlTripRepository.findAll();
    }

    public Trip addNewTrip(String destinationEnName, String getDestinationHeName, String destinationGoogleId, Date startDate, Date endDate) {
        Trip newTrip = new Trip(destinationEnName, getDestinationHeName, destinationGoogleId, startDate, endDate);
        Trip result = sqlTripRepository.save(newTrip);
        return result;
    }

    @Override
    public Trip save(Trip trip) {
        return sqlTripRepository.save(trip);
    }

}

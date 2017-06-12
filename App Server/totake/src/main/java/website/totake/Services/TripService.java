package website.totake.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import website.totake.Repositories.SqlTripRepository;
import website.totake.Services.Interfaces.ITripSerivce;
import website.totake.SqlStructure.SqlTrip;

import java.util.Date;

/**
 * Created by meni on 28/05/17.
 */
@Service
@Transactional
public class TripService  implements ITripSerivce {
    @Autowired
    private SqlTripRepository sqlTripRepository;

    public SqlTrip getTrip(long tripId) {
        SqlTrip trip = sqlTripRepository.findTripByTripId(tripId);
        return trip;
    }

    public Iterable<SqlTrip> getAllTrips() {
        return sqlTripRepository.findAll();
    }

    public SqlTrip addNewTrip(String destinationEnName, String getDestinationHeName, String destinationGoogleId, Date startDate, Date endDate) {
        SqlTrip newTrip = new SqlTrip(destinationEnName, getDestinationHeName, destinationGoogleId, startDate, endDate);
        SqlTrip result = sqlTripRepository.save(newTrip);
        return result;
    }

    @Override
    public SqlTrip save(SqlTrip sqlTrip) {
        return sqlTripRepository.save(sqlTrip);
    }

}

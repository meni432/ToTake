package website.totake.Repositories;

import org.springframework.data.repository.CrudRepository;
import website.totake.SqlStructure.Trip;

/**
 * Created by meni on 26/05/17.
 */
public interface SqlTripRepository  extends CrudRepository<Trip, Long> {
    public Trip findTripByTripId(long tripId);
}

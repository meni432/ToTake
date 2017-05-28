package website.totake.Repositories;

import org.springframework.data.repository.CrudRepository;
import website.totake.SqlStructure.SqlTrip;

/**
 * Created by meni on 26/05/17.
 */
public interface SqlTripRepository  extends CrudRepository<SqlTrip, Long> {
}

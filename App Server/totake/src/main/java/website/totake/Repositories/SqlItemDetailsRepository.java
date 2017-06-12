package website.totake.Repositories;

import org.springframework.data.repository.CrudRepository;
import website.totake.SqlStructure.SqlItem;
import website.totake.SqlStructure.SqlItemDetails;

import java.util.List;

/**
 * NOTICE: DO NOT CHANGE THE FUNCTION NAMES IN THIS REPOSITORY
 */
public interface SqlItemDetailsRepository extends CrudRepository<SqlItemDetails, Long>  {
//    List<SqlItemDetails> removeByTripIdAndItemId(long tripId, long itemId);
//    List<SqlItemDetails> findByTripIdAndItemId(long tripId, long itemId);
}

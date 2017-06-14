package website.totake.Repositories;

import org.springframework.data.repository.CrudRepository;
import website.totake.SqlStructure.ItemDetails;

/**
 * NOTICE: DO NOT CHANGE THE FUNCTION NAMES IN THIS REPOSITORY
 */
public interface SqlItemDetailsRepository extends CrudRepository<ItemDetails, Long>  {
//    List<ItemDetails> removeByTripAndItem(long tripId, long itemId);
//    List<ItemDetails> findByTripIdAndItemId(long tripId, long itemId);
}

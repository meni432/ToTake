package website.totake.Repositories;

import org.springframework.data.repository.CrudRepository;
import website.totake.SqlStructure.SqlItem;
import website.totake.SqlStructure.SqlItemDetails;

/**
 * Created by meni on 26/05/17.
 */
public interface SqlItemDetailsRepository extends CrudRepository<SqlItemDetails, Long>  {
}

package website.totake.Repositories;

import org.springframework.data.repository.CrudRepository;
import website.totake.SqlStructure.SqlItem;

/**
 * Created by meni on 26/05/17.
 */
public interface SqlItemRepository extends CrudRepository<SqlItem, Long>  {
}

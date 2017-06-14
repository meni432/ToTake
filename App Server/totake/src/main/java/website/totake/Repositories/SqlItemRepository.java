package website.totake.Repositories;

import org.springframework.data.repository.CrudRepository;
import website.totake.SqlStructure.Item;

/**
 * Created by meni on 26/05/17.
 */
public interface SqlItemRepository extends CrudRepository<Item, Long>  {

}

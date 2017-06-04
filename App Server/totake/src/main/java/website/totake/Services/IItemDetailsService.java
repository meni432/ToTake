package website.totake.Services;

import website.totake.SqlStructure.SqlItem;
import website.totake.SqlStructure.SqlItemDetails;
import website.totake.SqlStructure.SqlTrip;

/**
 * Created by meni on 04/06/17.
 */
public interface IItemDetailsService {
    public SqlItemDetails addNewItemDetails(SqlTrip sqlTrip, SqlItem sqlItem, int amount, int isDone);
    public SqlItemDetails save(SqlItemDetails sqlItemDetails);
}

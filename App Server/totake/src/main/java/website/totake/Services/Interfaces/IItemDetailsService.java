package website.totake.Services.Interfaces;

import website.totake.SqlStructure.SqlItem;
import website.totake.SqlStructure.SqlItemDetails;
import website.totake.SqlStructure.SqlTrip;

/**
 * Created by meni on 04/06/17.
 */
public interface IItemDetailsService {
    public SqlItemDetails addNewItemDetails(SqlTrip sqlTrip, SqlItem sqlItem, int amount, int isDone);
    public SqlItemDetails save(SqlItemDetails sqlItemDetails);
    public void removeByTripIdAndItemId(SqlTrip sqlTrip, SqlItem sqlItem);
    public void removeByTripIdAndItemId(long tripId, long itemId);
    public SqlItemDetails getItemDetails(long tripId, long itemId);
}

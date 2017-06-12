package website.totake.Services.Interfaces;

import website.totake.SqlStructure.SqlItem;
import website.totake.SqlStructure.SqlTrip;

import java.util.Date;

/**
 * Created by meni on 28/05/17.
 */
public interface IItemService {
    public SqlItem getItem(long itemId);
    public Iterable<SqlItem> getAllItems();
    public SqlItem addNewItem(String mItemEHName, String mItemHEName);
    public SqlItem save(SqlItem sqlItem);
}

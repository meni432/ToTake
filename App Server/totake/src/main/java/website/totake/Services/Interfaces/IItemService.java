package website.totake.Services.Interfaces;

import website.totake.SqlStructure.Item;

/**
 * Created by meni on 28/05/17.
 */
public interface IItemService {
    public Item getItem(long itemId);
    public Iterable<Item> getAllItems();
    public Item addNewItem(String mItemEHName, String mItemHEName);
    public Item save(Item item);
}

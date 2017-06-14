package website.totake.Services.Interfaces;

import website.totake.SqlStructure.Item;
import website.totake.SqlStructure.ItemDetails;
import website.totake.SqlStructure.Trip;

/**
 * Created by meni on 04/06/17.
 */
public interface IItemDetailsService {
    public ItemDetails addNewItemDetails(Trip trip, Item item, int amount, int isDone);
    public ItemDetails save(ItemDetails itemDetails);
    public void removeByTripAndItem(Trip trip, Item item);
    public void removeByTripAndItem(long tripId, long itemId);
    public ItemDetails getItemDetails(long tripId, long itemId);
    public void removeItem(Item item);
}

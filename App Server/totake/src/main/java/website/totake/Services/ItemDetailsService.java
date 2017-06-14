package website.totake.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import website.totake.Repositories.SqlItemDetailsRepository;
import website.totake.Services.Interfaces.IItemDetailsService;
import website.totake.SqlStructure.Item;
import website.totake.SqlStructure.ItemDetails;
import website.totake.SqlStructure.Trip;

/**
 * Created by meni on 04/06/17.
 */
@Service
@Transactional
public class ItemDetailsService implements IItemDetailsService {
    @Autowired
    private SqlItemDetailsRepository sqlItemDetailsRepository;

    @Override
    public ItemDetails addNewItemDetails(Trip trip, Item item, int amount, int isDone) {
        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setTrip(trip);
        itemDetails.setItem(item);
        itemDetails.setAmount(amount);
        itemDetails.setIsDone(isDone);
        ItemDetails result = sqlItemDetailsRepository.save(itemDetails);
        return result;
    }

    @Override
    public ItemDetails save(ItemDetails itemDetails) {
        return sqlItemDetailsRepository.save(itemDetails);
    }

    @Override
    public void removeByTripAndItem(Trip trip, Item item) {
        //TODO need to implement
        throw new RuntimeException("removeByTripAndItem not implemnt yet");
    }

    @Override
    public void removeByTripAndItem(long tripId, long itemId) {
        //TODO need to implement
        throw new RuntimeException("removeByTripAndItem not implemnt yet");
    }

    @Override
    public ItemDetails getItemDetails(long tripId, long itemId) {
//        List<ItemDetails> sqlItemDetails = sqlItemDetailsRepository.findByTripIdAndItemId(tripId, itemId);
//        if(sqlItemDetails.size() == 1) {
//            return sqlItemDetails.get(0);
//        }
//        return null;
        //TODO need to impltment
        throw new RuntimeException("getItemDetails not implemnt yet");
    }

    @Override
    public void removeItem(Item item) {
        item.setStatus(Item.ITEM_DELETED);
    }
}

package website.totake.Services;

import io.prediction.Event;
import io.prediction.EventClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import website.totake.Repositories.SqlItemDetailsRepository;
import website.totake.Services.Interfaces.IItemDetailsService;
import website.totake.SqlStructure.Item;
import website.totake.SqlStructure.ItemDetails;
import website.totake.SqlStructure.Trip;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
//        Trip trip = tripService.getTrip(tripId);
//        List<ItemDetails> itemDetails = sqlItemDetailsRepository.findByTrip_TripIdAndItem_ItemID(tripId, itemId);
//        if (itemDetails.size() == 1) {
//            itemDetails.get(0).setIsDone(1);
//            sqlItemDetailsRepository.save(itemDetails.get(0));
//        }

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

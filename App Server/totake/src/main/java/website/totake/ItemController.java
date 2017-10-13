package website.totake;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.prediction.EngineClient;
import io.prediction.Event;
import io.prediction.EventClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import website.totake.Services.ItemDetailsService;
import website.totake.Services.ItemService;
import website.totake.Services.TripService;
import website.totake.Services.UserService;
import website.totake.SqlStructure.Item;
import website.totake.SqlStructure.ItemDetails;
import website.totake.SqlStructure.Trip;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Created by meni on 26/05/17.
 */
@RestController
public class ItemController {
//    @Autowired
//    private SqlItemRepository sqlItemRepository;

    @Autowired
    private TripService tripService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDetailsService itemDetailsService;

    @Autowired
    private UserService userService;

    @RequestMapping("/deleteItemFromTrip")
    public Item deleteItemFromTrip(@RequestParam(name = "userId", defaultValue = "-1") long userId,
                                   @RequestParam(name = "tripId", defaultValue = "-1") long tripId,
                                   @RequestParam(name = "itemId", defaultValue = "-1") long itemId) {

        Trip trip = tripService.getTrip(tripId);
        ItemDetails itemDetails = trip.getItemSpecificDetails(itemId);
        itemDetails.setIsDone(1);
        itemDetailsService.save(itemDetails);
        return null;
    }


    @RequestMapping("/getSuggestionItemForTrip")
    public Iterable<Item> getSuggestionItemForTrip(@RequestParam(name = "userId", defaultValue = "-1") long userId,
                                                   @RequestParam(name = "tripId", defaultValue = "-1") long tripId,
                                                   @RequestParam(name = "amount", defaultValue = "10") long amount) {

        Trip trip = tripService.getTrip(tripId);
        if (trip.getItemDetails().size() < 5) {
            return null;
        } else {
            ImmutableList.Builder<String> itemIdsListBuilder = ImmutableList.builder();
            Set<ItemDetails> tripItemsDetails = trip.getItemDetails();

            for (ItemDetails itemDetails : tripItemsDetails) {
                itemIdsListBuilder.add("i" + itemDetails.getPrimaryKey().getItem().getItemID());
            }

            ImmutableList<String> itemIdsImmutableList = itemIdsListBuilder.build();
            System.out.println("itemIdsImmutableList: " + itemIdsImmutableList);

            JsonObject response = getSuggestionListFromServer(amount, itemIdsImmutableList);

            if (response != null) {
                ImmutableList<Item> items = predictionResponseToItemObject(response);
                return items;
            }

        }
        return null;
    }

    private JsonObject getSuggestionListFromServer(long amount, ImmutableList<String> itemIdsImmutableList) {
        JsonObject response = null;
        EngineClient engineClient = new EngineClient(Defaults.PIO_DEPLOY_SERVER);

        try {
            response = engineClient.sendQuery(ImmutableMap.<String, Object>of(
                    "items", itemIdsImmutableList,
                    "num", amount
            ));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private ImmutableList<Item> predictionResponseToItemObject(JsonObject response) {
        JsonElement jsonElement = response.get("itemScores");
        JsonArray jsonElements = jsonElement.getAsJsonArray();

        ImmutableList.Builder<Item> itemBuilder = ImmutableList.builder();
        for (JsonElement element : jsonElements) {
            JsonObject itemObject = element.getAsJsonObject();
            String sItem = itemObject.get("item").getAsString();
            double iScore = itemObject.get("score").getAsDouble();
            int iItem = Integer.parseInt(sItem.substring(1));
            Item item = itemService.getItem(iItem);
            itemBuilder.add(item);
        }

        return itemBuilder.build();
    }

    @RequestMapping("/notifyChangeAmount")
    public ItemDetails notifyChangeAmount(@RequestParam(name = "userId", defaultValue = "-1") long userId,
                                          @RequestParam(name = "tripId", defaultValue = "-1") long tripId,
                                          @RequestParam(name = "itemId", defaultValue = "-1") long itemId,
                                          @RequestParam(name = "amount", defaultValue = "-1") int amount) {

        ItemDetails itemDetails = itemDetailsService.getItemDetails(tripId, itemId);
        itemDetails.setAmount(amount);
        itemDetailsService.save(itemDetails);

        return itemDetails;
    }

    @RequestMapping("/addNewItem")
    public Item addNewItem(@RequestParam(name = "userId", defaultValue = "-1") long userId,
                           @RequestParam(name = "tripId", defaultValue = "-1") long tripId,
                           @RequestParam(name = "itemName", defaultValue = "none") String itemName,
                           @RequestParam(name = "amount", defaultValue = "-1") int amount) {

        Trip trip = tripService.getTrip(tripId);
        Item item = itemService.addNewItem(itemName, itemName);
        ItemDetails itemDetails = itemDetailsService.addNewItemDetails(trip, item, amount, 0);
        return itemDetails.getItem();
    }


    @RequestMapping("/getSimilarItems")
    public String getSimilarItems(@RequestParam(name = "itemId", defaultValue = "i1") String itemId,
                                  @RequestParam(name = "amount", defaultValue = "4") long amount) {
        // create client object
        JsonObject response = null;
        EngineClient engineClient = new EngineClient("http://95.85.54.205:8000");

        // query

        try {
            response = engineClient.sendQuery(ImmutableMap.<String, Object>of(
                    "items", ImmutableList.of(itemId),
                    "num", amount
            ));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }


    @RequestMapping("/assignItemToUser")
    public Item assignItemToUser(@RequestParam(name = "userId", defaultValue = "-1") long userId,
                                 @RequestParam(name = "tripId", defaultValue = "-1") long tripId,
                                 @RequestParam(name = "itemId", defaultValue = "-1") long itemId,
                                 @RequestParam(name = "amount", defaultValue = "-1") int amount) {

        Trip trip = tripService.getTrip(tripId);
        Item item = itemService.getItem(itemId);
        ItemDetails itemDetails = itemDetailsService.addNewItemDetails(trip, item, amount, 0);

        JSONObject json = new JSONObject();
        try {
            json.put("event", "view");
            json.put("entityType", "user");
            json.put("entityId", Defaults.PIO_USER_PREFIX + userId);
            json.put("targetEntityType", "item");
            json.put("targetEntityId", Defaults.PIO_ITEM_PREFIX + itemId);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        EventPrediction.getInstance().sendEvent(json);

        return itemDetails.getItem();
    }

    @RequestMapping("/getAllItems")
    public Iterable<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @RequestMapping("/loadItemsToPredictionEngine")
    public boolean loadItemsToPredictionEngine() {
        for (Item item : itemService.getAllItems()) {
            try {
                JSONObject json = new JSONObject();
                json.put("event", "$set");
                json.put("entityType", "item");
                json.put("entityId", "i" + item.getItemID());


                CloseableHttpClient httpClient = HttpClientBuilder.create().build();

                EventPrediction.getInstance().sendEvent(json);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}

package website.totake.Services.DataModels;

import website.totake.SqlStructure.Trip;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ItemBlackList {

    private final Map<Trip, List<Long>> tripItemListMap = new HashMap<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void addToBlackList(Trip trip, long itemId) {
        executorService.submit( () -> {
           if (!tripItemListMap.containsKey(trip)) {
               tripItemListMap.put(trip, new ArrayList<>());
           }
           List<Long> tripBlackList = tripItemListMap.get(trip);
           if (!tripBlackList.contains(itemId)) {
               tripBlackList.add(itemId);
           }
        });
    }

    public Future<List<Long>> getBlackList(Trip trip) {
        return executorService.submit(() -> tripItemListMap.get(trip));
    }

}

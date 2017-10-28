package website.totake.Services.DataModels;

import com.google.common.collect.ImmutableList;
import website.totake.SqlStructure.Trip;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ItemBlackList {

    private final Map<Trip, List<Long>> tripItemListMap = new HashMap<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void addToBlackList(Trip trip, long itemId) {
        executorService.submit(() -> {
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


    private static final String DELIMITER = "|";
    private static final String DELIMITER_PATTERN = "\\|";
    public static String addToBlackList(long itemId, String origBlackList) {
        try {
            if (origBlackList == null || origBlackList.equals("") || origBlackList.length() == 0) {
                return itemId + DELIMITER;
            }
        } catch (NullPointerException e) { }
        String[] split = origBlackList.split(DELIMITER_PATTERN);
        List<Long> longs = new ArrayList<>();
        for (String s : split) {
            try {
                long id = Long.valueOf(s);
                    longs.add(id);
            } catch (NumberFormatException e) {
//                e.printStackTrace();
            }
        }
        if (!longs.contains(itemId)) {
            longs.add(itemId);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (long id : longs) {
            stringBuilder.append(id + "|");
        }
        return stringBuilder.toString();
    }

    public static boolean isInBlackList(String blackList, long itemId) {
        String[] strings = blackList.split(DELIMITER_PATTERN);
        List<Long> longs = new ArrayList<>();
        for (String s : strings) {
            try {
                longs.add(Long.valueOf(s));
            } catch (NumberFormatException e) {
//                e.printStackTrace();
            }
        }

        return longs.contains(itemId);
    }

    public static ImmutableList<Long> getImmutableBlackList(String blackList) {
        if (blackList == null || blackList.length() == 0) {
            return null;
        }
        String[] strings = blackList.split(DELIMITER_PATTERN);
        List<Long> longs = new ArrayList<>();
        for (String s : strings) {
            try {
                longs.add(Long.valueOf(s));
            } catch (NumberFormatException e) {
//                e.printStackTrace();
            }
        }

        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (long l : longs) {
            builder.add(l);
        }

        return builder.build();
    }
}

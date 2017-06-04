import java.io.File;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by meni on 24/05/17.
 */
public class DataReader {


    private static List<String> readFileAsList(String fileName) {
        if (fileName == null) {
            fileName = "user-item-160517_4.csv";
        }
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            return lines;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static UserItemsContainer userItemsArray(String fileName) {
        List<UserItem> userItems = new ArrayList<>();
        Map<Integer, Integer> itemFreq = new HashMap<>();


        List<String> allFileLines = readFileAsList(fileName);
        int numberOfLines = allFileLines.size();
        int maxUserId = -1;
        int maxItemId = -1;
        for (int lineIndex = 0; lineIndex < numberOfLines; lineIndex++) {
            String line = allFileLines.get(lineIndex);
            String[] lineElement = line.split(",");
            try {
                int userId = Integer.parseInt(lineElement[0]);
                int itemId = Integer.parseInt(lineElement[1]);
                maxUserId = Math.max(maxUserId, userId);
                maxItemId = Math.max(maxItemId, itemId);
                UserItem userItem = new UserItem(userId, itemId);
                userItems.add(userItem);
                Integer cFreq = null;
                if ((cFreq = itemFreq.get(itemId)) != null) {
                    itemFreq.put(itemId, cFreq + 1);
                } else {
                    itemFreq.put(itemId, 0);
                }
            } catch (NumberFormatException e) {
            }
        }

        return new UserItemsContainer(userItems, maxUserId, maxItemId, itemFreq);
    }


    public static Map<Integer, String> itemNames = null;

    private static Map<Integer, String> loadItemNames() {
        List<String> lines = readFileAsList("all-items-240517.csv");
        Map<Integer, String> itemIdToName = new HashMap<>();
        for (String line : lines) {
            try {
                String[] lineElement = line.split(",");
                int itemId = Integer.parseInt(lineElement[0]);
                String heName = lineElement[1];
                itemIdToName.put(itemId, heName);
            } catch (NumberFormatException e) {}
        }

        return itemIdToName;
    }

    public static String getItemName(int id) {
        if (itemNames == null) {
            itemNames = loadItemNames();
        }
        if (itemNames.containsKey(id)) {
            return itemNames.get(id);
        }
        return "no name";
    }

}

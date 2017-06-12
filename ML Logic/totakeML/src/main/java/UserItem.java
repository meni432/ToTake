import java.util.List;
import java.util.Map;

/**
 * Created by meni on 24/05/17.
 */
public class UserItem {
    int userId;
    int itemId;
    int rate; //0-5

    @Override
    public String toString() {
        return "UserItem{" +
                "userId=" + userId +
                ", itemId=" + itemId +
                " , rate=" + rate +
                '}';
    }

    public UserItem(int userId, int itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }


    public UserItem(int userId, int itemId, int weight) {
        this.userId = userId;
        this.itemId = itemId;
        this.rate = weight;
    }
}

class UserItemsContainer {
    private List<UserItem> userItems;
    private int maxUserId;
    private int maxItemId;
    private  Map<Integer, Integer> itemFreq;
    private  Map<Integer, Integer> userRateForItem;

    @Override
    public String toString() {
        return "UserItemsContainer{" +
                "userItems=" + userItems +
                ", maxUserId=" + maxUserId +
                ", maxItemId=" + maxItemId +
                ", itemFreq=" + itemFreq +
                '}';
    }

    public UserItemsContainer(List<UserItem> userItems, int maxUserId, int maxItemId, Map<Integer, Integer> itemFreq) {
        this.userItems = userItems; //list of user items
        this.maxUserId = maxUserId; //
        this.maxItemId = maxItemId;
        this.itemFreq = itemFreq;
    }

    public UserItemsContainer(List<UserItem> userItems, int maxUserId, int maxItemId, Map<Integer, Integer> itemFreq,Map<Integer, Integer> userRateForItem) {
        this.userItems = userItems; //list of user items
        this.maxUserId = maxUserId; //
        this.maxItemId = maxItemId;
        this.itemFreq = itemFreq;
        this.userRateForItem = userRateForItem;
    }

    public List<UserItem> getUserItems() {
        return userItems;
    }

    public int getMaxUserId() {
        return maxUserId;
    }

    public int getMaxItemId() {
        return maxItemId;
    }

    public Map<Integer, Integer> getItemFreq() {
        return itemFreq;
    }

    public Map<Integer, Integer> getUserRateForItem() {
        return userRateForItem;
    }
}
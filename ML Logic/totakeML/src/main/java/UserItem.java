import java.util.List;
import java.util.Map;

/**
 * Created by meni on 24/05/17.
 */
public class UserItem {
    int userId;
    int itemId;

    @Override
    public String toString() {
        return "UserItem{" +
                "userId=" + userId +
                ", itemId=" + itemId +
                '}';
    }

    public UserItem(int userId, int itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }
}

class UserItemsContainer {
    private List<UserItem> userItems;
    private int maxUserId;
    private int maxItemId;
    private  Map<Integer, Integer> itemFreq;

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
        this.userItems = userItems;
        this.maxUserId = maxUserId;
        this.maxItemId = maxItemId;
        this.itemFreq = itemFreq;
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
}
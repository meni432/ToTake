/**
 * Created by tahel on 15/06/2017.
 */
public class RecommendedItem {

    private int itemId;
    private int itemSum;

    public  RecommendedItem(int itemId){
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getItemSum() {
        return itemSum;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setItemSum(int itemSum) {
        this.itemSum = itemSum;
    }

}

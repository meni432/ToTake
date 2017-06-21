import java.util.*;

/**
 * Created by tahel on 15/06/2017.
 */
public class RatedCF {


    private UserItemsContainer ans;
    private int numOfItems;
    private int numOfUsers;
    private List<List<RecommendedItem>> recommendationLists;
    private double[][] itemItemCosine; //every item relationship to every other item
    private int[][] itemUserMatrix; //save for every item if user take this item
    private double[][] itemUserRate; //rate of each user of each item
    private double[][] itemUserRateCosine; //cos(itemUserVectors)
    private final double k = 0;


    public RatedCF(){
        ans = DataReader.userItemsArray(null);
        numOfItems = ans.getMaxItemId() + 1;
        numOfUsers = ans.getMaxUserId() + 1;
        recommendationLists = new ArrayList<>();
        itemItemCosine = new double[numOfItems][numOfItems];
        itemUserMatrix = new int[numOfItems][numOfUsers];
        itemUserRate = new double[numOfItems][numOfUsers];
        itemUserRateCosine = new double[numOfItems][numOfUsers];
    }


    public RatedCF(UserItemsContainer ans){
        this.ans = ans;
        numOfItems = ans.getMaxItemId() + 1;
        numOfUsers = ans.getMaxUserId() + 1;
        recommendationLists = new ArrayList<>();
        itemItemCosine = new double[numOfItems][numOfItems];
        itemUserMatrix = new int[numOfItems][numOfUsers];
        itemUserRate = new double[numOfItems][numOfUsers];
        itemUserRateCosine = new double[numOfItems][numOfUsers];
    }

    /**
     * get two vectors
     * @return the multiply between the given vectors.
     */
    public static double multiplyVectors(double[] vecA, double[] vecB) {
        double ans = 0;
        assert (vecA.length == vecB.length);
        for (int i = 0; i < vecA.length; i++) {
            ans += vecA[i] * vecB[i];
        }
        return ans;
    }

    /**
     * @return normal of the given vector
     */
    public static double normVector(double [] vec) {
        double ans = 0;
        for (int i = 0; i < vec.length; i++) {
            ans += vec[i] * vec[i];
        }
        return Math.sqrt(ans);
    }

    /**
     * run on all users items and if user take item -put user rate of item
     */
    private void fillMatixRate(){
        for (UserItem userItem : ans.getUserItems()) {
            if (userItem.userId > 0 && userItem.itemId > 0) {
                itemUserRate[userItem.itemId][userItem.userId] = userItem.rate;
                itemUserMatrix[userItem.itemId][userItem.userId] = 1;
            } else itemUserRate[userItem.itemId][userItem.userId] = 0;
        }
    }


    /**
     * make the recommendation better with summing all the item rate and divides with the number of users who take this item
     *  (like 10/3...)
     *  and sud it from the rill rate (like 5-(10/3))
     */
    private void fillMatixRateCos() {
        for (int i = 0; i < numOfItems ; i++) { //items
            double countRated = 0;
            double sumRate = 0;
            for (int j = 0; j < numOfUsers ; j++) { //users
                sumRate += itemUserRate[i][j];
                countRated++;
            }
            for (int j = 0; j < numOfUsers ; j++) { //users
                if (itemUserMatrix[i][j] != 0) {
                    itemUserRateCosine[i][j] = itemUserRate[i][j] - (sumRate / countRated);
                }
            }
        }

    }

    /**
     * item item cosine
     * run over all item and multiply every two items (and div the normal) -cosine
     */
    private void cosine(){
        for (int indexItemA = 0; indexItemA < numOfItems ; indexItemA++) {
            for (int indexItemB = 0; indexItemB < numOfItems ; indexItemB++) {
                if (indexItemA != indexItemB) { //if not the same item
                    double[] vectorA = itemUserRateCosine[indexItemA];
                    double[] vectorB = itemUserRateCosine[indexItemB];
                    if ((normVector(vectorA) * normVector(vectorB)) > 0) {
                        itemItemCosine[indexItemA][indexItemB] =
                                multiplyVectors(vectorA, vectorB) / (normVector(vectorA) * normVector(vectorB)); //find similarity
                    }
                }
            }
        }
    }

    /**
     * for machine learning algorithm
     * Item-to-Item algorithm offers an interesting point of reference
     */
    public void slopeOneAlgo(){
        fillMatixRate();
        fillMatixRateCos();
        cosine();

        for (int userIndex = 0; userIndex < numOfUsers; userIndex++) { //run on the users
            double[] userItemSum = new double[ans.getMaxItemId()];
            double[] userItemSumCos = new double[ans.getMaxItemId()];
            double [] userItemRecommendRate = new double[ans.getMaxItemId()];
            for (int userItemIndex = 0; userItemIndex < numOfItems ; userItemIndex++) { //run on items - of the user
                if (itemUserMatrix[userItemIndex][userIndex] == 0) {
                    for (int itemIndex = 0; itemIndex < numOfItems ; itemIndex++) { //run on the items (the others items to find rate

                        if (itemItemCosine[userItemIndex][itemIndex] > 0 && itemUserRate[itemIndex][userIndex] != 0) {
                            userItemSum[userItemIndex] += itemItemCosine[userItemIndex][itemIndex] * itemUserRate[itemIndex][userIndex];
                            userItemSumCos[userItemIndex] += itemItemCosine[userItemIndex][itemIndex];
                            userItemRecommendRate[userItemIndex] += itemItemCosine[userItemIndex][itemIndex];
                        }
                    }
                    userItemSum[userItemIndex] = userItemSum[userItemIndex] / userItemSumCos[userItemIndex];
                } else userItemSum[userItemIndex] = 0;
            }

            ArrayList<RecommendedItem> userRecommendation = new ArrayList<RecommendedItem>();
            for (int userItemSumIndex = 0; userItemSumIndex < userItemSum.length; userItemSumIndex++) {
                if (userItemSum[userItemSumIndex] > k) {
                    userRecommendation.add(new RecommendedItem(userItemSumIndex));
                }
            }

            Collections.sort(userRecommendation, new Comparator<RecommendedItem>() {
                @Override
                public int compare(RecommendedItem lhs, RecommendedItem rhs) {
                    return userItemSum[lhs.getItemId()] < userItemSum[rhs.getItemId()] ? -1 : (userItemSum[lhs.getItemId()] > userItemSum[rhs.getItemId()]) ? 1 : 0;
                }
            });

            recommendationLists.add(userIndex, userRecommendation);

        }
    }

}

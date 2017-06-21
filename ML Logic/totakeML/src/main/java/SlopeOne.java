import java.util.*;

/**
 * Created by meni on 22/05/17.
 */
public class SlopeOne {

    protected UserItemsContainer ans = DataReader.userItemsArray(null);
    protected List<List<Integer>> recommendationLists;
    protected List<List<SlopeOneRate.UserPair>> recommendationListsPair;
    protected double[][] itemItemCosine; //every item relationship to every other item
    protected   int[][] itemUserMatrix; //save for every item if user take this item

    public SlopeOne(UserItemsContainer ans){
        this.ans = ans;
        itemItemCosine = new double[ans.getMaxItemId() + 1][ans.getMaxItemId() + 1];
        itemUserMatrix = new int[ans.getMaxItemId() + 1][ans.getMaxUserId() + 1];
    }

    public SlopeOne(){
        itemItemCosine = new double[ans.getMaxItemId() + 1][ans.getMaxItemId() + 1];
        itemUserMatrix = new int[ans.getMaxItemId() + 1][ans.getMaxUserId() + 1];
    }

    public static double multiplyVectors(int[] vecA, int[] vecB) {
        double ans = 0;
        assert (vecA.length == vecB.length);
        for (int i = 0; i < vecA.length; i++) {
            ans += vecA[i] * vecB[i];
        }
        return ans;
    }


    public static double multiplyVectors(double[] vecA, double[] vecB) {
        double ans = 0;
        assert (vecA.length == vecB.length);
        for (int i = 0; i < vecA.length; i++) {
            ans += vecA[i] * vecB[i];
        }
        return ans;
    }

    public static double normVector(int[] vec) {
        double ans = 0;
        for (int i = 0; i < vec.length; i++) {
            ans += vec[i] * vec[i];
        }
        return Math.sqrt(ans);
    }

    public static double normVector(double[] vec) {
        double ans = 0;
        for (int i = 0; i < vec.length; i++) {
            ans += vec[i] * vec[i];
        }
        return Math.sqrt(ans);
    }
    public static double[][] randomMatrix(int n, int m) {
        double[][] ans = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[i][j] = Math.random();
            }
        }
        return ans;
    }



    public void slopeOne() {
        Map<Integer, Integer> itemFreq = ans.getItemFreq();

        Iterator it = itemFreq.entrySet().iterator();
        while (it.hasNext()) {
            //combined into one class the key and the value
            Map.Entry pair = (Map.Entry) it.next();
//            System.out.println(pair);
            it.remove(); // avoids a ConcurrentModificationException
        }

        //for machine learning algorithm


        //run on all users items and if user take item -put true
        //Item-to-Item algorithm offers an interesting point of reference
        for (UserItem userItem : ans.getUserItems()) {
            if (userItem.userId > 0 && userItem.itemId > 0) {
                itemUserMatrix[userItem.itemId][userItem.userId] = 1;
            }
        }

        //run over all item and multiply every two items (and div the normal)
        for (int indexItemA = 0; indexItemA < itemItemCosine.length; indexItemA++) {
            for (int indexItemB = 0; indexItemB < itemItemCosine.length; indexItemB++) {
                if (indexItemA != indexItemB) { //if not the same item
                    int[] vectorA = itemUserMatrix[indexItemA];
                    int[] vectorB = itemUserMatrix[indexItemB];
                    if ((normVector(vectorA) * normVector(vectorB)) > 0) {
                        itemItemCosine[indexItemA][indexItemB] =
                                multiplyVectors(vectorA, vectorB) / (normVector(vectorA) * normVector(vectorB));
                    }
                }
            }
        }


        final double k = 0;
        recommendationLists = new ArrayList<>();
        recommendationListsPair = new ArrayList<>();
        for (int userIndex = 0; userIndex < ans.getMaxUserId(); userIndex++) {
            double[] userItemSum = new double[ans.getMaxItemId()];
            for (int userItemIndex = 0; userItemIndex < ans.getMaxItemId(); userItemIndex++) { //run on the users
                for (int itemIndex = 0; itemIndex < ans.getMaxItemId(); itemIndex++) { //run on the items
                    if (itemUserMatrix[userItemIndex][userIndex] == 0) {
                        userItemSum[userItemIndex] += itemItemCosine[userItemIndex][itemIndex];
                    }
                }
            }
            ArrayList<Integer> userRecommendation = new ArrayList<Integer>();
            ArrayList<SlopeOneRate.UserPair> userRecommendationPairs = new ArrayList<>();
            for (int userItemSumIndex = 0; userItemSumIndex < userItemSum.length; userItemSumIndex++) {
                if (userItemSum[userItemSumIndex] > k) {
                    userRecommendation.add(userItemSumIndex);
                }
            }
//            for (int i = 0; i < userRecommendation.size(); i++) {
//                System.out.print(" " + userItemSum[userRecommendation.get(i)]);
//            }
//            System.out.println();

            Collections.sort(userRecommendation, new Comparator<Integer>() {
                @Override
                public int compare(Integer lhs, Integer rhs) {
                    return userItemSum[lhs] < userItemSum[rhs] ? 1 : (userItemSum[lhs] > userItemSum[rhs]) ? -1 : 0;
                }
            });

//            for (int i = 0; i < userRecommendation.size(); i++) {
//                System.out.print(" " + userItemSum[userRecommendation.get(i)]);
//            }
//            System.out.println();
            recommendationLists.add(userIndex, userRecommendation);
//            recommendationListsPair.add(userIndex, )

        }
        printRecommendation();
    }

    public void printRecommendation(){

        for (int i = 0; i < recommendationLists.size(); i++) {

            System.out.println("------- recommendation for id " + i + " --------");
            for (int itemId : recommendationLists.get(i)) {
                System.out.print("[(" + itemId + ") " + DataReader.getItemName(itemId) + "] , ");
            }
            System.out.println("\n");
            System.out.println("the list of item the user select: ");
            for (int j = 0; j < ans.getMaxItemId(); j++) {
                if (itemUserMatrix[j][i] > 0) {
                    System.out.print("[(" + j + ") " + DataReader.getItemName(j) + "], ");
                }
            }
            System.out.println("\n");
        }
    }
}

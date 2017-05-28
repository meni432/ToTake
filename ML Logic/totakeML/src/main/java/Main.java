import org.junit.Assert;

import java.util.*;

/**
 * Created by meni on 24/05/17.
 */
public class Main {

    public static double multiplayVectors(int[] vecA, int[] vecB) {
        double ans = 0;
        assert (vecA.length == vecB.length);
        for (int i = 0; i < vecA.length; i++) {
            ans = vecA[i] * vecB[i];
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

    public static double[][] randomMatrix(int n, int m) {
        double[][] ans = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[i][j] = Math.random();
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        UserItemsContainer ans = DataReader.userItemsArray(null);
        System.out.println(ans);
        for (int i = 0; i < ans.getUserItems().size(); i++) {
//            System.out.println(ans.getUserItems().get(i));
        }

        Map<Integer, Integer> itemFreq = ans.getItemFreq();

        Iterator it = itemFreq.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
//            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }

        int[][] itemUserMatrix = new int[ans.getMaxItemId() + 1][ans.getMaxUserId() + 1];
        for (UserItem userItem : ans.getUserItems()) {
            if (userItem.userId > 0 && userItem.itemId > 0) {
                itemUserMatrix[userItem.itemId][userItem.userId] = 1;
            }
        }

        double[][] itemItemCosine = new double[ans.getMaxItemId() + 1][ans.getMaxItemId() + 1];
        for (int indexItemA = 0; indexItemA < itemItemCosine.length; indexItemA++) {
            for (int indexItemB = 0; indexItemB < itemItemCosine.length; indexItemB++) {
                if (indexItemA != indexItemB) {
                    int[] vectorA = itemUserMatrix[indexItemA];
                    int[] vectorB = itemUserMatrix[indexItemB];
                    if ((normVector(vectorA) * normVector(vectorB)) > 0) {
                        itemItemCosine[indexItemA][indexItemB] =
                                multiplayVectors(vectorA, vectorB) / (normVector(vectorA) * normVector(vectorB));
                    }
                }
            }
        }


        final double k = 0;
        List<List<Integer>> recommendationLists = new ArrayList<>();
        for (int userIndex = 0; userIndex < ans.getMaxUserId(); userIndex++) {
            double[] userItemSum = new double[ans.getMaxItemId()];
            for (int userItemIndex = 0; userItemIndex < ans.getMaxItemId(); userItemIndex++) {
                for (int itemIndex = 0; itemIndex < ans.getMaxItemId(); itemIndex++) {
                    if (itemUserMatrix[userItemIndex][userIndex] == 0) {
                        userItemSum[userItemIndex] += itemItemCosine[userItemIndex][itemIndex];
                    }
                }
            }
            List<Integer> userRecommendation = new ArrayList<>();
            for (int userItemSumIndex = 0; userItemSumIndex < userItemSum.length; userItemSumIndex++) {
                if (userItemSum[userItemSumIndex] > k) {
                    userRecommendation.add(userItemSumIndex);
                }
            }

            recommendationLists.add(userIndex, userRecommendation);
        }

        for (int i = 0; i < recommendationLists.size(); i++) {
            System.out.println("recomandation for id " + i);
            for (int itemId : recommendationLists.get(i)) {
                System.out.print("[(" + itemId +") " + DataReader.getItemName(itemId) + "] , ");
            }
            System.out.println();
            System.out.print("orig: ");
            for (int j = 0; j < ans.getMaxItemId(); j++) {
                if (itemUserMatrix[j][i] > 0) {
                    System.out.print("[(" +j+ ") " + DataReader.getItemName(j) +"], ");
                }
            }
            System.out.println();
        }
    }
}

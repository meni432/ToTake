/**
 * Created by tahel on 12/06/2017.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by tahel on 12/06/2017.
 */
public class SlopeOneRate extends SlopeOne {

    private double[][] itemUserRate;
    private double[][] itemUserRateCosine;

    public SlopeOneRate() {
        super();
        itemUserRate = new double[ans.getMaxItemId() + 1][ans.getMaxUserId() + 1];
        itemUserRateCosine = new double[ans.getMaxItemId() + 1][ans.getMaxUserId() + 1];
    }

    public SlopeOneRate(UserItemsContainer ans) {
        super(ans);
        itemUserRate = new double[ans.getMaxItemId() + 1][ans.getMaxUserId() + 1];
        itemUserRateCosine = new double[ans.getMaxItemId() + 1][ans.getMaxUserId() + 1];
    }


    public void slopeOne() {

        //for machine learning algorithm

        //run on all users items and if user take item -put true
        //Item-to-Item algorithm offers an interesting point of reference
        for (UserItem userItem : ans.getUserItems()) {
            if (userItem.userId > 0 && userItem.itemId > 0) {
//                itemUserRate[userItem.itemId][userItem.userId] = userItem.rate;
                if (userItem.itemId == 86) {
                    itemUserRate[userItem.itemId][userItem.userId] = Math.random() * 5 + 1;
                } else itemUserRate[userItem.itemId][userItem.userId] = Math.random() * 2 + 1;
                itemUserMatrix[userItem.itemId][userItem.userId] = 1;
            } else itemUserMatrix[userItem.itemId][userItem.userId] = 0;
        }

        //for recommendation with rate (like- 10/3)
        for (int i = 0; i < itemUserRateCosine.length; i++) { //items
            double countRated = 0;
            double sumRate = 0;
            for (int j = 0; j < itemUserRateCosine[0].length; j++) { //users
                sumRate += itemUserRate[i][j];
                countRated++;
            }
            for (int j = 0; j < itemUserRateCosine[0].length; j++) {
                if (itemUserMatrix[i][j] != 0) {
                    itemUserRateCosine[i][j] = itemUserRate[i][j] - (sumRate / countRated);
                }
            }
        }

        //run over all item and multiply every two items (and div the normal) -cosine
        for (int indexItemA = 0; indexItemA < itemItemCosine.length; indexItemA++) {
            if (indexItemA == 86) System.out.println(Arrays.toString(itemUserRate[indexItemA]));
            for (int indexItemB = 0; indexItemB < itemItemCosine.length; indexItemB++) {

                if (indexItemA != indexItemB) { //if not the same item
                    double[] vectorA = itemUserRateCosine[indexItemA];
                    double[] vectorB = itemUserRateCosine[indexItemB];

//                    if(indexItemA == 86) System.out.print(multiplyVectors(vectorA, vectorB)+" "+(normVector(vectorA) * normVector(vectorB))+" $$ ");
                    if ((normVector(vectorA) * normVector(vectorB)) > 0) {
                        itemItemCosine[indexItemA][indexItemB] =
                                multiplyVectors(vectorA, vectorB) / (normVector(vectorA) * normVector(vectorB)); //find similarity
//                        if(indexItemA == 86) System.out.print(multiplyVectors(vectorA, vectorB)+" "+(normVector(vectorA) * normVector(vectorB))+" $$ ");
                    }
                }
            }
        }

        final double k = 0;
        recommendationLists = new ArrayList<>();
        recommendationListsPair = new ArrayList<>();
        for (int userIndex = 0; userIndex < ans.getMaxUserId(); userIndex++) { //run on the users
            double[] userItemSum = new double[ans.getMaxItemId()];
            double[] userItemSumCos = new double[ans.getMaxItemId()];
            double [] userItemRecommendRate = new double[ans.getMaxItemId()];
            for (int userItemIndex = 0; userItemIndex < ans.getMaxItemId(); userItemIndex++) { //run on items - of the user
                if (itemUserMatrix[userItemIndex][userIndex] == 0) {
                    for (int itemIndex = 0; itemIndex < ans.getMaxItemId(); itemIndex++) { //run on the items (the others items to find rate

                        if (itemItemCosine[userItemIndex][itemIndex] > 0 && itemUserRate[itemIndex][userIndex] != 0) {
                            userItemSum[userItemIndex] += itemItemCosine[userItemIndex][itemIndex] * itemUserRate[itemIndex][userIndex];
                            userItemSumCos[userItemIndex] += itemItemCosine[userItemIndex][itemIndex];
                            userItemRecommendRate[userItemIndex] += itemItemCosine[userItemIndex][itemIndex];
//                            if (userItemIndex == 142)
//                                System.out.println("item item rate: " + itemItemCosine[userItemIndex][itemIndex] + " userItemSumCos: " + userItemSumCos[userItemIndex] + " useItemSum " + userItemSum[userItemIndex] + " user: " + userIndex);
                        }
                    }
                    userItemSum[userItemIndex] = userItemSum[userItemIndex] / userItemSumCos[userItemIndex];
//                    if (userItemIndex == 142)
//                        System.out.println("userItemSum " + userItemSum[userItemIndex] + " user: " + userIndex);
                } else userItemSum[userItemIndex] = 0;
            }
            ArrayList<Integer> userRecommendation = new ArrayList<Integer>();
            ArrayList<UserPair> userRecommendationPairs = new ArrayList<>();
            for (int userItemSumIndex = 0; userItemSumIndex < userItemSum.length; userItemSumIndex++) {
                if (userItemSum[userItemSumIndex] > k) {
                    userRecommendation.add(userItemSumIndex);
                    userRecommendationPairs.add(new UserPair(userItemSumIndex, userItemSum[userItemSumIndex]));
                }
            }
//            System.out.println(Arrays.toString(userItemSum));
            Collections.sort(userRecommendation, new Comparator<Integer>() {
                @Override
                public int compare(Integer lhs, Integer rhs) {
                    return userItemSum[lhs] < userItemSum[rhs] ? -1 : (userItemSum[lhs] > userItemSum[rhs]) ? 1 : 0;
                }
            });

            Collections.sort(userRecommendationPairs, new Comparator<UserPair>() {
                @Override
                public int compare(UserPair lhs, UserPair rhs) {
                    return userItemSum[lhs.userId] < userItemSum[rhs.userId] ? -1 : (userItemSum[lhs.userId] > userItemSum[rhs.userId]) ? 1 : 0;
                }
            });

            recommendationLists.add(userIndex, userRecommendation);
            recommendationListsPair.add(userIndex, userRecommendationPairs);

        }
        printRecommendation();
    }


    class UserPair {
        int userId;
        double userSum;

        public UserPair(int userId, double itemSum) {
            this.userId = userId;
            this.userSum = itemSum;
        }

        @Override
        public String toString() {
            return "UserPair{" +
                    "userId=" + userId +
                    ", userSum=" + userSum +
                    '}';
        }
    }

    public void printRecommendation() {

        try{
            PrintWriter writer = new PrintWriter("C:\\Users\\tahel\\Desktop\\recommendation.txt", "UTF-8");
            for (int i = 0; i < recommendationLists.size(); i++) {

//            if (!(i == 43 || i == 45)) continue;
                writer.println("------- recommendation for id " + i + " --------");
//            for (int itemId : recommendationLists.get(i)) {
//                writer.println("[(" + itemId + ")  " + DataReader.getItemName(itemId) + "] , ");
//            }
//            System.out.println();
//            System.out.println("/////////////////////////////");

                for (UserPair itemId : recommendationListsPair.get(i)) {
                    writer.print("[(" + itemId.userId + ")  " + " (" + itemId.userSum + ") " + DataReader.getItemName(itemId.userId) + "] , ");
                }
                writer.println("\n");
                writer.println("the list of item the user select: ");
                for (int j = 0; j < ans.getMaxItemId(); j++) {
                    if (itemUserMatrix[j][i] > 0) {
                        writer.print("[(" + j + ")" + " { " + itemUserRate[j][i] + " } " + DataReader.getItemName(j) + "], ");
                    }
                }
                writer.println("\n");
            }
            writer.close();

        } catch (IOException e) {
            // do something
        }
//
        for (int i = 0; i < recommendationLists.size(); i++) {

            if (!(i == 43 || i == 45)) continue;
            System.out.println("------- recommendation for id " + i + " --------");
//            for (int itemId : recommendationLists.get(i)) {
//                System.out.print("[(" + itemId + ")  " + DataReader.getItemName(itemId) + "] , ");
//            }
//            System.out.println();
//            System.out.println("/////////////////////////////");

            for (UserPair itemId : recommendationListsPair.get(i)) {
                System.out.print("[(" + itemId.userId + ")  " + " (" + itemId.userSum + ") " + DataReader.getItemName(itemId.userId) + "] , ");
            }
            System.out.println("\n");
            System.out.println("the list of item the user select: ");
            for (int j = 0; j < ans.getMaxItemId(); j++) {
                if (itemUserMatrix[j][i] > 0) {
                    System.out.print("[(" + j + ")" + " { " + itemUserRate[j][i] + " } " + DataReader.getItemName(j) + "], ");
                }
            }
            System.out.println("\n");
        }
//        for (int i = 0; i < itemUserRate.length; i++) {
//            if(i==86)System.out.println(Arrays.toString(itemUserRate[i]));
//        }
//        System.out.println("\n");
//        for (int i = 0; i < itemItemCosine.length; i++) {
//            for (int j = 0; j < itemItemCosine[0].length; j++) {
//                if(j==86) System.out.print(itemItemCosine[i][j]+" ");
//            }
//        }
//        System.out.println("\n");
//        for (int i = 0; i < itemItemCosine.length; i++) {
//            if(i==86 || i==142){
//                System.out.println(i+": "+Arrays.toString(itemItemCosine[i])+"\n");
//                System.out.println();
//            }
//        }
    }
}


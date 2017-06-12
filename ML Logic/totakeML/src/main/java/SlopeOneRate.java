/**
 * Created by tahel on 12/06/2017.
 */
import java.util.*;

/**
 * Created by tahel on 12/06/2017.
 */
public class SlopeOneRate extends SlopeOne {

    private int [][] itemUserRate;
    public SlopeOneRate(){
        super();
        itemUserRate = new int[ans.getMaxItemId() + 1][ans.getMaxUserId() + 1];
    }

    public SlopeOneRate(UserItemsContainer ans){
        super(ans);
        itemUserRate = new int[ans.getMaxItemId() + 1][ans.getMaxUserId() + 1];
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
                itemUserRate[userItem.itemId][userItem.userId] = userItem.rate;
                itemUserMatrix[userItem.itemId][userItem.userId] = 1;
            }
            //else itemUserMatrix[userItem.itemId][userItem.userId]=0;
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

        }
        printRecommendation();
    }

}


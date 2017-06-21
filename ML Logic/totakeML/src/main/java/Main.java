import java.util.*;

/**
 * Created by meni on 24/05/17.
 */
public class Main {

    public static void main(String[] args) {
        //get array of all item in file
        UserItemsContainer ans = DataReader.userItemsArray(null);
//        SlopeOne s = new SlopeOne(ans);
//        s.slopeOne();
//        System.out.println("///////////////////////////////////////////////////////////");
        SlopeOneRate s2 = new SlopeOneRate(ans);
        s2.slopeOne();
    }
}

public class Main {

    public static void main(String[] args) {
        String s = null;
       try {
           s.charAt(1);
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}

package TestSachen;

/**
 * Created by Martin on 30.11.2015.
 */
public class huebi {
    public static void main(String[] args) {

        String bla = "16";
        try {
            int i = Integer.parseInt(bla);
            if (bla != "" && i <= 15) {
                System.out.println("treffer");
            } else System.out.println("fail");
        } catch (NumberFormatException e) {
            System.out.println("keine nummer");
        }
    }
}

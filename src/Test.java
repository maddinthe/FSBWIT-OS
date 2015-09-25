/**
 * Created by Martin on 14.09.2015.
 */
public class Test {
    public static void main(String[] args) {
        StringBuilder test=new StringBuilder();



        for (int i = 0; i < 10000; i++) {
            test.append(i);
            test.append("\n");
        }
        long now = System.currentTimeMillis();
        System.out.println(test);
       long sb = (System.currentTimeMillis()-now);

        now=System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
        }
        System.out.println(sb);
        System.out.println(System.currentTimeMillis()-now);

    }
}

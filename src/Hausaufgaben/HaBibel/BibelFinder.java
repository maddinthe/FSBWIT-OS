package Hausaufgaben.HaBibel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mtheilen on 02.10.2015.
 * Augabe wörter der Bibel nach häfigkeit sortiert inkl derer ausgeben *
 */
public class BibelFinder {

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        woerter(new File("./Testdaten/bibel.txt"));
        System.out.println(System.currentTimeMillis()-start);

    }

    public static void woerter(File in) {
        try (BufferedReader br = new BufferedReader(new FileReader(in))) {
            Map<String, Integer> woerter = new HashMap<>();

            Pattern p = Pattern.compile("\\b[a-zA-Zäöüß]+");
            String zeile;
            Integer c = 0;
            while ((zeile = br.readLine()) != null) {
                Matcher m = p.matcher(zeile);
                while (m.find())
                    if ((c = woerter.get(m.group())) != null)
                        woerter.put(m.group(), c + 1);
                    else
                        woerter.put(m.group(), 1);


            }
            String[] worte=woerter.keySet().toArray(new String[woerter.size()]);
            Arrays.sort(worte, new Comparator<String>() {
                public int compare(String o1, String o2) {
                    int i= woerter.get(o2)-woerter.get(o1);
                    if (i==0) return String.CASE_INSENSITIVE_ORDER.compare(o1, o2);
                    else return i;
                }
            });
            StringBuffer sb=new StringBuffer();
            for (String s:worte){
                sb.append(s);
                sb.append(" kommt");
                sb.append(woerter.get(s));
                sb.append(" mal vor\n");
            }
            System.out.println(sb);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

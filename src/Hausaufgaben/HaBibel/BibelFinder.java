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
 * Augabe der Wörter der Bibel nach Häufigkeit sortiert und Ausgabe der Worte inkl deren Häufigkeit*
 */
public class BibelFinder {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        woerter(new File("./Testdaten/bibel.txt"));
        System.out.println(System.currentTimeMillis() - start);

    }

    public static void woerter(File in) {
        try (BufferedReader br = new BufferedReader(new FileReader(in))) {
            Map<String, Integer> woerter = new HashMap<>();

            Pattern p = Pattern.compile("\\b[a-zA-ZäöüßÄÖÜ]+");
            String zeile;
            Integer c = 0;
            while ((zeile = br.readLine()) != null) {
                Matcher m = p.matcher(zeile);
                while (m.find()) {
                    String toKey = m.group().toLowerCase();
                    if ((c = woerter.get(toKey)) != null)
                        woerter.put(toKey, c + 1);
                    else
                        woerter.put(toKey, 1);
                }

            }
//            String[] worte = woerter.keySet().toArray(new String[woerter.size()]);
//            Arrays.sort(worte, new Comparator<String>() {
//                public int compare(String o1, String o2) {
//                    int i = woerter.get(o2) - woerter.get(o1);
//                    if (i == 0) return o1.compareTo(o2);
//                    else return i;
//                }
//            });

          List<Map.Entry<String,Integer>> list=new LinkedList<>(woerter.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {

                public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
                    int i= o1.getValue()-o2.getValue();
                    if (i==0)return o1.getKey().compareTo(o1.getKey());
                    return i;
                }
            });

            StringBuffer sb = new StringBuffer();
            for (Map.Entry<String,Integer> s : list) {
                sb.append(s.getKey());
                sb.append(" kommt ");
                sb.append(s.getValue());
                sb.append(" mal vor,\n");
            }
            sb.append("Ingesamt kommen ");
            sb.append(list.size());
            sb.append(" unterschiedliche und ");
            long anz = 0;
            for (Integer i : woerter.values())
                anz += i;
            sb.append(anz);
            sb.append(" Worte insgesamt vor.");
            System.out.println(sb);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

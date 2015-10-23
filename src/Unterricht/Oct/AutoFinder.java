package Unterricht.Oct;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mtheilen on 20.10.2015.
 */
public class AutoFinder {
    public static void main(String[] args) {
        List<String> ausg = autosToString(getAutoFiles("Y:\\3_XI\\XI_6\\302_SOP_OOP\\Autos\\"));
        ausg=autoBundler(ausg);
        System.out.println(ausg.get(0));
        System.out.println("-----------------------------------");
        System.out.println(ausg.get(27));
        System.out.println("-----------------------------------");
        System.out.println(ausg.get(51));


    }

    public static List<String> autoBundler(List<String> autos) {
        List<String> ret = new LinkedList<>();
        autos = new ArrayList<String>(autos);
        for (int index = 0; index < autos.size(); index++) {
            String ort = autos.get(index);
            if (ort.matches("^DE( )?-( )?\\d{5} .*$")) {
                StringBuilder toSave = new StringBuilder();
                toSave.append(autos.get(index - 1));
                toSave.append("\n");
                toSave.append(ort);
                toSave.append("\n");
                for (int i = index + 1; ; i++) {
                    String zeile = autos.get(i);
                    if (zeile.startsWith("Finanzierung")){
                        break;
                    }

                    else {
                        toSave.append(zeile);
                        toSave.append("\n");
                    }
                }
                ret.add(toSave.toString());
            }
        }


        return ret;
    }


    public static List<String> autosToString(List<File> autos) {
        List<String> ret = new LinkedList<>();

        for (File f : autos) {
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String read;
                while ((read = br.readLine()) != null) {
                    ret.add(read);
                }

            } catch (IOException e) {
                System.out.println("Dateifehler bei " + f.getName());
                ;
            }
        }
        return ret;
    }


    public static List<String> getAutoInfo(File autos) {
        Pattern p = Pattern.compile("(^(.*)(DE( )?-( )?\\d{5} \\b\\w+\\b)|^(.*)\\n(DE( )?-( )?\\d{5} \\b\\w+\\b))[\\w\\W]*?(Finanzierung)", Pattern.MULTILINE);
        List<String> ret = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(autos))) {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s);
                sb.append("\n");
            }
            Matcher m = p.matcher(sb);
            while (m.find()) {
                ret.add(m.group());
            }
        } catch (IOException e) {
            System.out.println("Dateifehler");
        }
        return ret;
    }


    public static List<File> getAutoFiles(String dirPath) {
        return getAutoFiles(new File(dirPath));
    }

    public static List<File> getAutoFiles(File dir) {
        File[] dateien = dir.listFiles();
        List<File> autos = new LinkedList<>();
        Pattern p = Pattern.compile("(Autos_)(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\b");
        for (File f : dateien)
            if (f.isFile() && (p.matcher(f.getName()).find()))
                autos.add(f);
        return autos;
    }


}

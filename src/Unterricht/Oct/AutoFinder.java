package Unterricht.Oct;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        File[] autos = getAutoFiles("Y:\\3_XI\\XI_6\\302_SOP_OOP\\Autos\\");
        System.out.println(autos.length);
        List<String> ausg = new ArrayList<>();
        for (File f : autos) {
            ausg.addAll(getAutoInfo(f));
        }


        for (String s : ausg) {
            System.out.println(s);
            System.out.println("--------------------------------------------------------------------");
        }
        System.out.println(ausg.size());

    }

    public static List<String> getAutoInfo(File autos) {
        Pattern p = Pattern.compile("(\\w{3,})(.)*\\n(.)*\\1((.)*\\n){0,12}(Finanzierung[, ]*Versicherung)$", Pattern.MULTILINE);
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


    public static File[] getAutoFiles(String dirPath) {
        return getAutoFiles(new File(dirPath));
    }

    public static File[] getAutoFiles(File dir) {
        File[] dateien = dir.listFiles();
        List<File> autos = new LinkedList<>();
        Pattern p = Pattern.compile("(Autos_)(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\b");
        for (File f : dateien)
            if (f.isFile() && (p.matcher(f.getName()).find()))
                autos.add(f);
        return autos.toArray(new File[autos.size()]);
    }


}

package Unterricht.Oct;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by mtheilen on 20.10.2015.
 */
public class AutoFinder {
    public static void main(String[] args) {
        List<String> ausg = autosToString(getAutoFiles("Y:\\3_XI\\XI_6\\302_SOP_OOP\\Autos\\"));
        ausg = autoBundler(ausg);
        for (String s:ausg)
            System.out.println(s);

        autoLister(ausg);


    }


    public static void autoLister(List<String> autos) {
        for (String autoS : autos) {
            StringBuilder out=new StringBuilder("Auto: ");
            String[] zerlegt= autoS.split("\n");
            out.append(zerlegt[0]);
            out.append("\n");
            out.append("Ort: ");
            out.append(zerlegt[1]);
            out.append("\n");
            out.append("Extras: ");
            for (int i = 2; i < zerlegt.length; i++) {
                out.append(zerlegt[i]);
                out.append(", ");
            }
            out.append("\n");


            System.out.println(out);
        }

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
                    if (zeile.startsWith("Finanzierung")) {
                        index = i;
                        break;
                    } else {
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
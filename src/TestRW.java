import java.io.*;
import java.io.BufferedReader;import java.io.BufferedWriter;import java.io.FileReader;import java.io.FileWriter;import java.io.IOException;import java.lang.String;import java.lang.System;import java.util.ArrayList;
import java.util.List;

/**
 * Created by mtheilen on 14.09.2015.
 */
public class TestRW {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Nationalhymne.txt"));

            String zeile = null;
            List<String> al = new ArrayList<>();
            while ((zeile = br.readLine()) != null)
                al.add(zeile);

            BufferedWriter bw = new BufferedWriter(new FileWriter("neue.txt"));

            for (String s : al) {
                bw.write(s);
                bw.newLine();
            }
            bw.close();


        } catch (IOException e) {
            System.out.println("Dateifehler");
        }

    }


}

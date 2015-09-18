import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import java.lang.System;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mtheilen on 14.09.2015.
 */
public class TestRW {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("Nationalhymne.txt")); BufferedWriter bw = new BufferedWriter(new FileWriter("neue.txt"));) {


            String zeile = null;
            List<String> al = new ArrayList<>();
            while ((zeile = br.readLine()) != null)
                al.add(zeile);


            for (int i = al.size() - 1; i >= 0; i--) {
                bw.write(al.get(i));
                bw.newLine();
            }


        } catch (IOException e) {
            System.out.println("Dateifehler");
        }

    }


}

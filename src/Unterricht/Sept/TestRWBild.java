package Unterricht.Sept;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by mtheilen on 18.09.2015.
 */
public class TestRWBild {
    public static void main(String[] args) {

        try (BufferedInputStream fis = new BufferedInputStream(new FileInputStream("Download.png"))) {

            byte[] b = new byte[16];
            int c = 0;
            while ((c = fis.read(b)) != -1) {
                System.out.println();
                for (int i = 0; i < c; i++) {
                    int hexb = b[i]&255;
                    String hex = Integer.toHexString(hexb).toUpperCase();
                    if (hex.length() == 1) hex = 0 + hex;
                    System.out.print(hex + " ");
                }
            }


        } catch (IOException e) {
            System.out.println("Dateifehler");
        }


    }
}

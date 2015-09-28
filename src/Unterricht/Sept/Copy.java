package Unterricht.Sept;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mtheilen on 25.09.2015.
 */
public class Copy {
    public static void main(String[] args) {
        File in = new File("input.bmp");
        File out = new File("output.bmp");

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(in)); BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(out))) {
            int c=0;
            byte[] b=new byte[1024];
            List<byte[]> ba=new ArrayList<>();
            while ((c=bis.read(b))!=-1) {
                byte[] toStore = new byte[c];
                for (int i = 0; i < c; i++)
                    toStore[i]=b[i];
                ba.add(toStore);
            }

            for (byte[] bout:ba){
                bos.write(bout);
            }




        } catch (IOException e) {
            System.out.println("Dateifehler");
        }

    }


}

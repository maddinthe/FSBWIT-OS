package TestSachen;

import java.io.File;
import java.util.Arrays;

/**
 * Created by Martin on 19.10.2015.
 */
public class FileTest {
    public static void main(String[] args) {
        File dir = new File("C:\\Video\\backup");
        File[] files=dir.listFiles();

        for (File f:files){
            if (f.isFile())
                System.out.println(f.getName());
        }
    }
}

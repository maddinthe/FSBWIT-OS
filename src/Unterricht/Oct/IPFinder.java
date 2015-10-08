package Unterricht.Oct;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mtheilen on 08.10.2015.
 */
public class IPFinder {

    public static void main(String[] args) {


    try(BufferedReader br= new BufferedReader(new FileReader("./Testdaten/neu.log"))){
        String read;
        Pattern p= Pattern.compile("((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
        List<String> ips=new LinkedList<>();
        while ((read=br.readLine())!=null){
            Matcher m=p.matcher(read);
            while (m.find()){
                ips.add(m.group());
            }
        }

        System.out.println(ips.size());


    }
    catch(IOException e){
        System.out.println("Dateifehler");
    }


    }
}

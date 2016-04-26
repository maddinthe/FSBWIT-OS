package programm;

import db.Datenbank;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * Created by mtheilen on 04.03.2016.
 */
public class Programm {
    public Programm() {
        Datenbank db = null;
        try {
            db = Datenbank.getInstance();
        } catch (ClassNotFoundException e) {
            System.out.println("Datenbanktreiber nicht gefunden!");
        } catch (SQLException e) {
            if(e.getMessage().equals("Datenbank existiert nicht!"))
                try{
                    db=Datenbank.getInstance("test");
                }
                catch (SQLException e1){

                }
        }
        try {
            db.dropIfExists("blob");
            db.dropIfExists("test");
            db.createTable("test");
            db.insertOrUpdate("Theilen", 1234);
            db.insertOrUpdate("Dreher", -1234);
            db.insertOrUpdate("Kertz", 2345);
            db.insertOrUpdate("Kertz", 123);

            db.createTableMitBlob("blob");
            try {
                FileInputStream fis = new FileInputStream(".\\db-test\\bild.jpg");
                db.instertOrUpdateBlob("Theilen", fis);
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                FileInputStream fis = new FileInputStream(".\\db-test\\bild.jpg");
                db.instertOrUpdateBlob("Theilen", fis);
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Icon icon = new ImageIcon(ImageIO.read(db.getBlob("Theilen")));
                JOptionPane.showMessageDialog(null, "Tooles Bild aus DB", "Hallo", JOptionPane.INFORMATION_MESSAGE, icon);
            } catch (IOException e) {
                e.printStackTrace();
            }


            db.printTable("test");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

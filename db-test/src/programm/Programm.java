package programm;

import db.Datenbank;

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
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        try{
            db.dropIfExists("blob");
            db.dropIfExists("test");
            db.createTable("test");
            db.createTableMitBlob("blob");

            db.insertOrUpdate("Theilen",1234);
            db.insertOrUpdate("Dreher",-1234);
            db.insertOrUpdate("Kertz",2345);
            db.insertOrUpdate("Kertz",123);
            db.printTable("test");
        }catch(SQLException e){
                e.printStackTrace();
        }
    }
}

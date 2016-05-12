package umlGrund.db;

import java.sql.Connection;

/**
 * Created by mtheilen on 09.05.2016.
 */
public class Datenbank {
    private Datenbank datenbank;
    private Connection connection;

    public static Datenbank getInstance() {
        return new Datenbank();
    }

    public static Datenbank getInstance(String dbname) {
        return new Datenbank();
    }
    private void einlesenScript(){

    }
    public Object[] abfragen(){
        return new Object[1];
    }
}

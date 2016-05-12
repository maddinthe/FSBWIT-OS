package umlGrund.db;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by mtheilen on 04.03.2016.
 */
public class Datenbank {

    private static Datenbank datenbank;
    private static Connection conn;

    private Datenbank() {
    }

    public static Datenbank getInstance() throws ClassNotFoundException, SQLException {
        if (datenbank == null) {
            Class.forName("org.postgresql.Driver");
            datenbank = new Datenbank();
        }
        boolean renew = conn == null;
        if (!renew)
            try {
                if (conn.isClosed()) {
                    renew = true;
                }
            } catch (SQLException e) {
                renew = true;
            }
        if (renew) {
            String host = "localhost";
            int port = 5432;
            String database = "test";
            try {
                Socket socket = new Socket(host, port);
                socket.close();
            } catch (IOException e) {
                throw new SQLException("Server nicht erreichbar", "08001", e);
            }
            String url = "jdbc:postgresql://" + host + ":" + port + "/";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "root");
            try {
                conn = DriverManager.getConnection(url, props);
                conn.close();
            } catch (SQLException e) {
                throw new SQLException("Zugriff verweigert!", e.getSQLState(), e);
            }
            try {
                conn = DriverManager.getConnection(url + database, props);
            } catch (SQLException e) {
                throw new SQLException("Datenbank existiert nicht!", e.getSQLState(), e);
            }
        }
        return datenbank;
    }
    public static Datenbank getInstance(String database)  throws SQLException {
        String host="localhost";
        int port=5432;
        String url="jdbc:postgresql://"+host+":"+port+"/";
        Properties props =new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password","root");
        try {
            conn=DriverManager.getConnection(url,props);
            conn.createStatement().executeUpdate(
                    "CREATE DATABASE "+database);
        }catch (SQLException e){
            throw new SQLException("Zugriff verweigert", e.getSQLState(), e);
        }
        try{
            getInstance();
        }catch (ClassNotFoundException e){}
        einlesenScript();
        return datenbank;
    }

    private static void einlesenScript() throws SQLException {
        try (BufferedReader br=new BufferedReader(new FileReader(Datenbank.class.getResource("init.sql").getFile()))){
            String sqlInsutction="";
            String zeile;
            while((zeile=br.readLine())!=null){
                zeile=zeile.split("--")[0];
                sqlInsutction+=zeile+" ";
                if (sqlInsutction.trim().endsWith(";")){
                    Statement stmt = conn.createStatement();
                    stmt.execute(sqlInsutction);
                    sqlInsutction="";
                }
            }

        }catch (IOException e){}
    }

    public void createTable(String tableName) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
                "CREATE TABLE " + tableName + " (" +
                        " name VARCHAR," +
                        " number INT," +
                        "PRIMARY KEY (name)" +
                        ")"
        );
    }


    /**
     * Diese Methode löscht eine Tabelle, falls sie existiert, andernfalls tut sie nichts.
     *
     * @param tableName Der Name der Tabelle, die gelöscht werden soll.
     * @throws SQLException Wenn beim Erstellen der Verbindung ein Fehler passiert.
     */
    public void dropIfExists(String tableName) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("DROP TABLE " + tableName);
        } catch (SQLException e) {
            if (!e.getSQLState().equals("42P01")) e.printStackTrace();
        }
    }

    public void insertOrUpdate(String name, int number) throws SQLException {
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate("INSERT INTO test" +
                    " VALUES ('" + name + "', " + number + ")");
        } catch (SQLException e) {
            stmt.executeUpdate("UPDATE test " +
                    " SET number=" + number +
                    " WHERE name='" + name + "'");
        }
    }

    public void printTable(String tableName) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery(
                "SELECT * FROM " + tableName
        );
        ResultSetMetaData rm = r.getMetaData();
        //Tabellenkopf->Spaltennamen
        int col = rm.getColumnCount();
        int[] max = new int[col];
        List<List<String>> tabelle = new ArrayList<>(col);
        for (int i = 0; i < col; i++) {
            List<String> spalte = new ArrayList<>();
            String s = rm.getColumnLabel(i + 1);
            spalte.add(s);
            tabelle.add(spalte);
            max[i] = s.length();
        }
        //Tabelleneinträge
        if (r.next())
            do {
                for (int i = 0; i < col; i++) {
                    String s = r.getString(i + 1);
                    if (s == null) s = "null";
                    tabelle.get(i).add(s);
                    max[i] = Math.max(max[i], s.length());
                }
            } while (r.next());
        r.close();
        //ausgabe
        for (int i = 0; i < tabelle.get(0).size(); i++) {
            String s = "";
            for (int j = 0; j < col; j++) {
                s += "|" + String.format("%-" + max[j] + "s", tabelle.get(j).get(i));
            }
            System.out.println(s + "|");
        }


    }

    public void createTableMitBlob(String tableName) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
                "CREATE TABLE " + tableName + " (" +
                        " name VARCHAR," +
                        " blob BYTEA," +
                        " PRIMARY KEY (name)," +
                        " FOREIGN KEY (name) REFERENCES test(name)" +
                        ")"
        );
    }

    public void instertOrUpdateBlob(String name, InputStream is) throws SQLException {

        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("SELECT name FROM blob WHERE name='" + name + "'");
        PreparedStatement ps;
        if (r.next()) {
            ps = conn.prepareCall("UPDATE blob SET blob =? WHERE name ='" + name + "'");
            ps.setBinaryStream(1, is);
            ps.executeUpdate();
        } else {
            ps = conn.prepareStatement("INSERT INTO blob VALUES (?,?)");
            ps.setBinaryStream(2, is);
            ps.setString(1, name);
            ps.execute();
        }
    }

    public InputStream getBlob(String name) throws SQLException {
        Statement stmn = conn.createStatement();
        ResultSet r = stmn.executeQuery(
                "SELECT blob" +
                        " FROM blob" +
                        " WHERE name='" + name + "'"
        );
        if (r.next())  //!!wichtig!!
            return r.getBinaryStream(1);
        return null;
    }


}

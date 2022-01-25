/*
 * Made by Andrea Mate.
 * For practice.
 * This is the way!
 */
package szotar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import panel.Panel;

/* @author Máté Andrea */
public class DB {

    final String db = "jdbc:mysql://localhost:3306/szotar?useUnicode=true&characterEncoding=UTF-8";
    final String user = "tanulo";
    final String pass = "tanulo";

    public void beolvas(ObservableList<Szo> tabla, String s) {
        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            tabla.clear();
            ResultSet eredmeny = ekp.executeQuery();
            while (eredmeny.next()) {
                tabla.add(new Szo(
                        eredmeny.getInt("szoID"),
                        eredmeny.getString("lecke"),
                        eredmeny.getString("angol"),
                        eredmeny.getString("magyar")));
            }
        } catch (SQLException e) {
            Panel.hiba("Hiba", e.getMessage());
            Platform.exit();
        }
    }

    public int hozzaad(String lecke, String angol, String magyar) {
        String s = "insert into szavak(lecke,angol,magyar) values(?,?,?);";
        try (Connection kapcs = DriverManager.getConnection(db, user, pass);
                PreparedStatement ekp = kapcs.prepareStatement(s)) {
            ekp.setString(1, lecke);
            ekp.setString(2, angol);
            ekp.setString(3, magyar);
            return ekp.executeUpdate();
        } catch (SQLException e) {
            Panel.hiba("Hozzáadás", e.getMessage());
            return 0;
        }
    }

    public int modosit(int id,String lecke,String angol,String magyar){
        String s="update szavak set lecke=?, angol=?, magyar=? where szoID=?;";
        try (Connection kapcs=DriverManager.getConnection(db,user,pass);
                PreparedStatement ekp=kapcs.prepareStatement(s)) {
            ekp.setString(1, lecke);
            ekp.setString(2, angol);
            ekp.setString(3, magyar);
            ekp.setInt(4, id);
            return ekp.executeUpdate();
        } catch (SQLException e) {
            Panel.hiba("Módosítás", e.getMessage());
            return 0;
        }
    }
    public int torol(int id){
        String s="delete from szavak where szoID=?;";
        try (Connection kapcs=DriverManager.getConnection(db,user,pass);
                PreparedStatement ekp=kapcs.prepareStatement(s)) {
            ekp.setInt(1, id);
            return ekp.executeUpdate();
        } catch (Exception e) {
            Panel.hiba("Törlés", e.getMessage());
            return 0;
        }
    }
}

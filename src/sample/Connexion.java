package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    final static String url="jdbc:oracle:thin:@localhost:1521:XE";
    final static String name="system";
    final static String password="medyassine1997";
    static Connection con;
    static Connexion ins;
    public static Connection getCon() {
        return con;
    }
    private Connexion() {
        try {
            con=DriverManager.getConnection(url,name,password);
            System.out.println("connexion �tablie");
        }
        catch (SQLException ex)
        {
            System.out.println("erreur: "+ex.getMessage());
        }
    }
   public static Connexion getInstance()
    {
        if (ins==null)
        {
            ins=new Connexion();
        }
        return ins;
    }


}

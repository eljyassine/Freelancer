package dbr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Technologie;
import sample.Connexion;

public class TechnologieDBR {

    Connection con=Connexion.getInstance().getCon();


    public  List<Technologie> selectBy(String attribute, String value) {
        try {
            String sql="SELECT * FROM * WHERE "+attribute+"=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,value);
            ResultSet rs = pstmt.executeQuery();
            List<Technologie> techList = new ArrayList<>();
            while(rs.next()){
                Technologie technologie = new Technologie();
                technologie.setId(rs.getInt("id"));
                technologie.setNomTechnologie(rs.getString("nomTechnologie"));
                technologie.setCategorie_tech_id(rs.getInt("Categorie_tech_id"));
                techList.add(technologie);
            }
            return  techList;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    public List<Technologie> selectAll() {
        try {
            String sql="SELECT * FROM technologie";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<Technologie> techList = new ArrayList<>();
            while(rs.next()){
                Technologie technologie = new Technologie();
                technologie.setId(rs.getInt("id"));
                technologie.setNomTechnologie(rs.getString("nomTechnologie"));
                technologie.setCategorie_tech_id(rs.getInt("Categorie_tech_id"));
                techList.add(technologie);
            }
            return techList;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}



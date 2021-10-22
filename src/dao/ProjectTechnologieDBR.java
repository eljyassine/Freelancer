package dbr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Project;
import models.Technologie;
import models.User;
//import services.offres.projet.ServiceProjet;
import sample.Connexion;

public class ProjectTechnologieDBR {

    Connection con=Connexion.getInstance().getCon();

    public void add(Project projet, Technologie tech) {
        try {

            String sql="INSERT INTO projet_technologie(projet_id,technologie_id) VALUES(?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,Integer.toString(projet.getId()));
            pstmt.setString(2,Integer.toString(tech.getId()));
            pstmt.executeUpdate();
            System.out.println("Technologie applique au projet"+projet.getNomProjet()+" avec success");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Project projet) {
        try {
            String sql="DELETE FROM projet_technologie WHERE projet_id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,projet.getId());
            pstmt.executeUpdate();
            System.out.println("Technologie supprime avec success");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Project> getProjetByTech(Technologie t) {
        try {
            ProjectDBR projectDBR = new ProjectDBR();
            String sql="SELECT * FROM projet_technologie WHERE technologie_id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,Integer.toString(t.getId()));
            ResultSet rs = pstmt.executeQuery();
            List<Project> projetList = new ArrayList<>();
            while(rs.next()){
                projetList.add(projectDBR.selectBy("id", rs.getString("projet_id")).get(0));
            }
            return  projetList;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Technologie> getTechsByProjet(Project p) {
        try {
            //ServiceProjet serviceProjet = new ServiceProjet();
            TechnologieDBR technologieDBR = new TechnologieDBR();
            ProjectDBR projectDBR = new ProjectDBR();
            models.Client client = new models.Client(2);
            String sql="SELECT * FROM projet_technologie WHERE projet_id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,Integer.toString(p.getId()));
            ResultSet rs = pstmt.executeQuery();
            List<Technologie> techList = new ArrayList<>();
            while(rs.next()){
                techList.add(technologieDBR.selectBy("id", rs.getString("technologie_id")).get(0));
            }
            return  techList;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

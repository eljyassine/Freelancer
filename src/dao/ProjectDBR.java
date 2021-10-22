package dbr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dbr.UserDb;
import models.Client;
import models.Project;
import models.User;
import sample.Connexion;

public class ProjectDBR {
    UserDb userDb= new UserDb();
    Connection con=Connexion.getInstance().getCon();

    public void add(Project projet)  {
        try {
            String sql="INSERT INTO projet(id,client_id,nomProjet,TitreProjet,DateDebut,dateFin,dateRealisation,"
                    + "dateCreation,datePublication,description,publie,valide,cout) VALUES( DEFAULT,?,?,?,?,?,?,?,?,?,?,?,?)";


            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,Integer.toString(projet.getClient().getIdClient()));
            pstmt.setString(2,projet.getNomProjet());
            pstmt.setString(3,projet.getTitreProjet());

            String pattern = "yyyy/MM/dd";
            DateFormat df = new SimpleDateFormat(pattern);
            String dateDebutSql= df.format(projet.getDateDebut());
            String dateFinSql= df.format(projet.getDateFin());

            Date today = Calendar.getInstance().getTime();
            String todayAsString = df.format(today);

            pstmt.setString(4,dateDebutSql);
            pstmt.setString(5,dateFinSql);
            pstmt.setString(6,null);
            pstmt.setString(7,todayAsString);
            pstmt.setString(8,null);
            pstmt.setString(9,projet.getDescription());
            pstmt.setString(10,(projet.isPublie() ? "1": "0"));
            pstmt.setString(11,"1");
            pstmt.setString(12,Double.toString(projet.getCout()));
            pstmt.executeUpdate();
            System.out.println("Produit Ajoute avec success");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Project projet) {
        try {

            String sql="UPDATE projet SET nomProjet=?,TitreProjet=?,DateDebut=?,"
                    + "dateFin=?,dateRealisation=?,datePublication=?,"
                    + "description=?,publie=?,valide=?,cout=? WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            String pattern = "yyyy/MM/dd";
            DateFormat df = new SimpleDateFormat(pattern);
            String dateDebutSql= df.format(projet.getDateDebut());
            String dateFinSql= df.format(projet.getDateFin());
            String datePublicationsql = (projet.getDatePublication()==null)? null : df.format(projet.getDatePublication());
            String dateRealistationsql = (projet.getDateCreation()==null)? null : df.format(projet.getDateCreation());


            pstmt.setString(1,projet.getNomProjet());
            pstmt.setString(2,projet.getTitreProjet());
            pstmt.setString(3,dateDebutSql);
            pstmt.setString(4,dateFinSql);
            pstmt.setString(5,dateRealistationsql);
            pstmt.setString(6,datePublicationsql);
            pstmt.setString(7,projet.getDescription());
            pstmt.setString(8,(projet.isPublie() ? "1": "0"));
            pstmt.setString(9,(projet.isValide() ? "1": "0"));
            pstmt.setString(10,Double.toString(projet.getCout()));
            pstmt.setString(11,Integer.toString(projet.getId()));
            //
            pstmt.executeUpdate();
            System.out.println("Produit modifie avec success");
            System.out.println(pstmt.toString());
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  List<Project> selectBy(String attribute, String value) {
        try {

            models.Client client = new models.Client(2);
            String sql="SELECT * FROM projet WHERE "+attribute+"=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,value);
            ResultSet rs = pstmt.executeQuery();
            List<Project> projetList = new ArrayList<>();
            while(rs.next()){
                Project projet = new Project();
                projet.setId(rs.getInt(1));
                projet.setClient((Client) userDb.selectBy("id", rs.getString("client_id")).get(0));
                projet.setNomProjet(rs.getString("nomProjet"));
                projet.setTitreProjet(rs.getString("TitreProjet"));
                projet.setDateDebut(rs.getDate("DateDebut"));
                projet.setDateFin(rs.getDate("DateFin"));
                projet.setDateRealisation(rs.getDate("dateRealisation"));
                projet.setDateCreation(rs.getDate("dateCreation"));
                projet.setDatePublication(rs.getDate("datePublication"));
                projet.setDescription(rs.getString("description"));
                projet.setPublie(rs.getBoolean("publie"));
                projet.setValide(rs.getBoolean("valide"));
                projet.setCout(rs.getDouble("cout"));
                projetList.add(projet);
            }
            return  projetList;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void delete(Project projet) {
        try {
            String sql="DELETE FROM projet WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,projet.getId());
            pstmt.executeUpdate();
            System.out.println("Produit supprime avec success");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Project> selectAll() {
        try {
            models.Client client = new models.Client(2);
            String sql="SELECT * FROM `projet`";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<Project> projetList=new ArrayList<>();
            while(rs.next()){
                Project projet = new Project();
                projet.setId(rs.getInt(1));
                projet.setClient(client); // !!!! a changer
                projet.setNomProjet(rs.getString("nomProjet"));
                projet.setTitreProjet(rs.getString("TitreProjet"));
                projet.setDateDebut(rs.getDate("DateDebut"));
                projet.setDateFin(rs.getDate("DateFin"));
                projet.setDateRealisation(rs.getDate("dateRealisation"));
                projet.setDateCreation(rs.getDate("dateCreation"));
                projet.setDatePublication(rs.getDate("datePublication"));
                projet.setDescription(rs.getString("description"));
                projet.setPublie(rs.getBoolean("publie"));
                projet.setValide(rs.getBoolean("valide"));
                projet.setCout(rs.getDouble("cout"));
                projetList.add(projet);
            }
            return projetList;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
/*
    public Project selectLast() {
        try {
            String sql="SELECT * FROM projet WHERE id=max(id)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<Project> projetList=new ArrayList<>();
            while(rs.next()){
                Project projet = new Project();
                projet.setId(rs.getInt("id"));
                System.out.println(projet.getId());
                projet.setClient((Client) UserDb.selectBy("client_id", rs.getString("client_id")).get(0));
                projet.setNomProjet(rs.getString("nomProjet"));
                projet.setTitreProjet(rs.getString("TitreProjet"));
                projet.setDateDebut(rs.getDate("DateDebut"));
                projet.setDateFin(rs.getDate("DateFin"));
                projet.setDateRealisation(rs.getDate("dateRealisation"));
                projet.setDateCreation(rs.getDate("dateCreation"));
                projet.setDatePublication(rs.getDate("datePublication"));
                projet.setDescription(rs.getString("description"));
                projet.setPublie(rs.getBoolean("publie"));
                projet.setValide(rs.getBoolean("valide"));
                projet.setCout(rs.getDouble("cout"));
                projetList.add(projet);
            }
            return projetList.get(0);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/
}



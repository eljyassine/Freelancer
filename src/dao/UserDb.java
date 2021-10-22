package dbr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;

import sample.Connexion;


public class UserDb {
   static private  String  s;
    Connection con=Connexion.getInstance().getCon();

    public void add(User user) throws SQLException {
        String sql = "INSERT INTO userinfo (username,firstName,lastName,email,password,dateNaissance,imagename,competance,role,id) VALUES (?,?,?,?,?,?,?,?,?,ma_sequence.nextval)";
        PreparedStatement pstmt = con.prepareStatement(sql);


        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getFirstName());
        pstmt.setString(3, user.getLastName());
        pstmt.setString(4, user.getEmail());
        pstmt.setString(5, user.getPassword());
        pstmt.setString(6, user.getDateNaissance());
        pstmt.setString(7, user.getImagename());
        pstmt.setString(8, user.getCompetance());
        pstmt.setString(9, user.getRole());
        pstmt.executeUpdate();


        //System.out.println( pstmt.executeUpdate());
    }
    public List<User> selectByid(String attribute,int value) throws SQLException {
        String sql="SELECT * FROM userinfo where "+attribute+"=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,value);
        ResultSet rs = pstmt.executeQuery();
        List<User> userList=new ArrayList<>();
        while(rs.next()){
            User user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setDateNaissance(rs.getString("datenaissance"));
            user.setImagename(rs.getString("imagename"));
            user.setCompetance(rs.getString("competance"));
            user.setRole (rs.getString("role"));
            userList.add(user);
        }
        return  userList;
    }


    public List<User> selectBy(String attribute,String value) throws SQLException {
        String sql="SELECT * FROM userinfo where "+attribute+"=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,value);
        ResultSet rs = pstmt.executeQuery();
        List<User> userList=new ArrayList<>();
        while(rs.next()){
            User user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setDateNaissance(rs.getString("datenaissance"));
            user.setImagename(rs.getString("imagename"));
            user.setCompetance(rs.getString("competance"));
            user.setRole (rs.getString("role"));
            userList.add(user);
        }
        return  userList;
    }

    public void update(User user) throws SQLException {
        String sql = "UPDATE userinfo SET   username=?,firstName=?,lastName=?,email=?,password=?,dateNaissance=?,imagename=?,competance=?,role=? WHERE id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getFirstName());
        pstmt.setString(3, user.getLastName());
        pstmt.setString(4, user.getEmail());
        pstmt.setString(5, user.getPassword());
        pstmt.setString(6, user.getDateNaissance());
        pstmt.setString(7, user.getImagename());
        pstmt.setString(8, user.getCompetance());
        pstmt.setString(9, user.getRole());
        pstmt.setInt(10, user.getId());

        pstmt.executeUpdate();
    }
    public void delete(int id) throws SQLException
    {
        String sql = "DELETE FROM userinfo WHERE id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

    public void updatePassword( User user) throws SQLException {

        String sql="UPDATE userinfo SET password=? WHERE id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,user.getPassword());
        pstmt.setInt(2,user.getId());
        pstmt.executeUpdate();
        System.out.println(user.getPassword());
    }


    public List<User> selectAll() throws SQLException {
        String sql = "SELECT * FROM userinfo where role like 'Freelancer' or role  like 'Client'";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        //User user = new User();
        List<User> userList = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setUsername(rs.getString(2));
            user.setFirstName(rs.getString(3));
            user.setLastName(rs.getString(4));
            user.setEmail(rs.getString(5));
            user.setPassword(rs.getString(6));
            user.setDateNaissance(rs.getString(7));
            user.setImagename(rs.getString(8));
            user.setCompetance(rs.getString(9));
            user.setRole(rs.getString(10));
            System.out.println(user);
            userList.add(user);
        }
        return userList;
    }
    public List<User> selectRole(String Role) throws SQLException {
        String sql = "SELECT * FROM userinfo where role =?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, Role);
        ResultSet rs = pstmt.executeQuery();
        //User user = new User();
        List<User> userList = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setUsername(rs.getString(2));
            user.setFirstName(rs.getString(3));
            user.setLastName(rs.getString(4));
            user.setEmail(rs.getString(5));
            user.setPassword(rs.getString(6));
            user.setDateNaissance(rs.getString(7));
            user.setImagename(rs.getString(8));
            user.setCompetance(rs.getString(9));
            user.setRole(rs.getString(10));
            System.out.println(user);
            userList.add(user);
        }
        return userList;
    }



   /*  public static void main(String[] args) throws SQLException {
      User u=new User("amentb","amen","talel","amental@gmail.com","2002","05/06/2001","eezezrrdcvsdf","aasdfddsggeerreffrezaaaaaaa",3);
        UserDb data = new UserDb();
        data.updatePassword(u);


        // int i=55;  UserDb data = new UserDb();data.delete(i);

       User u=new User("youssefr","tubel","toufa","yoseftoufa@gmail.com","0000","05/08/2008","eezezrrdcvsdf","aasdfddsggeerreffrezaaaaaaa","freelancer");
       UserDb data = new UserDb();
       data.add(u);
        System.out.println("gggggggggggggggggggggggg");

      List<User> userList = data.selectBy("firstname", "yassine");
        if (userList.size() != 0) {
            User user = userList.get(0);
            s =  user.getPassword();}
        System.out.println(s);}
*/




}

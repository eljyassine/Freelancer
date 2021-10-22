package controllers.utilisateurs;

import dbr.UserDb;
import models.User;
import sample.UserSession;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserDb data;
    public UserService() {
        data = new UserDb();
    }


    public int authentification (String username,String passwd) throws SQLException {
        List<User> userList=data.selectBy("username",username);
        if  (userList.size() !=0){
            User user = userList.get(0);
            System.out.println(user);

                if (passwd.equals(user.getPassword())) {
                    UserSession userSession=UserSession.getInstace(user);

                    if(user.getRole().equals("Freelancer")){

                        return 3;
                    }else if(user.getRole().equals("Client")){
                        return 2;}
                    else return 4;
                }
                else return 1;

        }else {return 0;}

    }






    public int checkForUser(User user) throws SQLException {


        if (data.selectBy("username",user.getUsername()).size() != 0) return 0;

        else if (data.selectBy("email",user.getEmail()).size() != 0) return 1;

        else  {UserDb data=new UserDb(); ;data.add(user); return 2; }
    }




    public void updateUserProfile(User user) throws SQLException {
        data.update(user);
    }

    public boolean usernameIsAvailable(String username) throws SQLException {
        return (data.selectBy("username",username).size() == 0) ? true : false;
    }



    public boolean emailIsAvailable(String email) throws SQLException {
        return (data.selectBy("email",email).size() != 0) ? true : false;
    }


    public void editUserPassword(User user) throws SQLException {
        user.setPassword(user.getPassword());
        data.updatePassword(user);
    }

    public User selectUserBymail(String email) throws SQLException {
       return data.selectBy("email",email).get(0);
    }


    /*
   public static void main(String[] args) throws SQLException {
       UserService u =new  UserService();
        int x = u.authentification("yassineelj","0000" );
    System.out.println(x);
    }
*/
}
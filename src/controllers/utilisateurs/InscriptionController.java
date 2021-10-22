package controllers.utilisateurs;

import models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
 import javafx.scene.Node;
 import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
 import java.sql.SQLException;


public class InscriptionController  {

    @FXML
    Button btnInscription;
      @FXML
    TextField txtEmail;
    @FXML
    TextField txtPseudo;
    @FXML
    TextField txtPassword;
    @FXML
    TextField txtPasswordConf;
    @FXML
    RadioButton radioRoleFreelc;

    @FXML
    TextField txtFirstName;
    @FXML
    TextField txtLastName;
    @FXML
    Label lblCtrUsername,lblCtrEmail,lblCtrFirstName,lblCtrLastName,lblCtrPasswd,lblCtrPasswd2;
    @FXML
    Pane pane1,pane2;
    @FXML
    Button btnAddInfos,btnSkip;

    private static User user;

    public InscriptionController() {
        user=new User();
    }



    @FXML
    public void handleBtnAcceuil(MouseEvent event){
        loadPage(event,"/views/utilisateurs/Authentification.fxml");
    }



    @FXML
    public void handleBtnEditBasicInfo(MouseEvent event){
        loadPage(event,"/views/utilisateurs/EditBasicInformations.fxml");
    }




    @FXML
    public void handleBtnSkip(MouseEvent event){
        if (user.getRole().equals("Freelancer")) loadPage(event, "/views/utilisateurs/DashboardFreelancer.fxml");
        else loadPage(event, "/views/utilisateurs/DashboardEmbaucheur.fxml");
    }

    @FXML

    public void  handleBtnInscription() throws SQLException {

        String username=txtPseudo.getText();
        String email=txtEmail.getText();
        String password=txtPassword.getText();
        String passwordConf=txtPasswordConf.getText();
        String firstName=txtFirstName.getText();
        String lastName=txtLastName.getText();
        String role=(radioRoleFreelc.isSelected())? "Freelancer" : "Client";


        if (isValidUsername(username) && isValidemail(email) && isValidName(firstName) && isValidPname(lastName) && isValidPassword(password,passwordConf)){
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);
            user.setFirstName(firstName);
            user.setLastName(lastName);


            UserService userService = new UserService();
            switch (userService.checkForUser(user)){
                case 0: lblCtrUsername.setText("le nom d'utilisateur est deja utilisé "); break;
                case 1: lblCtrEmail.setText("email est deja utilisé"); lblCtrUsername.setText(""); break ;
                case 2:  pane2.toFront(); //return user;
            }
        }
     //   return null;
    }




    @FXML
    public void handleBtnRetour(){ pane1.toFront();    }

    private void loadPage(MouseEvent event,String pageName){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene= new Scene(FXMLLoader.load(getClass().getResource(pageName)));
            stage.setScene(scene);
            stage.show();

        } catch (    IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private boolean isValidPassword(String pass1,String pass2){
        if (!(pass1.length()>6 && pass1.matches("[a-zA-Z0-9]+"))){
            lblCtrPasswd.setText("Mot de passe doit avoir au moins 6 caracteres, ne doit pas contenir de caractères spéciaux.");
            lblCtrPasswd2.setText("");
            return false;
        }
        else if( ! pass1.equals(pass2)){
            lblCtrPasswd.setText("");
            lblCtrPasswd2.setText("Mot de passe ne correspond pas");
            return false;
        }
        else{
            lblCtrPasswd2.setText("");
            lblCtrPasswd.setText("");
            return true;
        }
    }

    private boolean isValidUsername(String username){
        if (!(username.length()>6 && username.matches("[a-zA-Z][a-zA-Z0-9]+"))){
            lblCtrUsername.setText("le nom d'utilisateur doit contenir au moins 6 lettres, ne doit pas contenir de caractères spéciaux. ");
            return false;
        }
        else{
            lblCtrUsername.setText("");
            return true;
        }
    }

    private boolean isValidemail( String email){
        if (1==1){
            lblCtrEmail.setText("");
            return true;
        }
        else {
            lblCtrEmail.setText("email invalide");
            return false;
        }
    }

    private boolean isValidName( String name){
        if (name.matches("[a-zA-Z]+")){
            lblCtrFirstName.setText("");
            return true;
        }
        else {
            lblCtrFirstName.setText("Le nom doit contenir seulement des caracteres");
            return false;
        }
    }

    private boolean isValidPname( String name){
        if (name.matches("[a-zA-Z]+")){
            lblCtrLastName.setText("");
            return true;
        }
        else {
            lblCtrLastName.setText("Le nom doit contenir seulement des caracteres");
            return false;
        }
    }






}

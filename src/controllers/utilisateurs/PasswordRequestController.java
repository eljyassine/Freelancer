package controllers.utilisateurs;

import models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;


public class PasswordRequestController {

    @FXML
    TextField txtEmail;
    @FXML
    TextField txtPassword;
    @FXML
    TextField txtPasswordConf;
    @FXML
    TextField txtConfirmaCompte;
    @FXML
    Label lblCtrEmail,lblCtrPasswd,lblCtrPasswd2,lblVerifCpt;
    @FXML
    Pane pane1,pane3,pane4;

    private User user=new User();
    private UserService userService = new UserService();


    @FXML
    public void handleBtnAcceuil(MouseEvent event){
        loadPage(event,"/views/utilisateurs/Authentification.fxml");
    }

    @FXML
    public void handleBtnEditPassword() throws SQLException {
        if(isValidPassword(txtPassword.getText(),txtPasswordConf.getText())){
            user.setPassword(txtPassword.getText());
            userService.editUserPassword(user);
            pane4.toFront();
        }
    }

    @FXML
    public void handleBtnRetour(){pane1.toFront();}

    public void handleBtnVerifierCompte() {
    }

    @FXML
    public void handleBtnValiderEmail() throws SQLException {
        String email=txtEmail.getText();

        if (isValidemail(email)){

            if ( userService.emailIsAvailable(email)){
                user=userService.selectUserBymail(email);
                pane3.toFront();
            }else lblCtrEmail.setText("Cette adresse email n'appartient a aucun utilisateur");
        }
        }



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
}

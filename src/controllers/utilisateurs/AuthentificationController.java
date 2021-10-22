package controllers.utilisateurs;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.sql.SQLException;


public class AuthentificationController {



    @FXML
    private BorderPane loginBorderPane;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPasswd;
    @FXML
    private Label lblWarning;

    @FXML
    UserService userService;

    public AuthentificationController() {
        userService=new UserService();

    }


    @FXML
    public void handleBtnInscription() {
        loadGui("utilisateurs/Inscription.fxml");
    }

    @FXML
    public void handleBtnAuth() throws SQLException {
        String username=txtUsername.getText();
        String passwd=txtPasswd.getText();
        UserService userService=new UserService();

        switch (userService.authentification(username,passwd)){
            case 0: lblWarning.setText("Utilisateur n'existe pas."); break;
            case 1: lblWarning.setText("Mot de passe est incorrect."); break;
            case 2: loadGui("utilisateurs/DashboardEmbaucheur.fxml"); break;
            case 3: loadGui("utilisateurs/DashboardFreelancer.fxml");break;
            case 4: loadGui("admin/AdminDashboard.fxml"); break;

        }
    }





    @FXML
    public void loadGui( String gui) {
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/"+gui));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        loginBorderPane.setCenter(root);
    }


    @FXML
    public void handleBtnPasswordForgotten(MouseEvent event){
        loadGui( "utilisateurs/PasswordRequest.fxml");
    }

}



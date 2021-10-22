package controllers.admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import controllers.admin.serviceadmine;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;


import dbr.UserDb;
import java.sql.SQLException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import sample.UserSession;


public class AdminDashboardController {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button CompBtn;
    @FXML
    Button LangBtn;
    @FXML
    Button UserBtn;
    @FXML
    Button Logout;
    @FXML
    AnchorPane dd;







    public void User(MouseEvent event) throws SQLException, IOException{
        AnchorPane p =FXMLLoader.load(getClass().getResource("/views/admin/ModifUser.fxml"));
        dd.getChildren().setAll(p);
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

    @FXML
    public void handleBtnLogout(MouseEvent event){

        System.out.println("this works");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setHeaderText("Deconnexion");
        alert.setContentText("Vous allez se deconnecter, etes vous sure ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            UserSession.getInstace(new User()).cleanUserSession();
            loadPage(event,"/views/utilisateurs/Authentification.fxml");
        }
    }






    @FXML
        private void AfficherNbrClient(ActionEvent event) {


    }

    @FXML
    private void AfficherNbrMembre(ActionEvent event) {



    }

    @FXML
    private void GestionUtilisateur(ActionEvent event) {



    }


    public void projet(MouseEvent mouseEvent) {
    }
}

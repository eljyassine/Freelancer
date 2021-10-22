package controllers.utilisateurs;

import javafx.scene.Parent;
import models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.UserSession;
import java.io.IOException;
import java.util.Optional;

public class DashboardClientController {
    User currentUser=null;

    @FXML
    Label lblUsername;


    public DashboardClientController() {
        currentUser= UserSession.getInstace(new User()).getUser();
    }



    @FXML
    public void handleBtnLogout(MouseEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setHeaderText("Deconnexion");
        alert.setContentText("Vous allez se deconnecter, etes vous sure ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            UserSession.getInstace(currentUser).cleanUserSession();
            loadPage(event,"/views/utilisateurs/Authentification.fxml");
        }
    }

    @FXML
    public void handleBtnMonProfil(MouseEvent event){
        loadPage(event,"/views/utilisateurs/ProfileClient.fxml");
    }


    private void loadPage(MouseEvent event, String pageName) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene((Parent) FXMLLoader.load(getClass().getResource(pageName)));
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }}
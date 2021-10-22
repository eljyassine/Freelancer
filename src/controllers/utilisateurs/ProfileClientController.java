package controllers.utilisateurs;

import models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.UserSession;
import java.io.IOException;
import java.util.Optional;


public class ProfileClientController  {

    @FXML
    Label lblName,lblEmail,lblUsername,lblDateNaiss,lblMemberSince;
    @FXML
    TextArea txtBio;
    @FXML
    Button btnEditBasicInfo;

    User currentUser=null;
    UserService userService=null;


    public ProfileClientController() {
        currentUser= UserSession.getInstace(new User()).getUser();
        userService=new UserService();
    }


    @FXML
    public void handleBtnEditBasicInfo(MouseEvent event){
        loadPage(event,"/views/utilisateurs/EditBasicInformations.fxml");
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

    private void loadPage(MouseEvent event, String pageName){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource(pageName)));
            stage.setScene(scene);
            stage.show();

        } catch (    IOException ex) {
            System.err.println(ex.getMessage());
        }
    }


    public void handleBtnShowProject(MouseEvent mouseEvent) {
    }
}

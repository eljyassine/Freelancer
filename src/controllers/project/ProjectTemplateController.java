package controllers.project;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import models.Project;
import com.jfoenix.controls.JFXButton;
import dbr.ProjectDBR;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ProjectTemplateController {

    ServiceProject serviceProjet = new ServiceProject();
    ServiceProjectTech serviceProjetTech = new ServiceProjectTech();
    ProjectDBR projetDao = new ProjectDBR();
    ListeProjectController listeProjetController = new ListeProjectController();

    @FXML
    private Text nomProjet;

    @FXML
    private Text coutProjet;

    @FXML
    private Text techStringsProjet;

    @FXML
    private Text dateProjet;

    private Project projet = new Project();

    @FXML
    private JFXButton publierBtn;
/*


    @FXML
    void afficherDemandes(ActionEvent event) {
        loadListeDemandesProjetGui(this.projet);
    }

    @FXML
    void onAjouterCompetance(ActionEvent event) {
        loadAjouterCompetanceProjetGui("AjouterCompetanceProjet.fxml",projet);
    }
*/
    @FXML
    void onModifierProjet(ActionEvent event) {
        loadModifierProjetGui("AjouterProject.fxml", projet);
    }


    @FXML
    void onPublierProjet(ActionEvent event) {
        if(projet.isPublie()) {

        }


    Alert confirmation=  new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Valider");
        confirmation.setHeaderText("Vous etes sur de vouloir publier ce projet ?");
        confirmation.setContentText("Cliquer sur ok pour confirmer.");
    Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get()== ButtonType.OK)
    {
        serviceProjet.publierProjet(projet);
        serviceProjetTech.notifierFreelancers(projet, serviceProjetTech.getTechsByProjet(projet));
        //Debut Notif
        String title = "Succ�s";
        String message = "Projet "+projet.getNomProjet()+" publi� avec Succ�s";
        /*
        NotificationType notification = NotificationType.SUCCESS;

        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(notification);
        tray.showAndDismiss(new Duration(2000));
        */

        SetProjet(projet);
    }
}



    @FXML
    void onDeleteProjet(ActionEvent event) {
        Alert confirmation=  new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Valider");
        confirmation.setHeaderText("Vous etes sur de vouloir supprimer ce projet ?");
        confirmation.setContentText("Cliquer sur ok pour confirmer.");
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.get()== ButtonType.OK)
        {
            projetDao.delete(projet);
            SetProjet(projet);
        }

    }




    public void SetProjet(Project projet) {
        techStringsProjet.setText("");
        String pattern = "yyyy/MM/dd";
        DateFormat df = new SimpleDateFormat(pattern);
        String  techStringsP = "";
        this.projet = projet;
        System.out.println(this.projet.isPublie());
        nomProjet.setText(projet.getNomProjet());
        coutProjet.setText("$ "+Double.toString(projet.getCout()));
        serviceProjetTech.getTechsByProjet(projet).forEach(tech->{
            techStringsProjet.setText(techStringsProjet.getText()+"|"+tech.getNomTechnologie());
        });
        projet.getDateDebut();
        //date Debut
        String dateDebut= df.format(projet.getDateDebut());
        String dateFin= df.format(projet.getDateFin());
        this.dateProjet.setText(dateDebut+"-->"+dateFin);
        if (projet.isPublie()) {
            publierBtn.setText("Publi�");
            publierBtn.setDisable(true);
        }

    }




    public void loadModifierProjetGui(String gui, Project projet) {
        AnchorPane root = null;
        try {
            Stage stage= new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AjouterProjectController.class.getResource("/models/Project/"+gui));
            root = loader.load();
            AjouterProjectController ajouterProjetController = loader.getController();
            System.out.println(projet.getNomProjet());
            ajouterProjetController.intitModifierProjetAtt(projet);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void afficherDemandes(ActionEvent actionEvent) {
    }

    public void onAjouterCompetance(ActionEvent actionEvent) {
    }
}

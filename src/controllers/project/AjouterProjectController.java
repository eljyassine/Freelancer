package controllers.project;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import models.Client;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import dbr.ProjectDBR;
import models.Project;
import models.Technologie;
import models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sample.ClientSession;

public class AjouterProjectController {


    @FXML
    private AnchorPane PaneProjet;

    @FXML
    private Text titre;

    @FXML
    private JFXTextField nomProjet;

    @FXML
    private JFXTextField titreProjet;

    @FXML
    private JFXDatePicker dateDebutProjet;

    @FXML
    private JFXDatePicker dateFinProjet;

    @FXML
    private JFXTextField coutProjet;

    @FXML
    private JFXTextArea descriptionProjet;

    @FXML
    private JFXButton valider;

    @FXML
    private ListView <Technologie> technologieList;



    private Project projet ;

    private ServiceTechnologie serviceTechnologie = new ServiceTechnologie();

    private ServiceProjectTech serviceProjetTech = new ServiceProjectTech();
    List<Technologie> listChoix = new ArrayList<Technologie>();
    User currentUser = new User();
    Client currentClient = new Client();
    List<String> techChoix = new ArrayList<String>();

    private boolean update;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public Project getProjet() {
        return projet;
    }

    public void setProjet(Project projet) {
        this.projet = projet;
    }

    @FXML
    public void onClickAjouterProjet() throws ParseException {
        //validation des champs
        boolean numeric = true;
        Double num = 0.0;

        Date dateDebut = null;
        Date dateFin = null;
        Boolean validDate = false;
        String msgErreur = "";
        //Verification de la date
        if((dateDebutProjet.getValue()!=null) && (dateFinProjet.getValue()!=null)) {
            dateDebut = Date.from(Instant.from( dateDebutProjet.getValue().atStartOfDay(ZoneId.systemDefault()))) ;
            dateFin = Date.from(Instant.from( dateFinProjet.getValue().atStartOfDay(ZoneId.systemDefault()))) ;

        }
        else {
            msgErreur += "Le champ date est invalide \n";
        }
        //--------- Fin Verification de la date
        try {


            num = Double.parseDouble(coutProjet.getText());
        } catch (NumberFormatException e) {
            numeric = false;
            msgErreur += "Le champ cout est invalide \n";
        }
        //Verification si le cout est convertible et supp a 0 et le titre du projet est valide
        if((numeric )&& ( num > 0.0) && (titreProjet.getText().chars().allMatch(Character::isLetter))
                && (!titreProjet.getText().equals(""))&& (!nomProjet.getText().equals("")) && (!descriptionProjet.getText().equals("") && (validDate))
        )
        {
            Alert confirmation=  new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Valider");
            confirmation.setHeaderText("Vous etes sur ?");
            confirmation.setContentText("Cliquer sur ok pour confirmer.");
            Optional<ButtonType> result = confirmation.showAndWait();

            if (result.get()== ButtonType.OK)
            {
                ProjectDBR projetDao = new ProjectDBR();
                if (isUpdate()) {
                    projet.setNomProjet(nomProjet.getText());
                    projet.setTitreProjet(titreProjet.getText());
                    projet.setDateDebut(dateDebut);
                    projet.setDateFin(dateFin);
                    projet.setDescription(descriptionProjet.getText());
                    projet.setCout(Double.parseDouble(coutProjet.getText()));
                    projetDao.update(projet);
                    serviceProjetTech.ajouterTech(projet, listChoix);

                    String title = "Succ�s";
                    String message = "Projet "+projet.getNomProjet()+" modifi� avec Succ�s";
                    NotificationType notification = NotificationType.SUCCESS;
                    TrayNotification tray = new TrayNotification();

                    tray.setTitle(title);
                    tray.setMessage(message);
                    tray.setNotificationType(notification);
                    tray.showAndDismiss(new Duration(2000));

                }
                else {
                    currentClient= ClientSession.getInstance(new Client()).getClient();
                    Project p = new Project(currentClient, nomProjet.getText(), titreProjet.getText(), dateDebut, dateFin, descriptionProjet.getText(), false, false, Double.parseDouble(coutProjet.getText()));

                    projetDao.add(p);

                    String title = "Succ�s";
                    String message = "Projet "+p.getNomProjet()+" ajout� avec Succ�s";
                    NotificationType notification = NotificationType.SUCCESS;
                    TrayNotification tray = new TrayNotification();
                    tray.setTitle(title);
                    tray.setMessage(message);
                    tray.setNotificationType(notification);
                    tray.showAndDismiss(new Duration(2000));

                }

                nomProjet.setText("");
                titreProjet.setText("");
                dateDebutProjet.setValue(null);
                dateFinProjet.setValue(null);
                coutProjet.setText("");
                descriptionProjet.setText("");
            }
        }

        else {

            if (nomProjet.getText().equals("")) {
                msgErreur += "Le champ nom du projet ne peut pas etre vide \n";
            }

            if (descriptionProjet.getText().equals("")) {
                msgErreur += "Le champ description ne peut pas etre vide \n";
            }
            if (!(titreProjet.getText().chars().allMatch(Character::isLetter)) || (titreProjet.getText().equals(""))) {
                msgErreur += "Le champ titre est non valide \n";
            }

            Alert erreur = new Alert(Alert.AlertType.ERROR);
            erreur.setTitle("Erreur");
            erreur.setHeaderText("Champ(s) invalide(s)");
            erreur.setContentText(msgErreur);
            erreur.showAndWait();
        }
    }




    public void intitModifierProjetAtt(Project projet) {
        nomProjet.setText(projet.getNomProjet());
        titreProjet.setText(projet.getTitreProjet());
        dateDebutProjet.setValue(LocalDate.parse(projet.getDateDebut().toString()));
        dateFinProjet.setValue(LocalDate.parse(projet.getDateFin().toString()));
        coutProjet.setText(Double.toString(projet.getCout()));
        descriptionProjet.setText(projet.getDescription());
        setUpdate(true);
        setProjet(projet);
        titre.setText("Modifier Projet");
    }



}

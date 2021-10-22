package controllers.project;

import com.jfoenix.controls.JFXButton;
import models.Project;
import models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import controllers.project.ServiceProject;
import sample.UserSession;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NavigationProjetController{


	ServiceProject serviceProject= new ServiceProject();
	User currentUser = new User();
	
    @FXML
    private JFXButton AjouterProjetLink;

    @FXML
    private JFXButton MesProjetLink;
    
    @FXML
    private BorderPane borderPane;
    
    
    public void loadAjouterProjetGUI() {
    	loadGui("AjouterProjet.fxml");
    }
    
    public void loadMesProjetGUI() {
    	loadGui("listeProjet.fxml");
    }

    public void loadOffresGUI(MouseEvent event) {
    	loadGui("listeOffres.fxml");
    }
    
    @FXML
    void loadDemandesMesProjetsGUI(ActionEvent event) {
    	
    	currentUser= UserSession.getInstace(new User()).getUser();

    	//loadListeDemandesProjetsGui("listeDemandesProjet.fxml",serviceProject.getProjetsOfUser(currentUser));
    }
    
    public void goBack(MouseEvent event) {
    	System.out.println("hello");
//    	loadGui("listeOffres.fxml");
    }
    
    @FXML
    void loadOperationGUI(ActionEvent event) {
    	loadGui("operationProjet.fxml");
    }
    
    
    
    
    
    
    @FXML
    void loadGui(String gui) {
    	AnchorPane root = null;
    	try {
    		root = FXMLLoader.load(getClass().getResource("/views/offres/projet/"+gui));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	borderPane.setCenter(root);
    }

    @FXML
    void GUIMesProjets() {

    }


	



}

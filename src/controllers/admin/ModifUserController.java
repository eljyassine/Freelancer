
package controllers.admin;

import models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import dbr.UserDb;
import models.User;

public class ModifUserController  {

    @FXML
    private ChoiceBox Choice;
    @FXML
    private TableView<models.User> t1;
    @FXML
    private TableColumn<models.User, Integer> Id;
    @FXML
    private TableColumn<models.User, String> UserName;
    @FXML
    private TableColumn<models.User, String> Mail;


    @FXML
    private TableColumn<models.User, String> Salt;
    @FXML
    private TableColumn<models.User, String> Password;
    @FXML
    private TableColumn<models.User, String> Role;
    @FXML
    private TableColumn<models.User, String> Competance;
    @FXML
    private TableColumn<models.User, String> FirstName;
    @FXML
    private TableColumn<models.User, String> LastName;
    @FXML
    private TableColumn<models.User, String> DateNaissance;
    @FXML
    private Button backbtn;
    @FXML
    private Button OK;
    @FXML
    private Button show;
    @FXML
    private TextField txtid;
    @FXML
    private Button Bannir;
    @FXML
    private TextField txtusername;
    @FXML
    private TextField txtmail;

    @FXML
    private TextField txtrole;
    @FXML
    private TextField txtcompetance;
    @FXML
    private TextField txtfirstname;
    @FXML
    private TextField txtlastname;
    @FXML
    private DatePicker txtdatenaissance;
    @FXML
    private Button btnupdate;
    @FXML
    private Button stat;
     @FXML
    AnchorPane dd;



    UserDb data = new  UserDb();

     User user = new  User();

    @FXML
    public void back(MouseEvent event) throws SQLException {

        loadPage(event, "/views/admin/AdminDashboard.fxml");

    }
    public void initialize() {

        ObservableList<String> Choices = FXCollections.observableArrayList("Tous", "Freelancer", "Client");
        Choice.setValue("Tous");
        Choice.setItems(Choices);

        try {

            listUser();
        } catch (SQLException ex) {
            Logger.getLogger(ModifUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadPage(MouseEvent event, String pageName) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource(pageName)));
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void listUser() throws SQLException {
        ObservableList< User> user = FXCollections.observableArrayList();
        user.addAll(data.selectAll());
        

        //Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        UserName.setCellValueFactory(new PropertyValueFactory<>("Username"));
        FirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        Mail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        //Password.setCellValueFactory(new PropertyValueFactory<>("Password"));
        DateNaissance.setCellValueFactory(new PropertyValueFactory<>("DateNaissance"));
//        Competance.setCellValueFactory(new PropertyValueFactory<>("Comeptance"));
        Role.setCellValueFactory(new PropertyValueFactory<>( "Role"));
        t1.getItems().clear();
        t1.setItems(user);

    }



    @FXML
    public void RechercheUser() throws SQLException {

        String s = (String) Choice.getValue();
        System.out.println(s);
        if (s == "Tous") {
            listUser();
        } else {
            ObservableList< User> user = FXCollections.observableArrayList();

            user.addAll(data.selectRole(s));
            

            //Id.setCellValueFactory(new PropertyValueFactory<>("id"));
            UserName.setCellValueFactory(new PropertyValueFactory<>("Username"));
            FirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
            LastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
            Mail.setCellValueFactory(new PropertyValueFactory<>("Email"));
            //Password.setCellValueFactory(new PropertyValueFactory<>("Password"));
            DateNaissance.setCellValueFactory(new PropertyValueFactory<>("DateNaissance"));
//            Competance.setCellValueFactory(new PropertyValueFactory<>("Comeptance"));
            Role.setCellValueFactory(new PropertyValueFactory<>( "Role"));
            t1.getItems().clear();
            t1.setItems((ObservableList<User>) user);

        }


    }


    @FXML
    public void show(MouseEvent event) throws SQLException {
        user = t1.getSelectionModel().getSelectedItem();
        //txtid.setText("" + user.getId());
        txtusername.setText(user.getUsername());
        txtfirstname.setText(user.getFirstName());
        txtlastname.setText(user.getLastName());
        txtmail.setText(user.getEmail());
        //txtpassword.setText(user.getPassword());
//        txtcompetance.setText(user.getCompetance());
        txtrole.setText(user.getRole());
      txtdatenaissance.setValue(LocalDate.parse(user.getDateNaissance()));//peut etree fausse
    


        /*  t1.getItems().clear();
        t1.setItems(U);
        RechercheUser();*/
    }


    @FXML
    public void Banne (MouseEvent event) throws SQLException {
        user = t1.getSelectionModel().getSelectedItem();
        data.delete(user.getId());

    }
}

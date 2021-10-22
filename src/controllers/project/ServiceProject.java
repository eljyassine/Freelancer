package controllers.project;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dbr.ProjectDBR;
import models.Project;
import models.User;
import models.Client;

public class ServiceProject {

    private ProjectDBR projetDBR;

    public ServiceProject() {
        projetDBR =  new ProjectDBR();
    }

    public List<String> getAllProjets() throws SQLException {
        List<Project> list=projetDBR.selectAll();
        List<String> data = new ArrayList<>();
        for (Project element: list) {
            data.add(element.getNomProjet());
        }
        return data;
    }

    public List<Project> getClientProjets(Client client) throws SQLException {
        List<Project> list=projetDBR.selectBy("client_id",Integer.toString(client.getIdClient()));
        return list;
    }

    //retourne la liste des projets modifiables d'un client
    public List<Project> getPublishedProjets() throws SQLException {
        List<Project> list=projetDBR.selectBy("publie", "1");
        return list;
    }


    public void publierProjet(Project projet) {
        projet.setPublie(true);
        Date today = Calendar.getInstance().getTime();
        projet.setDatePublication(today);
        projetDBR.update(projet);
    }

    public ArrayList<Project> getProjetsOfUser(User user){
        return (ArrayList<Project>) projetDBR.selectBy("client_id", Integer.toString(user.getId()));
    }


}

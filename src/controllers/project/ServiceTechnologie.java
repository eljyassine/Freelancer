package controllers.project;

import java.util.List;

import dbr.TechnologieDBR;
import models.Project;
import models.Technologie;

public class ServiceTechnologie {

    ServiceProjectTech serviceProjetTech = new ServiceProjectTech();

    private TechnologieDBR technologieD = new TechnologieDBR();


    public List<Technologie> getAllTechs(){
        return technologieD.selectAll();
    }

    public void ajouterTechsToProjet(List<Technologie> techs, Project projet) {
        serviceProjetTech.ajouterTech(projet,techs);
    }

    public Technologie getTechParNom(String nomCat) {
        return technologieD.selectBy("nomTechnologie", nomCat).get(0);
    }

}

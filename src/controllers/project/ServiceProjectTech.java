package controllers.project;

import java.util.ArrayList;
import java.util.List;

import dbr.ProjectTechnologieDBR;
import models.Project;
import models.Technologie;

/*
import services.offres.competenceUser.CompetenceUserService;
import services.offres.user.UserService;
*/

public class ServiceProjectTech {


    ProjectTechnologieDBR projetTechDBR = new ProjectTechnologieDBR();

    public void ajouterTech(Project projet, List<Technologie> techs) {
        System.out.println("techs "+techs);
        techs.forEach(tech->{
            System.out.println("forEach tech");
            projetTechDBR.add(projet,tech);
            if (projet.isPublie()) {
                notifierFreelancers(projet, techs);
            }
        });

    }

    public void removeAllTech(Project projet)
    {
        projetTechDBR.delete(projet);
    }

    public List<Technologie> getTechsByProjet(Project p){
        return projetTechDBR.getTechsByProjet(p);

    }

    public List<Technologie> getTechsByNameList(List<String> noms)
    {
        ServiceTechnologie serviceTechnologie = new ServiceTechnologie();
        List<Technologie> techs=new ArrayList<Technologie>();
        noms.forEach(nom->{
            techs.add(serviceTechnologie.getTechParNom(nom));
        });
        return techs;
    }


    public void notifierFreelancers(Project p,List<Technologie> techs) {
        /*
        UserService userService = new UserService();
        CompetenceUserService competenceUserService = new CompetenceUserService();
        techs.forEach(tech->{
            competenceUserService.notifierUsersHaving(p,tech);
        });
        */
    }
}

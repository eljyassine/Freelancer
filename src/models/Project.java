package models;

import java.util.Date;

public class Project {

    private int idProjet;
    private String nomProjet;
    private String TitreProjet;
    private Date DateDebut;
    private Date dateFin;
    private Date dateRealisation;
    private Date dateCreation;
    private Date datePublication;
    private String description;
    private boolean publie;
    private boolean valide;
    private double cout;

    private Client client;
    private int idClient;




    @Override
    public String toString() {
        return "Projet [id=" + idProjet + ", client=" + client.getIdClient() + ", nomProjet=" + nomProjet + ", TitreduProjet=" + TitreProjet
                + "]";
    }


    public Client getClient() {
        return client;
    }

    public void setClient (Client client) {
        this.client = client;
    }

    public Project(Client client, String nomProjet, String titreProjet, Date dateDebut, Date dateFin,
                   String description, boolean publie, boolean valide, double cout) {
        super();
        this.client = client;
        this.nomProjet = nomProjet;
        TitreProjet = titreProjet;
        DateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.publie = publie;
        this.valide = valide;
        this.cout = cout;
        this.idClient = client.getIdClient();
    }

    public String getTitreProjet() {
        return TitreProjet;
    }

    public void setTitreProjet(String titreProjet) {
        TitreProjet = titreProjet;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        DateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Date getDateRealisation() {
        return dateRealisation;
    }

    public void setDateRealisation(Date dateRealisation) {
        this.dateRealisation = dateRealisation;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublie() {
        return publie;
    }

    public void setPublie(boolean publie) {
        this.publie = publie;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public Project( String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public Project() {
    }

    public int getId() {
        return idProjet;
    }
    public void setId(int id) {
        this.idProjet = id;
    }
    public String getNomProjet() {
        return nomProjet;
    }
    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

}




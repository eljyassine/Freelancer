package models;

public class  User {

    private int id;
    protected String username="";
    protected String firstName="";
    protected String lastName="";
    protected String email="";
    protected String password="";
    protected String dateNaissance="";
    protected String imagename="";
    protected String competance="";
    protected String role="";


    public void setImagename(String image_name) {
        this.imagename = image_name;
    }
    public String getImagename() {
        return imagename;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() { return password; }

    public void setDateNaissance(String dateNaissance) {this.dateNaissance = dateNaissance;}
    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setCompetance(String competance) {
        this.competance = competance;
    }
    public String getCompetance() {
        return competance;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setRole (String role ) {
        this.role  = role ;
    }
    public String getRole () {
        return role ;
    }



    public User() { }
    public User( String username ,  String firstName,  String lastName,String email , String password,   String dateNaissance,String imagename,String competance, String role ) {

        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.dateNaissance = dateNaissance;
        this.imagename=imagename;
        this.role  = role;
        this.competance = competance;}

    public User( String username ,  String firstName,  String lastName,String email , String password,   String dateNaissance,String imagename,String competance,String role ,int id) {

        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.dateNaissance = dateNaissance;
        this.imagename=imagename;
        this.competance = competance;
        this.role = role;
        this.id = id;
    }

    public User(int id) {
        super();
        this.id = id;
    }

/*
    public static void main(String[] args) {
User u=new User(2,"siwarhamed","siwar","benhamed","benhamed@gmail.com","1000","02/06/1999","erererrerere","aaaaaaaaa");
        System.out.printf("fffffffffffffff")   ;
        String g =   u.getPassword();
        System.out.printf(g)   ;


    }
*/
}



  //  private String usernameCanonical;

/*   private String emailCanonical;*/
 //   private boolean enabled;
 //   private String salt;


 //   private String plainPassword;
 //   @Temporal(DATE)
 //   private Date lastLogin;
//    private String confirmationToken;
 //   @Temporal(DATE)
 //   private Date passwordRequestedAt;
 //   private String roleJv;

    //our user attributes

 //   private boolean valide;
  //  private boolean signale;





    /*
     //Liste des langues parlees par l'utilisateur
     @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
     @JoinTable(name = "langue_user",joinColumns = @JoinColumn(name = "idUser"),inverseJoinColumns = @JoinColumn(name = "idLangue"))
     private List<Langue> languesList;


     //Liste des competences du freelance
     @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
     @JoinTable(name = "competence_user",joinColumns = @JoinColumn(name = "idUser"),inverseJoinColumns = @JoinColumn(name = "idCompetence"))
     private List<Competence> competencesList;

     //Liste des annonces d'emplois faites par l'embaucheur
     @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
     private List<AnnonceEmplois> annonceEmploisList;

     //Liste des postulation aux annonces d'emplois faites par le freelance
     @OneToMany(mappedBy = "freelance",cascade=CascadeType.ALL)
     private List<Postulation> postulationList;

     //Liste des evaluations du freelance
     @OneToMany(mappedBy = "freelance",cascade=CascadeType.ALL)
     private List<Evaluation> evaluationFreelancer;

     //Liste des evaluations faites par l'embaucheur
     @OneToMany(mappedBy = "Embaucheur",cascade=CascadeType.ALL)
     private List<Evaluation> evaluationEmbaucheur;
     ***/



    //public String getUsernameCanonical() {
     //   return usernameCanonical;
   // }

   // public void setUsernameCanonical(String usernameCanonical) {
   //     this.usernameCanonical = usernameCanonical;
   // }



    //public String getEmailCanonical() {
    //    return emailCanonical;
    //}

    //public void setEmailCanonical(String emailCanonical) {
    //    this.emailCanonical = emailCanonical;
    //}

   // public boolean isEnabled() {
   //     return enabled;
   // }

    //public void setEnabled(boolean enabled) {
    //    this.enabled = enabled;
    //}
/*
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
*/


   /* public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Date passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public String getRoleJv() {
        return roleJv;
    }

    public void setRoleJv(String roleJv) {
        this.roleJv = roleJv;
    }
*/
  //  public float getSolde() {
   //     return solde;
   // }

 /* public User(int aInt, String string, String string0, String string1, String string2, String string3, String string4, String string5, String string6, String string7, String string8) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/


   /* public boolean isValide() {
        return valide;
    }

    public boolean isSignale() {
        return signale;
    }
*/














      //  this.usernameCanonical = usernameCanonical;

      //  this.emailCanonical = emailCanonical;
     //   this.enabled = enabled;
    //    this.salt = salt;

   //     this.lastLogin = lastLogin;
    //    this.confirmationToken = confirmationToken;
     //   this.passwordRequestedAt = passwordRequestedAt;
    //    this.roleJv = role;



/*
    public User(int id, String username,  String email,  String password, String firstName,String competance, String lastName) {
        this.id = id;
        this.username = username;
    //    this.usernameCanonical = usernameCanonical;
        this.email = email;
    //    this.emailCanonical = emailCanonical;
        //this.enabled = enabled;
        //this.salt = salt;
        this.password = password;
        //this.plainPassword = plainPassword;
        //this.lastLogin = lastLogin;
        //this.confirmationToken = confirmationToken;
        //this.passwordRequestedAt = passwordRequestedAt;
   //     this.roleJv = roleJv;
        this.firstName = firstName;
        //this.solde = solde;
        this.competance = competance;
        this.lastName = lastName;
        //this.dateNaissance = dateNaissance;
    }*/



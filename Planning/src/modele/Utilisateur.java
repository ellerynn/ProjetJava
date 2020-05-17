package modele;

/*UTILISATEUR (ID, EMAIL, PASSWD, NOM, PRENOM, DROIT) 
Remarques : cette relation indique le droit de consultation et de mise à jour aux données de l’emploi du temps. 
L’attribut DROIT indique le type de droit d’accès ou/et de mise à jour aux données, comme par exemple : 
- 1 pour un droit d’administrateur (service chargé de la planification) : il a le droit de consulter et de mettre à jour 
toutes les données de l’emploi du temps 
- 2 pour un droit de référent pédagogique : il peut consulter les emplois du temps de tous les enseignants, promotions, 
groupes, étudiants, cours et salles MAIS ne rien mettre à jour - 3 pour un droit d’enseignant : il ne peut consulter que 
son emploi du temps et le récapitulatif de ses cours sur une période - 4 pour un droit d’étudiant : il ne peut consulter 
que son emploi du temps et le récapitulatif de ses cours sur une période
*/

public class Utilisateur {
    protected int id;
    protected String email;
    protected String password;
    protected String nom;
    protected String prenom;
    protected int droit; //1 : administrateur ; 2 : référent pédagogique ; 3 : enseignant ; 4 : étudiant 
    
    //Constructeur par défaut
    public Utilisateur() {
        id = 0;
        email = new String();
        password = new String();
        nom = new String();
        prenom = new String();
        droit = 0;
    }
    
    //Constructeur
    public Utilisateur(String email, String password, String nom, String prenom, int droit) { 
        //Un administrateur peut créer un nouvel utilisateur, id s'incrémente seul dans BDD
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.droit = droit;
    }
    
    //Getters
    public int getId() {
        return id;
    }
    
    public int getDroit() {
        return droit;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    //Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setDroit(int droit) {
        this.droit = droit;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}


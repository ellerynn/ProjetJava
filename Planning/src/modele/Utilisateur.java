package modele;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class Utilisateur {
    protected int id;
    protected String email;
    protected String password;
    protected String nom;
    protected String prenom;
    protected int droit; //1 : administrateur ; 2 : référent pédagogique ; 3 : enseignant ; 4 : étudiant 
    
    /**
     * constructeur par défaut 
     */
    public Utilisateur() {
        id = 0;
        email = new String();
        password = new String();
        nom = new String();
        prenom = new String();
        droit = 0;
    }
    
    /**
     * constructeur
     * @param email
     * @param password
     * @param nom
     * @param prenom
     * @param droit
     */
    public Utilisateur(String email, String password, String nom, String prenom, int droit) { 
        //Un administrateur peut créer un nouvel utilisateur, id s'incrémente seul dans BDD
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.droit = droit;
    }
       
    /**
     * retourne l'id d'un utilisateur
     * @return
     */
    public int getId() {
        return id;
    }
    
    /**
     * retourne le droit d'un utilisateur
     * @return
     */
    public int getDroit() {
        return droit;
    }
    
    /**
     * retourne le nom d'un utilisateur
     * @return
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * retourne le prenom d'un utilisateur
     * @return
     */
    public String getPrenom() {
        return prenom;
    }
    
    /**
     * retourne le mail d'un utilisateur
     * @return
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * retourne le mot de passe d'un utilisateur
     * @return
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * set l'id d'un utilisateur
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * set le droit d'un utilisateur
     * @param droit
     */
    public void setDroit(int droit) {
        this.droit = droit;
    }
    
    /**
     * set le nom d'un utilisateur
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     * set le prenom d'un utilisateur
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    /**
     * set le mail d'un utilisateur
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     *set le mot de passe d'un utilisateur
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * copie un utilisateur passé en paramètre
     * @param utilisateur
     */
    public void copierUtilisateur(Utilisateur utilisateur){
        //Renommer SETUtilisateur ?
        //Essayer this = utilisateur;
        id = utilisateur.getId();
        email = utilisateur.getEmail();
        password = utilisateur.getPassword();
        nom = utilisateur.getNom();
        prenom = utilisateur.getPrenom();
        droit = utilisateur.getDroit();
    }

    /**
     * retourne this
     * @return
     */
    public Utilisateur getUtilisateur(){ //Pour update
        //ESSAYER JUSTE CA : return this;
        Utilisateur user = new Utilisateur(email, password, nom, prenom, droit);
        user.setId(getId());
        return user;
    }
}


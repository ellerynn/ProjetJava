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
     * @param email email de l'utilisateur
     * @param password mot de passe de l'utilisateur
     * @param nom nom de l'utilisateur
     * @param prenom prenom de l'utilisateur
     * @param droit droit de l'utilisateur
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
     * @return retourne l'id d'un utilisateur
     */
    public int getId() {
        return id;
    }
    
    /**
     * retourne le droit d'un utilisateur
     * @return retourne le droit d'un utilisateur
     */
    public int getDroit() {
        return droit;
    }
    
    /**
     * retourne le nom d'un utilisateur
     * @return retourne le nom d'un utilisateur
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * retourne le prenom d'un utilisateur
     * @return retourne le prenom d'un utilisateur
     */
    public String getPrenom() {
        return prenom;
    }
    
    /**
     * retourne le mail d'un utilisateur
     * @return retourne le mail d'un utilisateur
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * retourne le mot de passe d'un utilisateur
     * @return retourne le mot de passe d'un utilisateur
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * set l'id d'un utilisateur
     * @param id id d'un utilisateur
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * set le droit d'un utilisateur
     * @param droit droit d'un utilisateur
     */
    public void setDroit(int droit) {
        this.droit = droit;
    }
    
    /**
     * set le nom d'un utilisateur
     * @param nom nom d'un utilisateur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     * set le prenom d'un utilisateur
     * @param prenom le prenom d'un utilisateur
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    /**
     * set le mail d'un utilisateur
     * @param email le mail d'un utilisateur
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     *set le mot de passe d'un utilisateur
     * @param password le mot de passe d'un utilisateur
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * copie un utilisateur passé en paramètre
     * @param utilisateur Utilisateur à copier
     */
    public void copierUtilisateur(Utilisateur utilisateur){
        id = utilisateur.getId();
        email = utilisateur.getEmail();
        password = utilisateur.getPassword();
        nom = utilisateur.getNom();
        prenom = utilisateur.getPrenom();
        droit = utilisateur.getDroit();
    }

    /**
     * retourne this
     * @return retourne this
     */
    public Utilisateur getUtilisateur(){ //Pour update
        Utilisateur user = new Utilisateur(email, password, nom, prenom, droit);
        user.setId(getId());
        return user;
    }
}


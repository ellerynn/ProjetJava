package modele;

/*COURS (ID, NOM)*/
public class Cours {
    private int id;
    private String nom;
    
    //Constructeur par défaut
    public Cours() {
        id = 0;
        nom = new String();
    }
    
    //Constructeur 
    public Cours(String nom) { //Un administrateur peut créer un nouveau cours, id s'implémente seul dans BDD
        this.nom = nom;
    }
    
    //Getters
    public int getId() {
        return id;
    }
    
    public String getNom() {
        return nom;
    }
        
    //Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
        
    //Méthodes
}

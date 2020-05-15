package planning;

/*SALLE (ID, NOM, CAPACITE, #ID_SITE) 
Remarque : la salle indique sa capacité et le site, si l’école dispose de plusieurs sites
*/

public class Salle {
    private int id;
    private String nom;
    private int capacite;
    private Site site;
    
    //Constructeur par défaut
    public Salle() {
        id = 0;
        nom = new String();
        capacite = 0;
        site = new Site();
    }
    
    //Getters
    public int getId() {
        return id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public int getCapacite() {
        return capacite;
    }
    
    public Site getSite() {
        return site;
    }
    
    //Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    
    public void setSite(Site site) {
        this.site = site;
    }
}

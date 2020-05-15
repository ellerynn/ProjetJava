package planning;

/*GROUPE (ID, NOM, #ID_PROMOTION) Remarque : le groupe est affecté à une promotion*/

public class Groupe {
    private int id;
    private String nom;
    private Promotion promotion;
    
    //Constructeur par défaut
    public Groupe() {
        id = 0;
        nom = new String();
        promotion = new Promotion();
    }
    
    //Getters
    public int getId() {
        return id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public Promotion getPromotion() {
        return promotion;
    }
    
    //Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
}


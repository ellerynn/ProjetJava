package planning;

/*SEANCE (ID, SEMAINE, DATE, HEURE_DEBUT, HEURE_FIN, ETAT, #ID_COURS, #ID_TYPE) 
Remarques : l’attribut SEMAINE indique le numéro de semaine dans une année civile. 
L’attribut ETAT indique si la séance est en cours de validation, validée ou annulée : cet attribut pourra être 
représenté par un numéro identifiant l’état de la séance.
*/

public class Seance {
    protected int id;
    protected int semaine;
    protected String heure_debut;
    protected String heure_fin;
    protected int etat;
    protected Cours cours;
    protected TypeCours type;
    
    //Constructeur par défaut
    public Seance() {
        id = 0;
        semaine = 0;
        heure_debut = new String();
        heure_fin = new String();
        etat = 0;
        cours = new Cours();
        type = new TypeCours();
    }
    
    //Getters
    public int getId() {
        return id;
    }
    
    public int getSemaine() {
        return semaine;
    }
    
    public String getHeureDebut() {
        return heure_debut;
    }
    
    public String getHeureFin() {
        return heure_fin;
    }
    
    public int getEtat() {
        return etat;
    }
    
    public Cours getCours() {
        return cours;
    }
    
    public TypeCours getTypeCours() {
        return type;
    }
    
    //Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setSemaine(int semaine) {
        this.semaine = semaine;
    }
    
    public void setHeureDebut(String heure_debut) {
        this.heure_debut = heure_debut;
    }
    
    public void setHeureFin(String heure_fin) {
        this.heure_fin = heure_fin;
    }
    
    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    public void setCours(Cours cours) {
        this.cours = cours;
    }
    
    public void setTypeCours(TypeCours type) {
        this.type = type;
    }
}

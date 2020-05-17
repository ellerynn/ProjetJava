package modele;

import java.sql.Array;
import java.util.*;
import modele.*;

/*SEANCE (ID, SEMAINE, DATE, HEURE_DEBUT, HEURE_FIN, ETAT, #ID_COURS, #ID_TYPE) 
Remarques : l’attribut SEMAINE indique le numéro de semaine dans une année civile. 
L’attribut ETAT indique si la séance est en cours de validation, validée ou annulée : cet attribut pourra être 
représenté par un numéro identifiant l’état de la séance.
*/

public class Seance {
    private int id;
    private int semaine;
    private String heure_debut; //Format HH:MM:SS
    private String heure_fin; //Format HH:MM:SS
    private String date; //Format AAAA-MM-JJ
    private int etat;
    private Cours cours;
    private TypeCours type;
    private ArrayList<Enseignant> enseignants;
    private ArrayList<Groupe> groupes;
    private ArrayList<Salle> salles;
    
    //Constructeur par défaut
    public Seance() {
        id = 0;
        semaine = 0;
        heure_debut = new String();
        heure_fin = new String();
        date = new String();
        etat = 0;
        cours = new Cours();
        type = new TypeCours();
        enseignants = new ArrayList<>();
        groupes = new ArrayList<>();
        salles = new ArrayList<>();
    }
    
    //Méthodes
    public void addEnseignant(Enseignant e){
        enseignants.add(e);
    }
    public void addGroupe(Groupe g){
        groupes.add(g);
    }
    public void addSalle(Salle s){
        salles.add(s);
    }
    
    //Getters [en cours]
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
    
    public String getDate() {
        return date;
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
    public ArrayList<Enseignant> getEnseignants() {
        return enseignants;
    }
    public ArrayList<Salle> getSalles() {
        return salles;
    }
    public ArrayList<Groupe> getGroupes() {
        return groupes;
    }
    
    //Setters [en cours]
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
    
    public void setDate(String date) {
        this.date = date;
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

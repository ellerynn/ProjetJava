package modele;

import java.util.*;

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
    
    //Constructeur
    public Seance(int semaine, String heure_debut, String heure_fin, String date, Cours cours, TypeCours type) {
        //Un administrateur peut créer un nouveau groupe
        this.semaine = semaine;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.date = date;
        this.cours = cours;
        this.type = type;
        enseignants = new ArrayList<>();
        groupes = new ArrayList<>();
        salles = new ArrayList<>();
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
    
    public void setEnseignants(ArrayList<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }
    
    public void setGroupes(ArrayList<Groupe> groupes) {
        this.groupes = groupes;
    }
    
    public void setSalles(ArrayList<Salle> salles) {
        this.salles = salles;
    }
    
    //Méthodes
    public void ajouterEnseignant(Enseignant enseignant) { //Un admin peut ajouter une séance à un enseignant
        this.enseignants.add(enseignant);
    }
    
    public void ajouterGroupe(Groupe groupe) { //Un admin peut ajouter une séance à un groupe
        this.groupes.add(groupe);
    }
    
    public void ajouterSalle(Salle salle) { //Un admin peut ajouter une séance à une salle
        this.salles.add(salle);
    }
    public String calculDuree()
    {
        String duree = new String();
        int heureDebut = 0;
        int minuteDebut = 0;
        int heureFin = 0;
        int minuteFin = 0;
        
        //On récupère les segments.
        String tronqHeureDebut = getHeureDebut().substring(0, 2);
        String tronqMinuteDebut = getHeureDebut().substring(3, 5);
        String tronqHeureFin = getHeureFin().substring(0,2);
        String tronqMinuteFin = getHeureFin().substring(3, 5);
           
        //On les convertie en int pour pouvoir le calculer la durée de cette séance
        heureDebut += Integer.parseInt(tronqHeureDebut);
        minuteDebut += Integer.parseInt(tronqMinuteDebut);
        heureFin += Integer.parseInt(tronqHeureFin);
        minuteFin += Integer.parseInt(tronqMinuteFin);
        
        //La différence de chaque heure et chaque minute
        int heure = heureFin - heureDebut;
        int minute = minuteFin - minuteDebut;
        duree = orderingHour(heure+"h"+minute);
        return duree;
    }
    
    public String orderingHour(String duree)
    {
        int pos = duree.indexOf('h'); //Première occurence de la lettre h
        int heure = Integer.parseInt(duree.substring(0,pos));
        int minute = Integer.parseInt(duree.substring(pos+1, duree.length()));
        if (minute >= 60)
        {
            heure += minute/60; //La partie entière est le surplus d'heure dans minute
            minute = minute%60; //On récupère le reste
        }
        if (minute < 0)
        {
            minute = -minute;
            heure -= minute/60; //La partie entière représente les heures à soustraire
            if (minute%60 != 0)
            {
               heure--;
               minute = 60 - (minute%60);
            }
        }
        duree = heure+"h"+minute;
        return duree;
    }
}

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
    
    protected int id_seance;
    protected int id_groupe;
    
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
    
    public int getId_seance() {
        return id_seance;
    }
    
    public int getId_groupe() {
        return id_groupe;
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
    
    public void setId_seance(int id) {
        this.id_seance = id;
    }
    
    public void setId_groupe(int id) {
        this.id_groupe = id;
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
    
    @Override
    public String toString() {
        String str1 = new String(); //Enseignants
        for(int i=0;i<enseignants.size();i++) {
            str1 = str1 + " " + enseignants.get(i).getPrenom() + " " + enseignants.get(i).getNom(); 
            if(i != enseignants.size()-1)
                str1 = str1 + ", ";
        }
            
        String str2 = new String(); //Groupes
        for(int i=0;i<groupes.size();i++) {
            str2 = str2 + " " + groupes.get(i).getNom();
            if(i != groupes.size()-1)
                str2 = str2 + ", ";
        }
        
        String str3 = new String(); //Salles
        for(int i=0;i<salles.size();i++) {
            str3 = str3 + " " + salles.get(i).getNom() + " à " + salles.get(i).getSite().getNom();
            if(i != salles.size()-1)
                str3 = str3 + ", ";
        }
        
        String seance = new String();
        
        if(etat == 3)
            seance = "ANNULEE\n";
        
        if(etat == 1)
            seance = "EN COURS DE VALIDATION\n";
            
        seance = seance + " " + cours.getNom() + "\n" //Cours
                        + str1 + "\n" //Enseignants
                        + str2 + "\n" //Groupes
                        + str3 + "\n" //Salles
                        + type.getNom() //Type du cours
        ;        
        return seance;
    }
    
    public ArrayList<String> toArrayListOfString() {      
        ArrayList<String> seance = new ArrayList<>();
        
        if(etat == 3)
            seance.add("ANNULEE");
        
        if(etat == 2)
            seance.add("VALIDEE");
        
        if(etat == 1)
            seance.add("EN COURS DE VALIDATION");
            
        seance.add(" " + cours.getNom());
        
        String str1 = new String(); //Enseignants
        for(int i=0;i<enseignants.size();i++) {
            str1 = str1 + " " + enseignants.get(i).getPrenom() + " " + enseignants.get(i).getNom(); 
            if(i != enseignants.size()-1)
                str1 = str1 + ", ";
        }
        
        seance.add(str1);
            
        String str2 = new String(); //Groupes
        for(int i=0;i<groupes.size();i++) {
            str2 = str2 + " " + groupes.get(i).getNom();
            if(i != groupes.size()-1)
                str2 = str2 + ", ";
        }
        
        seance.add(str2);
        
        String str3 = new String(); //Salles
        for(int i=0;i<salles.size();i++) {
            str3 = str3 + " " + salles.get(i).getNom() + " à " + salles.get(i).getSite().getNom();
            if(i != salles.size()-1)
                str3 = str3 + ", ";
        }
        
        seance.add(str3);
        
        seance.add(type.getNom());
        
        //seance[0] = etat ; seance[1] = intitulé du cours; seance[2] = enseignants ; 
        //seance[3] = groupes; seance[4] = salles; seance[5] = type du cours;
        return seance;
    }
}

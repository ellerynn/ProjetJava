package modele;

import java.util.ArrayList;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
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
    
    /**
     * constructeur
     */
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
    
    /**
     * constructeur
     * @param semaine
     * @param heure_debut
     * @param heure_fin
     * @param date
     * @param etat
     * @param cours
     * @param type
     */
    public Seance(int semaine, String heure_debut, String heure_fin, String date, int etat, Cours cours, TypeCours type) {
        //Un administrateur peut créer un nouveau groupe
        this.semaine = semaine;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.date = date;
        this.etat = etat;
        this.cours = cours;
        this.type = type;
        enseignants = new ArrayList<>();
        groupes = new ArrayList<>();
        salles = new ArrayList<>();
    }
    
    /**
     * retourne id seance
     * @return
     */
    public int getId() {
        return id;
    }
    
    /**
     * retourne semaine seance
     * @return
     */
    public int getSemaine() {
        return semaine;
    }
    
    /**
     * retourne heure debut de seance
     * @return
     */
    public String getHeureDebut() {
        return heure_debut;
    }
    
    /**
     * retourne heure fin de seance
     * @return
     */
    public String getHeureFin() {
        return heure_fin;
    }
    
    /**
     * retourne date seance
     * @return
     */
    public String getDate() {
        return date;
    }
    
    /**
     * retourne etat seance
     * @return
     */
    public int getEtat() {
        return etat;
    }
    
    /**
     * retourne cours seance
     * @return
     */
    public Cours getCours() {
        return cours;
    }
    
    /**
     * retourne type cours seance
     * @return
     */
    public TypeCours getTypeCours() {
        return type;
    }
    
    /**
     * retourne enseignants seance
     * @return
     */
    public ArrayList<Enseignant> getEnseignants() {
        return enseignants;
    }
    
    /**
     * retourne salles seance
     * @return
     */
    public ArrayList<Salle> getSalles() {
        return salles;
    }
    
    /**
     * retourne groupes seance
     * @return
     */
    public ArrayList<Groupe> getGroupes() {
        return groupes;
    }
    
    /**
     * set id seance
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
        
    /**
     * set semaine seance
     * @param semaine
     */
    public void setSemaine(int semaine) {
        this.semaine = semaine;
    }
    
    /**
     * set heure debut seance
     * @param heure_debut
     */
    public void setHeureDebut(String heure_debut) {
        this.heure_debut = heure_debut;
    }
    
    /**
     * set heure fin seance
     * @param heure_fin
     */
    public void setHeureFin(String heure_fin) {
        this.heure_fin = heure_fin;
    }
    
    /**
     * set date seance
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    /**
     * set etat seance
     * @param etat
     */
    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    /**
     * set cours seance
     * @param cours
     */
    public void setCours(Cours cours) {
        this.cours = cours;
    }
    
    /**
     * set type cours seance
     * @param type
     */
    public void setTypeCours(TypeCours type) {
        this.type = type;
    }
    
    /**
     * set enseignants seance
     * @param enseignants
     */
    public void setEnseignants(ArrayList<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }
    
    /**
     * set groupes seance
     * @param groupes
     */
    public void setGroupes(ArrayList<Groupe> groupes) {
        this.groupes = groupes;
    }
    
    /**
     * set salles seances
     * @param salles
     */
    public void setSalles(ArrayList<Salle> salles) {
        this.salles = salles;
    }
    
    /**
     * ajouter enseignant
     * @param enseignant
     */
    public void ajouterEnseignant(Enseignant enseignant) { //Un admin peut ajouter une séance à un enseignant
        this.enseignants.add(enseignant);
    }
    
    /**
     * ajouter groupe
     * @param groupe
     */
    public void ajouterGroupe(Groupe groupe) { //Un admin peut ajouter une séance à un groupe
        this.groupes.add(groupe);
    }
    
    /**
     * ajouer salle
     * @param salle
     */
    public void ajouterSalle(Salle salle) { //Un admin peut ajouter une séance à une salle
        this.salles.add(salle);
    }
    
    /**
     * calcul durée d'une seance
     * @return
     */
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
    
    /**
     * ajuster heure seance
     * @param duree
     * @return
     */
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

    /**
     * verifie si un groupe est dans la seance
     * @param id
     * @return
     */
    public boolean isThisGroupInThisSeance(int id)
    {
        for (int i = 0 ; i < groupes.size(); i++)
        {
            if(groupes.get(i).getId() == id)
            {
                i = 100; //???
                return true;
            }
        }
        return false;
    }

    /**
     * verifie si prof dans seance
     * @param id
     * @return
     */
    public boolean isThisTeacherInThisSeance(int id)
    {
        for (int i = 0 ; i < enseignants.size(); i++)
        {
            if(enseignants.get(i).getId() == id)
            {
                i = 100; //???
                return true;
            }
        }
        return false;
    }

    /**
     * verifie si salle dans seance
     * @param id
     * @return
     */
    public boolean isThisRoomInThisSeance(int id)
    {
        for (int i = 0 ; i < salles.size(); i++)
        {
            if(salles.get(i).getId() == id)
            {
                i = 100;
                return true;
            }
        }
        return false;
    }

    /**
     * retourne capacite salles
     * @return
     */
    public int placeInTotal()
    {
        int var = 0;
        for (int i = 0 ; i < salles.size() ; i++)
        {
            var += salles.get(i).getCapacite();
        }
        return var;
    }
    
    /**
     * ???
     * @return
     */
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
            str2 = str2 + " " + groupes.get(i).getNom()+ " "+ groupes.get(i).getPromotion().getNom();
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
                        + str3 + "\n " //Salles
                        + type.getNom() //Type du cours
        ;        
        return seance;
    }
    
    /**
     * retourne string des seances
     * @return
     */
    public ArrayList<String> toArrayListOfString() {      
        ArrayList<String> seance = new ArrayList<>();
        
        if(etat == 3)
            seance.add("ANNULEE");
        
        if(etat == 1)
            seance.add("EN COURS DE VALIDATION"); //Blinder l'affichage dans ce cas la aussi
            
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
            str2 = str2 + " " + groupes.get(i).getNom()+" "+groupes.get(i).getPromotion().getNom();
            if(i != groupes.size()-1)
                str2 = str2 + ", ";
        }
        
        seance.add(str2);
        
        String str3 = new String(); //Salles
        for(int i=0;i<salles.size();i++) {
            str3 = str3 + " " + salles.get(i).getNom() + " " + salles.get(i).getSite().getNom();
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

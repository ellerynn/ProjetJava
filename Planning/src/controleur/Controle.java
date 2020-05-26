package controleur;

import vue.*;
import java.sql.*;

import modele.*;
import dao.*;
import java.util.*;

public class Controle {
    private Fenetre fenetre;
    private Utilisateur utilisateur;
    private Etudiant etudiant;
    private Enseignant enseignant;
    private static Connection connect;
    
    //Constructeur
    public Controle() {
        fenetre = new Fenetre(this);
        utilisateur = new Utilisateur();
        etudiant = new Etudiant();
        enseignant = new Enseignant();
    }
    
    public static void main(String[] args) {
        //Connexion BDD
        Connection con;
        String nameDatabase = "edt";
        String loginDatabase = "root";
        String passwordDatabase = "";    
       
        //Ouverture interface graphique
        Controle controle = new Controle();
        
        //controle.AffecterEnseignantSeance(1,16);
        //controle.AffecterGroupeSeance(1,5);
        //controle.ModifierSeanceCoursNom(1,2);
        //controle.ModifierSeanceCoursType(1,4);
        //controle.AffecterSalleSeance(1,3);
        //controle.AjouterSalleSeance(1,4); en plus
        //controle.DeplacerSeance(1, 2, "2020-05-15", "13:45:00", "15:15:00", 1, 3);
        //controle.AjouterSeance(1, "2020-05-15", "13:45:00", "15:15:00", 1, 1, 1, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        //controle.AjouterEnseignantSeance(1,16);
        //controle.AjoutGroupeSeance(1,5);
        //controle.AnnulerSeance(1);
        //controle.ValiderSeance(3);
        //controle.EnleverGroupeSeance(1, 5);
        //controle.EnleverEnseignantSeance(1, 17);
        
    }
    
    //MODULE MAJ(Mise a Jour)/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
    * MODULE MAJ N°1 AFFECTER UN ENSEIGNANT A UNE SEANCE
*/
    public void AffecterEnseignantSeance(int id_seance, int id_enseignant) {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance = sDAO.find(id_seance);
        
        if(!seance.getEnseignants().isEmpty()){// Si il est pas vide, il y a des enseignants donc pas d'affectation
            System.out.println("L'enseignant que vous essayez d'affecter ne peut être affecter car un enseignant est deja affecte, veuillez AJOUTER un enseignant");
        }
        else{
            if(sDAO.isTeacherNotFreeForThisSeance(id_enseignant, seance)){//METHODE N°14 DANS SEANCEDAO
                System.out.println("Impossible d'affecter cet enseignant à cette séance car il est deja attitre dans ce créneau");
            }else{
                    sDAO.insertInJonction(id_seance, id_enseignant, 1);//METHODE N°18 DANS SEANCEDAO
                    System.out.println("L'enseignant a bien été affecté à cette séance ! ");
            }         
        }    
    }
   
/*
    * MODULE MAJ N°2  AFFECTER UN GROUPE A UNE SEANCE
*/
    
    public void AffecterGroupeSeance(int id_seance, int id_groupe) {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance = sDAO.find(id_seance);
        
        if(!seance.getGroupes().isEmpty()){//Si il est pas vide, ce n'est pas une affectation
            System.out.println("Le groupe que vous essayez d'affecter ne peut pas etre affecter.");
        }
        else{
            if(sDAO.isGroupNotFreeForThisSeance(id_groupe, seance)){//METHODE N°13 DANS SEANCEDAO
                System.out.println("Impossible de rajouter ce groupe car un cours est deja attitre dans ce créneaux");
            }else{
                if(seance.placeInTotal() >= sDAO.find_capacite_groupes_total(id_groupe, id_seance)){//METHODE N°16 DANS SEANCEDAO
                    sDAO.insertInJonction(id_seance, id_groupe, 2);//METHODE N°18 DANS SEANCEDAO
                    System.out.println("Le groupe a bien été ajouté à cette seance ! ");
                }else{System.out.println("Le nombre d'eleves dans le groupe depasse la capacité maximale de la seance");}
            }         
        }    
    }
  
/*
    * MODULE MAJ N°3 MODIFIER LE NOM DU COURS
*/
    
    public void ModifierSeanceCoursNom(int id_seance, int id_cours) {
        CoursDAO cDAO = new CoursDAO();
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance2 = sDAO.find(id_seance);
        seance2.setCours(cDAO.find(id_cours));
        seance2 = sDAO.update(seance2);
    }
    
/*
    * MODULE MAJ N°3 MODIFIER LE TYPE DU COURS
*/
    
    public void ModifierSeanceCoursType(int id_seance, int id_type) {
        TypeCoursDAO tDAO = new TypeCoursDAO();
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance2 = sDAO.find(id_seance);
        seance2.setTypeCours(tDAO.find(id_type));
        seance2 = sDAO.update(seance2);
    }
    
/*
    * MODULE MAJ N°4 AFFECTER UNE SALLE A UNE SEANCE
*/
  
    public void AffecterSalleSeance(int id_seance, int id_salle) {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance = sDAO.find(id_seance);

        if(!seance.getSalles().isEmpty()){ //si des salles sont dj affecté à la séance, on affecte pas
            System.out.println("La salle que vous essayez d'affecter ne peut pas etre affecter.");
        }
        else{
            if(sDAO.isSalleNotFreeForThisSeance(id_salle, seance)){ //METHODE N°15 DANS SEANCEDAO
                System.out.println("Impossible d'affecter cet salle à cette séance car il est deja attitre dans ce créneau");
            }else{

                SalleDAO salledao = new SalleDAO();
                Salle salle = salledao.find(id_salle);

                if(salle.getCapacite() >= sDAO.find_capacite_groupes_total(0,id_seance)){ //METHODE N°17 DANS SEANCEDAO
                    sDAO.insertInJonction(id_seance, id_salle, 3); //METHODE N°18 DANS SEANCEDAO
                    System.out.println("La salle a bien été affecté à cette séance ! ");
                }else{System.out.println("Le nombre d'eleves pour cette seance depasse la capacité maximale de la seance, veuillez affecter une salle avec une capacité plus grande");}         
            }         
        }    
    }
    /*
    * MODULE MAJ N°4 AJOUT SALLE A UNE SEANCE (EN PLUS)
    */
    public void AjouterSalleSeance(int id_seance, int id_salle)
    {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance = sDAO.find(id_seance);
        //Mettre condition de si pas ajouter
        if (!seance.getSalles().isEmpty()) //Si c'est pas vide, si vide appellée la fonction affectation
        {
            if(sDAO.canIAjouterSalleSeance(seance,id_salle)) //Créneau et duplication: no pblm
            {
                sDAO.insertInJonction(id_seance, id_salle, 3);//METHODE N°18 DANS SEANCEDAO
            }
        }
    }
/*
    * MODULE MAJ N°5 DEPLACER UNE SEANCE DE COURS //Même quand ça déplace sur lui même, ça marche
*/
    
    public void DeplacerSeance(int id_seance,int Semaine, String Date, String Heure_Debut, String Heure_Fin, int Etat, int id_salle) {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance = sDAO.find(id_seance);
        seance.setSemaine(Semaine);
        seance.setDate(Date);
        seance.setHeureDebut(Heure_Debut);
        seance.setHeureFin(Heure_Fin);
        seance.setEtat(Etat);
        seance.getSalles().clear(); //déplacer .... "dans UNE salle libre" -> Toute les salles dj présent disparaissent
        SalleDAO saDAO = new SalleDAO();
        seance.ajouterSalle(saDAO.find(id_salle)); //On récupère la salle, on l'add
        boolean isOk = true;
        for (int i = 0 ; i < Math.max(seance.getSalles().size(),Math.max(seance.getEnseignants().size(),seance.getGroupes().size()));i++)
        {
            if(i < seance.getSalles().size() && sDAO.isSalleNotFreeForThisSeance(seance.getSalles().get(i).getId(),seance))
            {
                isOk = false;
                System.out.println("Salles non dispo");
                i = 1000;
            }
                
            if(i < seance.getEnseignants().size() && sDAO.isTeacherNotFreeForThisSeance(seance.getEnseignants().get(i).getId(), seance) )
            {
                isOk = false;
                System.out.println("Enseignants non dispo");
                i = 1000;
            }
            if(i < seance.getGroupes().size() && sDAO.isGroupNotFreeForThisSeance(seance.getGroupes().get(i).getId(), seance))
            {
                isOk = false;
                System.out.println("groupes non dispo");
                i = 1000;
            }
        }
        if(seance.placeInTotal() < sDAO.find_capacite_groupes_total(0, id_seance))//METHODE N°16 DANS SEANCEDAO
        {
            isOk = false;
            System.out.println("Salle trop petit");
        }
        if (isOk == true)
        {
            seance = sDAO.update(seance); //Si tout ce qui est en haut est ok, on update tout
            System.out.println("Deplacer avec succes");
        }
            
        
    }
    
/*
    * MODULE MAJ N°6 AJOUTER UNE SEANCE
*/
    
    public void AjouterSeance(int Semaine, String Date, String Heure_Debut, String Heure_Fin, int Etat, int ID_cours,int ID_type, ArrayList<Integer> idGroupes, ArrayList<Integer> idEnseignants, ArrayList<Integer> idSalles) 
    {
        SeanceDAO sDAO = new SeanceDAO();
        CoursDAO cDAO = new CoursDAO();
        TypeCoursDAO tDAO = new TypeCoursDAO();
        GroupeDAO gDAO = new GroupeDAO();
        EnseignantDAO eDAO = new EnseignantDAO();
        SalleDAO salleDAO = new SalleDAO();
        Cours c = cDAO.find(ID_cours);
        TypeCours t = tDAO.find(ID_type);
        Seance seance = new Seance(Semaine, Heure_Debut, Heure_Fin,Date, Etat, c, t);
        boolean okForCreate = true;
        //On ajoute les salles en accord avec leur créneau dispo sans vérif la capa car rien à vérifier au début
        for (int i = 0 ; i < idSalles.size();i++)
        {
            if (sDAO.canIAjouterSalleSeance(seance, idSalles.get(i)))
            {
               seance.ajouterSalle(salleDAO.find(idSalles.get(i))); 
            }
            else{
                okForCreate = false;
                i = 100; // Dès qu'il y a un false on arrête tout
            }
        }
        if(okForCreate) //Si les vérifs des salles sont bon, on continu
        {
            for (int i = 0; i <Math.max(idGroupes.size(), idEnseignants.size()); i++) // Pour éviter 2 for
            {
                if (i< idGroupes.size())
                {
                    
                    if (sDAO.canIAjoutGroupeSeance(seance, idGroupes.get(i)))
                    {//Si groupes non duplication/pbl crénaux /Pas de pbl de place, tout est bon
                        seance.ajouterGroupe(gDAO.find(idGroupes.get(i)));
                    }
                    else
                    {
                        okForCreate = false;
                    }
                }
                if (i<idEnseignants.size())
                {
                    if ( sDAO.canIAjouterEnseignantSeance(seance, idEnseignants.get(i)))
                    {//Si enseignants non duplication/pbl crénaux tout est bon
                        seance.ajouterEnseignant(eDAO.find(idEnseignants.get(i)));
                    }
                    else{
                        okForCreate = false;
                    }
                }
            }
        }
        if (okForCreate) //Si il y a eu un faux, on ne create pas.
        {
            seance = sDAO.create(seance);
            System.out.println("Ajouter avec succes");
        }else{
            System.out.println("La seance n'a pas été ajouté");
        }
    }
    
/*
    * MODULE MAJ N°7 AJOUTER UN ENSEIGNANT A UNE SEANCE
*/
  
    public void AjouterEnseignantSeance(int id_seance, int id_enseignant)
    {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance = sDAO.find(id_seance);
        if(sDAO.canIAjouterEnseignantSeance(seance,id_enseignant))
        {
            sDAO.insertInJonction(id_seance, id_enseignant, 1);//METHODE N°18 DANS SEANCEDAO
        }
    }
    
/*
    * MODULE MAJ N°8  AJOUT D'UN GROUPE A UNE SEANCE
*/
    
    public void AjoutGroupeSeance(int id_seance, int id_groupe)
    {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance = sDAO.find(id_seance);
        if(sDAO.canIAjoutGroupeSeance(seance, id_groupe))
        {
            sDAO.insertInJonction(id_seance, id_groupe, 2);//METHODE N°18 DANS SEANCEDAO
        }
    }
    
    
/*
    * MODULE MAJ N°9 ANNULER UNE SEANCE
*/
    
    public void AnnulerSeance (int id_seance)
    {
        DAO<Seance> seanceDAO = new SeanceDAO();
        Seance sea = seanceDAO.find(id_seance);
        sea.setEtat(3);
        sea = seanceDAO.update(sea);
    }
    
/*
    * MODULE MAJ N°10 VALIDER UNE SEANCE
*/
    
    public void ValiderSeance (int id_seance){
        SeanceDAO seanceDAO = new SeanceDAO();
        Seance sea = seanceDAO.find(id_seance); 
        //LES CONDITIONS
        if( (!sea.getEnseignants().isEmpty()) && (!sea.getGroupes().isEmpty())){
            sea.setEtat(2);
            sea = seanceDAO.update(sea);//METHODE UPDATE DANS SEANCEDAO
            System.out.println("C'est fait votre séance a été validée");
        }
        else{System.out.println("On ne peux pas valider cette séance car il faut au minimum un enseignant et un groupe");} 
    }
    
/*
    * MODULE MAJ N°11 ENLEVER UN GROUPE D'UNE SEANCE
*/
    
    public void EnleverGroupeSeance(int id_seance, int id_groupe) {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance2 = sDAO.find(id_seance);
        //LES CONDITIONS
        if(!seance2.getGroupes().isEmpty()) //Si des groupes sont dans cette séance
        {
            if(seance2.getGroupes().size()>1){ // Si le nombre de groupe est sup à 1
                sDAO.DeleteInJonction(id_seance, id_groupe, 2);//METHODE N°12 DANS SEANCEDAO
                System.out.println("Le groupe a été enlevée, la séance est toujours disponible !");
            }
            if(seance2.getGroupes().size()==1){
                sDAO.DeleteInJonction(id_seance, id_groupe, 2);//METHODE N°12 DANS SEANCEDAO
                seance2.setEtat(1);
                sDAO.update(seance2);
                System.out.println("Le groupe a été enlevée, la seéance est en cours de validation !");
            }
        }
        else{
            System.out.println("Il n'y a pas de groupe à enlever dans seance_groupes!");
        }
    }
   
/*
    * MODULE MAJ N°11 ENLEVER UN ENSEIGNANT D'UNE SEANCE
*/
    
    public void EnleverEnseignantSeance(int id_seance, int id_enseignant) {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance2 = sDAO.find(id_seance);
        
        //LES CONDITIONS 
        if(!seance2.getEnseignants().isEmpty()){ //S'il y a des enseignants
            if(seance2.getEnseignants().size()>1){
                sDAO.DeleteInJonction(id_seance, id_enseignant, 1);//METHODE N°12 DANS SEANCEDAO
                System.out.println("L'enseignant a été enlevée, la séance est toujours disponible !");
            }
            if(seance2.getEnseignants().size()==1){
                sDAO.DeleteInJonction(id_seance, id_enseignant, 1);//METHODE N°20 DANS SEANCEDAO
                seance2.setEtat(1);
                sDAO.update(seance2);
                System.out.println("L'enseignant a été enlevée, la séance est en cours de validation !");
            }  
        }else{
            System.out.println("Il n'y a pas d'enseignant à enlever dans seance_enseignants!");
        }  
    }
    
   //FIN DU MODULE MAJ ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    
    //Getters
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    
    public Etudiant getEtudiant() {
        return etudiant;
    }
    
    public Enseignant getEnseignant() {
        return enseignant;
    }
    
    public Boolean demandeConnexion() {
        String email = fenetre.getConnexion().getEmail();
        String password = fenetre.getConnexion().getPassword();

        //UTILISATEURDAO recuperation de toutes les données
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        utilisateur = utilisateurDAO.find(email,password);
        
        if(!(utilisateur.getEmail().isEmpty() && utilisateur.getPassword().isEmpty())) { //On a trouvé un utilisateur 
            //CAS PARTICULIERS D'UTILISATEUR : enseignant, étudiant, référent (= enseignant)
            if(utilisateur.getDroit() == 4) { //C'est un etudiant (droit = 4)
                recupEtudiant();
            }
            
            if(utilisateur.getDroit() == 3) { //C'est un enseignant (droit = 3)   
                recupEnseignant();
            }
            
            if(utilisateur.getDroit() == 2 || utilisateur.getDroit() == 1) { //C'est un référent pédagogique (droit = 2) ou un administrateur
                
            }
            
            //On peut passer a la suite
            return true;
        }
              
        return false; //On n'a pas trouvé d'utilisateur correspondant
    }

    public void recupEnseignant() {
        enseignant.setId(utilisateur.getId());
        enseignant.setNom(utilisateur.getNom());
        enseignant.setPrenom(utilisateur.getPrenom());
        enseignant.setEmail(utilisateur.getEmail());
        enseignant.setPassword(utilisateur.getPassword());
        enseignant.setDroit(utilisateur.getDroit());

        DAO<Enseignant> enseignantDAO = new EnseignantDAO(); //Récupération des données de l'enseignant via DAO
        enseignant = enseignantDAO.find(enseignant.getId());
    }
    
    public void recupEtudiant() {
        etudiant.setId(utilisateur.getId());
        etudiant.setNom(utilisateur.getNom());
        etudiant.setPrenom(utilisateur.getPrenom());
        etudiant.setEmail(utilisateur.getEmail());
        etudiant.setPassword(utilisateur.getPassword());
        etudiant.setDroit(utilisateur.getDroit());
    }
    
    public void recupSeances(int semaine) {
        SeanceDAO seanceDAO = new SeanceDAO();
        ArrayList<Seance> seances = seanceDAO.findSeancesByUserAndWeek(etudiant.getId(), semaine);
        
        if(utilisateur.getDroit() == 3 || utilisateur.getDroit() == 2) {
            //On ne récupère que les séances de la semaine courante
            enseignant.setSeances(seanceDAO.findSeancesByUserAndWeek(enseignant.getId(), semaine));
                
        }
        
        if(utilisateur.getDroit() == 4) {
            //On ne récupère que les séances de la semaine courante
            etudiant.setSeances(seanceDAO.findSeancesByUserAndWeek(etudiant.getId(), semaine));
        }
    }
    
    public int semaine() {
        Calendar cal = Calendar.getInstance(); //Date du jour        
        return cal.get(Calendar.WEEK_OF_YEAR); //Semaine courante
    }
    
    
}

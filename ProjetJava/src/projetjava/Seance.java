/*SEANCE (ID, SEMAINE, DATE, HEURE_DEBUT, HEURE_FIN, ETAT, #ID_COURS, #ID_TYPE) 
Remarques : l’attribut SEMAINE indique le numéro de semaine dans une année civile. 
L’attribut ETAT indique si la séance est en cours de validation, validée ou annulée : cet attribut pourra être 
représenté par un numéro identifiant l’état de la séance.
*/

package projetjava;

public class Seance {
    private int id;
    private int semaine;
    private String heure_debut;
    private String heure_fin;
    private int etat;
    private Cours cours;
    private TypeCours type;
    
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
}
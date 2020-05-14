/*ETUDIANT (#ID_UTILISATEUR  , NUMERO, #ID_GROUPE …) 
Remarques : l’étudiant est un utilisateur (sa clé primaire est aussi une clé étrangère qui référence la clé primaire de 
la relation UTILISATEUR) et est affecté à un groupe
*/

package projetjava;

import java.util.ArrayList;

public class Etudiant extends Utilisateur {
    private int numero;
    private Groupe groupe;
    private ArrayList<Seance> seance; //Rajouté
    
    //Constructeur par défaut
    public Etudiant() {
        super();
        numero = 0;
        groupe = new Groupe();
        seance = new ArrayList();
    }
}
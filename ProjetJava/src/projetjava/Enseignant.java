/*ENSEIGNANT (#  ID_UTILISATEUR, #ID_COURS  ) 
Remarques : l’enseignant est un utilisateur et peut donner plusieurs cours
*/

package projetjava;

import java.util.ArrayList;

public class Enseignant extends Utilisateur {
    private ArrayList<Cours> cours;
    
    //Constructeur par défaut
    public Enseignant() {
        super();
        cours = new ArrayList<>();
    }
}
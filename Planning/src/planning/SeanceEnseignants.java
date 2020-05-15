package planning;

/*SEANCE_ENSEIGNANTS (#ID_SEANCE, #ID_ENSEIGNANT) 
Remarque : on peut affecter plusieurs enseignants à une séance 
*/

import java.util.ArrayList;

public class SeanceEnseignants extends Seance{
    private ArrayList<Enseignant> enseignants;
    
    //Constructeur par défaut
    public SeanceEnseignants() {
        super();
        enseignants = new ArrayList<>();
    }
    
    //Getters [en cours]
    
    //Setters [en cours]
}

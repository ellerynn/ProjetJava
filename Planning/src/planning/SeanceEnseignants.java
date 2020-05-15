package planning;

/*SEANCE_ENSEIGNANTS (#ID_SEANCE, #ID_ENSEIGNANT) 
Remarque     :   on peut affecter plusieurs enseignants à une séance 
*/

import java.util.ArrayList;

public class SeanceEnseignants {
    private Seance seance;
    private ArrayList<Enseignant> enseignants;
    
    //Constructeur par défaut
    public SeanceEnseignants() {
        seance = new Seance();
        enseignants = new ArrayList<>();
    }
}

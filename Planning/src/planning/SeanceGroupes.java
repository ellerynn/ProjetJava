package planning;

/*SEANCE_GROUPES (#ID_SEANCE, #ID_GROUPE) 
Remarque     :   on peut affecter plusieurs groupes à une séance 
*/

import java.util.ArrayList;

public class SeanceGroupes {
    private Seance seance;
    private ArrayList<Groupe> groupes;
    
    //Constructeur par défaut
    public SeanceGroupes() {
        seance = new Seance();
        groupes = new ArrayList<>();
    }
}
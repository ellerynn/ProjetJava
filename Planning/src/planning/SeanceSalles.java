package planning;

/*SEANCE_SALLES (#ID_SEANCE, #ID_SALLE) 
Remarque : on peut affecter plusieurs salles à une séance 
*/

import java.util.ArrayList;

public class SeanceSalles extends Seance {
    private ArrayList<Salle> salles;
    
    //Constructeur par défaut
    public SeanceSalles() {
        super();
        salles = new ArrayList<>();
    }
    
    //Getters [en cours]
    
    //Setters [en cours]
}

package planning;

/*SEANCE_SALLES (#ID_SEANCE, #ID_SALLE) 
Remarque : on peut affecter plusieurs salles à une séance 
*/

import java.util.ArrayList;

public class SeanceSalles {
    private Seance seance;
    private ArrayList<Salle> salles;
    
    //Constructeur par défaut
    public SeanceSalles() {
        seance = new Seance();
        salles = new ArrayList<>();
    }
}

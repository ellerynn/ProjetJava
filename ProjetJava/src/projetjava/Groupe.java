/*GROUPE (ID, NOM, #ID_PROMOTION) Remarque : le groupe est affecté à une promotion*/

package projetjava;

public class Groupe {
    private int id;
    private String nom;
    private Promotion promotion;
    
    //Constructeur par défaut
    public Groupe() {
        id = 0;
        nom = new String();
        promotion = new Promotion();
    }
}

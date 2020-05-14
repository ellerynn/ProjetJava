/*SALLE (ID, NOM, CAPACITE, #ID_SITE) 
Remarque : la salle indique sa capacité et le site, si l’école dispose de plusieurs sites
*/

package projetjava;

public class Salle {
    private int id;
    private String nom;
    private int capacite;
    private Site site;
    
    //Constructeur par défaut
    public Salle() {
        id = 0;
        nom = new String();
        capacite = 0;
        site = new Site();
    }
}
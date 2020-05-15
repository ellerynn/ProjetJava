package planning;

/*SITE (ID, NOM) 
Remarque : l’école peut avoir plusieurs sites (exemple dans le cas de l’ECE : Eiffel1, Eiffel2 etc.)
*/

public class Site {
    private int id;
    private String nom;
    
    //Constructeur par défaut
    public Site() {
        id = 0;
        nom = new String();
    }
}

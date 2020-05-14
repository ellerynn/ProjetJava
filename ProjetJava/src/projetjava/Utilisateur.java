/*UTILISATEUR (ID, EMAIL, PASSWD, NOM, PRENOM, DROIT) 
Remarques : cette relation indique le droit de consultation et de mise à jour aux données de l’emploi du temps. 
L’attribut DROIT indique le type de droit d’accès ou/et de mise à jour aux données, comme par exemple : 
- 1 pour un droit d’administrateur (service chargé de la planification) : il a le droit de consulter et de mettre à jour 
toutes les données de l’emploi du temps 
- 2 pour un droit de référent pédagogique : il peut consulter les emplois du temps de tous les enseignants, promotions, 
groupes, étudiants, cours et salles MAIS ne rien mettre à jour - 3 pour un droit d’enseignant : il ne peut consulter que 
son emploi du temps et le récapitulatif de ses cours sur une période - 4 pour un droit d’étudiant : il ne peut consulter 
que son emploi du temps et le récapitulatif de ses cours sur une période
*/
package projetjava;

public class Utilisateur {
    private int id;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private int droit; //cf. Note début Utilisateur.java
    
    //Constructeur par défaut
    public Utilisateur() {
        id = 0;
        email = new String();
        password = new String();
        nom = new String();
        prenom = new String();
        droit = 0;
    }
}

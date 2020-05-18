/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Emilie
 */
public class Developpeur {
	private long id = 0;
	private String nom = "";
        private String prenom = "";
	private Langage langage = new Langage();
	
	public Developpeur(){
        }
	
	public Developpeur(long id, String nom, String prenom, Langage langage) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.langage = langage;
	}

        public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Langage getLangage() {
		return langage;
	}

	public void setLangage(Langage langage) {
		this.langage = langage;
	}
	
	public String toString(){
		String str = 	"NOM : " + this.getNom() + "\n";
		str += 			"PRENOM : " + this.getPrenom() + "\n";
		str +=			this.langage.toString();
		str +=			"\n.....................................\n";
		
		return str;
	}
        
}

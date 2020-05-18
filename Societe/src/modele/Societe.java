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
import java.util.ArrayList;

public class Societe {

	private long id = 0;
	private String nom = "";
	private ArrayList<Developpeur> listDeveloppeur = new ArrayList<Developpeur>();
	
	public Societe(){}
	
	public Societe(long id, String nom, ArrayList<Developpeur> listDeveloppeur) {
		this.id = id;
		this.nom = nom;
		this.listDeveloppeur = listDeveloppeur;
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

	public ArrayList<Developpeur> getListDeveloppeur() {
		return listDeveloppeur;
	}

	public void setListDeveloppeur(ArrayList<Developpeur> listDeveloppeur) {
		this.listDeveloppeur = listDeveloppeur;
	}
	
	public void addDeveloppeur(Developpeur dev){
		this.listDeveloppeur.add(dev);
	}
	
	public Developpeur getDeveloppeur(int indice){
		return this.listDeveloppeur.get(indice);
	}
	
	public String toString(){
		String str =	"*******************************\n";
		str += 			"NOM : " + this.getNom() + "\n";
		str +=			"*******************************\n";
		str +=			"LISTE DES DEVELOPPEURS : \n";
		
		for(Developpeur dev : this.listDeveloppeur)
			str += dev.toString() + "\n";
		
		return str;
	}
}

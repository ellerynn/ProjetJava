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
public class Langage {
	private long id = 0;
	private String nom = "";
	
	public Langage(){
        }
	
	public Langage(long id, String nom){
		this.id = id;
		this.nom = nom;
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
	
	public String toString(){
		return "LANGAGE DE PROGRAMMATION : " + this.nom;
	}
	
}

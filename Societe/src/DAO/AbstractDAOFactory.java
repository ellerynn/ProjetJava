/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Emilie
 */

public abstract class AbstractDAOFactory {

	public abstract DAO getSocieteDAO();
	public abstract DAO getDeveloppeurDAO();
	public abstract DAO getLangageDAO();

	/**
	 * Méthode nous permettant de récupérer une factory de DAO
	 * @param type
	 * @return AbstractDAOFactory
	 */
	public static AbstractDAOFactory getFactory(FactoryType type){
		
		if(type.equals(type.DAO_FACTORY)) 
			return new DAOFactory();
		
		if(type.equals(type.XML_DAO_Factory))
			return new XMLDAOFactory();
		
		return null;
	}
	
}

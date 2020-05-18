/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import modele.*;

/**
 *
 * @author Emilie
 */
public class DAOFactory extends AbstractDAOFactory{

        @Override
	public DAO<Societe> getSocieteDAO(){
		return new SocieteDAO();
	}	
        @Override
	public DAO<Developpeur> getDeveloppeurDAO(){
		return new DeveloppeurDAO();
	}	
        @Override
	public DAO<Langage> getLangageDAO(){
		return new LangageDAO();
	}
}

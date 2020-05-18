/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//SOURCE : https://cyrille-herby.developpez.com/tutoriels/java/mapper-sa-base-donnees-avec-pattern-dao/?fbclid=IwAR0EUBDfFMiFfs89wUd4HVb5juW2BL7KIE-Xlq8_viC7pZw26Wf5rceAvXw
package modele;

import DAO.*;

/**
 *
 * @author Emilie
 */

public class Main {

	/**
	 * @param args
	 */
        public static void fonction()
        {
            DAO<Societe> societeDao = new SocieteDAO();
            for(long i = 1; i <= 2; i++)
		System.out.println(societeDao.find(i));
        }
        public static void fonctionLangage()
        {
            DAO<Langage> langageDAO = new LangageDAO(); 
            System.out.println("\n Avant création d'un nouveau langage :");
            for(int i = 1; i < 5; i++)
                    System.out.println(langageDAO.find(i).toString());
            //*
            Langage lan = new Langage();
            lan.setNom("ActionScript");		
            lan = langageDAO.create(lan);
            //*/

            System.out.println("\n Après création d'un nouveau langage :");
            for(int i = 1; i < 5; i++)
                    System.out.println(langageDAO.find(i).toString());

            lan.setNom("ActionScript 2");
            lan = langageDAO.update(lan);

            System.out.println("\n Après mise à jour de l'objet langage :");
            for(int i = 1; i < 5; i++)
                    System.out.println(langageDAO.find(i).toString());

            langageDAO.delete(lan);
            System.out.println("\n Après suppression l'objet langage :");
            for(int i = 1; i < 5; i++)
                    System.out.println(langageDAO.find(i).toString());
		
        }
        public static void fonctionDeveloppeur()
        {
            DAO<Developpeur> developpeurDAO = new DeveloppeurDAO();

            System.out.println("\n Avant création d'un nouveau développeur :");
            for(int i = 3; i < 5; i++)
                    System.out.println(developpeurDAO.find(i).toString());
            //*
            Developpeur dev = new Developpeur();
            dev.setNom("Coquille");
            dev.setPrenom("Olivier");
            Langage lan = new Langage();
            lan.setNom("COBOL");
            dev.setLangage(lan);

            dev = developpeurDAO.create(dev);
            //*/

            System.out.println("\n Après création d'un nouveau développeur :");
            for(int i = 3; i < 5; i++)
                    System.out.println(developpeurDAO.find(i).toString());

            dev.setNom("MERLET");
            dev.setPrenom("Benoit");
            lan.setNom("4gl");
            dev.setLangage(lan);
            dev = developpeurDAO.update(dev);

            System.out.println("\n Après mise à jour de l'objet développeur :");
            for(int i = 3; i < 5; i++)
                    System.out.println(developpeurDAO.find(i).toString());

            developpeurDAO.delete(dev);
            System.out.println("\n Après suppression l'objet développeur :");
            for(int i = 3; i < 5; i++)
                    System.out.println(developpeurDAO.find(i).toString());

        }
        public static void fonctionSociete()
        {
            DAO<Societe> societeDAO = new SocieteDAO();
		  
            System.out.println("\n Avant création d'une nouvelle société :");
            for(int i = 2; i < 4; i++)
                    System.out.println(societeDAO.find(i));

            Societe societe = new Societe();
            societe.setNom("SUN");

            Developpeur dev1 = new Developpeur();
            dev1.setNom("Bugault");
            dev1.setPrenom("Noël");
            Langage lan = new Langage();
            lan.setNom("Windev");
            dev1.setLangage(lan);

            societe.addDeveloppeur(dev1);
            societe = societeDAO.create(societe);

            System.out.println("\n Après création d'une nouvelle société :");
            for(int i = 2; i < 4; i++)
                    System.out.println(societeDAO.find(i));

            societe.setNom("SUN Microsystem");

            Developpeur dev2 = new Developpeur();
            dev2.setNom("MAHE");
            dev2.setPrenom("Marie-pierre");
            Langage lan2 = new Langage();
            lan2.setNom("Fortran");
            dev2.setLangage(lan2);

            societe.addDeveloppeur(dev2);

            societe = societeDAO.update(societe);

            System.out.println("\n Après modification de la société :");
            for(int i = 2; i < 4; i++)
                    System.out.println(societeDAO.find(i));


            societeDAO.delete(societe);

            System.out.println("\n Après suppression de la société :");
            for(int i = 2; i < 4; i++)
                    System.out.println(societeDAO.find(i));
		
        }
        
	public static void main(String[] args) {
		// TODO Auto-generated method stub
                fonctionLangage();
	}

}

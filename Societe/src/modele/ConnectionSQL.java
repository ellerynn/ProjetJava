/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Emilie
 */
public class ConnectionSQL {
    private static Connection connect;
    private static String user = "root";
    private static String url = "jdbc:mysql://localhost:3306/societe";
    private static String passwd = "";
    
    public static Connection getInstance(){
        
        if(connect == null){
                try {
                    Class.forName("com.mysql.jdbc.Driver"); 
                    connect = DriverManager.getConnection(url, user, passwd);
                }catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("BDD non trouvé, est ce bien edt ?");
                }catch(ClassNotFoundException tm) {
                        System.out.println("VERIFICATION : 3306 ou 3307 ?");
                }
        }		
        return connect;	
    }
}

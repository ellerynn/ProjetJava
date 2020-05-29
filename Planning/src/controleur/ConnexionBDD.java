package controleur;

import java.sql.*;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class ConnexionBDD {
    private static Connection connect;
    private static String nomBDD = "edt";
    private static String user = "root";
    private static String url = "jdbc:mysql://localhost:3306/" + nomBDD;
    private static String passwd = "";
    
    /**
     * connection à la BDD
     * @return
     */
    public static Connection getInstance() {
        if(connect == null){
            try {
                Class.forName("com.mysql.jdbc.Driver"); 
                connect = DriverManager.getConnection(url, user, passwd);
            }
            catch (SQLException e) {
                e.printStackTrace();
                System.out.println("BDD non trouvé, est ce bien edt ?");
            }
            catch(ClassNotFoundException tm) {
                System.out.println("VERIFICATION : 3306 ou 3307 voire 3308 ?");
            }
        }		
        return connect;	
    }
}
package vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FormConnexion {
    private JButton bouton;
    private JPanel content;
    private JTextField email;
    private JTextField password;
    
    /*Constructeur par défaut*/      
    public FormConnexion() { 
        bouton = new JButton("Se connecter");
        content = new JPanel();
        email = new JTextField(4); //4 colonnes
        password = new JTextField(4); //4 colonnes
    }  
}
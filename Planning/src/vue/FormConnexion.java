package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;

public class FormConnexion extends JPanel {
   //Attributs                    
    private JButton bouton;
    private JPanel champs;
    private JTextField email;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JPasswordField password;
    
    public FormConnexion() {
        label1 = new JLabel("Connexion");
        champs = new JPanel();
        label2 = new JLabel("Identifiant");
        email = new JTextField();
        label3 = new JLabel("Mot de passe");
        password = new JPasswordField();
        bouton = new JButton("Se connecter");
        
        label2.setHorizontalAlignment(SwingConstants.LEFT);
        label3.setHorizontalAlignment(SwingConstants.LEFT);
        
        GroupLayout c1 = new GroupLayout(champs);
        champs.setLayout(c1);
        c1.setHorizontalGroup(
            c1.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(email)
            .addGroup(c1.createSequentialGroup()
                .addGroup(c1.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(label2)
                    .addComponent(label3))
                .addGap(0, 211, Short.MAX_VALUE))
            .addComponent(password)
        );
        
        c1.setVerticalGroup(
            c1.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c1.createSequentialGroup()
                .addComponent(label2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label3)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        
        GroupLayout c2 = new GroupLayout(this);
        this.setLayout(c2);
        c2.setHorizontalGroup(
            c2.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c2.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addComponent(champs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
            .addGroup(c2.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label1)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, c2.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bouton)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        c2.setVerticalGroup(
            c2.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(c2.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(label1)
                .addGap(18, 18, 18)
                .addComponent(champs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bouton)
                .addContainerGap(97, Short.MAX_VALUE))
        );
    }
    
    //Getters
    public JButton getBouton() { //Pour passer au site lorsqu'on appuie sur le bouton connexion
        return bouton;
    }
    
    public String getEmail() {
        return email.getText();
    }
    
    public String getPassword() {
        return String.valueOf(password.getPassword());
    }
}
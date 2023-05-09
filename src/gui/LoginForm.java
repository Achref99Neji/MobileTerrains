/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import services.ServiceUtilisateur;

/**
 *
 * @author omare
 */
public class LoginForm extends Form {
            ServiceUtilisateur su = ServiceUtilisateur.getInstance();

    private final String MYLOGIN = "Admin";
    private final String MYPSD = "Admin";
    private final TextField tf_login = new TextField("", "Login");
    private final TextField tf_password = new TextField("", "Password");
    private final Button loginBtn = new Button("Valider");
    private final Button createBtn = new Button("Créer un compte");
    public LoginForm() {
        super("Sign in", BoxLayout.yCenter());
        tf_password.setConstraint(TextField.PASSWORD);
        addAll(tf_login, tf_password, loginBtn,createBtn);
        loginBtn.addActionListener((evt) -> {
           su.signin(tf_login.getText(), tf_password.getText());
        });
        createBtn.addActionListener((evt)->{
            new RegisterForm().show();
        });
        
    }

}

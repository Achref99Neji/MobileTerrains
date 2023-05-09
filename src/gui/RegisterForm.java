/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import entities.User;
import services.ServiceUtilisateur;

/**
 *
 * @author achre
 */
public class RegisterForm extends Form {
    ServiceUtilisateur su = ServiceUtilisateur.getInstance();



       TextField username = new TextField("","username");
       TextField email = new TextField("","email");
       TextField password = new TextField("","mot de passe");
       Button BtnRegister = new Button("Register");
       Button BtnLogIn = new Button("Already have an account? Go to Login");
     public RegisterForm(){
                 super("Sign up", BoxLayout.yCenter());

                  password.setConstraint(TextField.PASSWORD);

       addAll(username,email,password,BtnRegister,BtnLogIn);
       
       BtnRegister.addActionListener((e)-> {
           User user = new User(email.getText(),username.getText(),password.getText());
           su.signup(username, password, email);
           Dialog.show("User added",user.getUsername(),"ok",null);
                      new LoginForm().show();
           });
       BtnLogIn.addActionListener((e)-> {
           new LoginForm().show();
           });
    }
}

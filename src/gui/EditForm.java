/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import services.ServiceUtilisateur;
import util.SessionManager;

/**
 *
 * @author achre
 */
public class EditForm extends Form{
    ServiceUtilisateur su = ServiceUtilisateur.getInstance();
    TextField usernameField = new TextField(SessionManager.getUserName());
                    TextField emailField = new TextField(SessionManager.getEmail());
                    TextField numberField = new TextField(SessionManager.getPhonenumber());
                    Button updateButton=new Button("Update");

       
                
    public EditForm(){
    super("Edit Account", new BoxLayout(BoxLayout.Y_AXIS));
    updateButton.addActionListener((eve)->{
                    su.ModifyUser(usernameField.getText(),emailField.getText(),numberField.getText());
                    });

     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt3) -> {
            new HomeForm().showBack();
        });
    addAll(usernameField).add(emailField).add(numberField).add(updateButton);
    }
    
}

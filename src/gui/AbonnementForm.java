/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import entities.Abonnement;
import entities.Type;
import services.serviceAbonnement;
import util.SessionManager;

/**
 *
 * @author Nadoura kaaboura
 */
public class AbonnementForm extends Form {
    public AbonnementForm (int id,int price){ 
        
        Form form = new Form("Attributs", BoxLayout.y());

// Récupérer les attributs de l'objet Abonnement existant   

    
// Créer des champs de saisie pour chaque attribut
TextField terrainField = new TextField("","Terrain", 20, TextField.ANY);
//TextField playerField = new TextField("", "Joueur", 20, TextField.ANY);
//TextField startDateField = new TextField("", "Date de début", 20, TextField.ANY);
//TextField endDateField = new TextField("", "Date de fin", 20, TextField.ANY);
TextField couponField = new TextField("", "Coupon", 20, TextField.ANY);
Picker startDateField = new Picker();
startDateField.setType(Display.PICKER_TYPE_DATE);
Picker endDateField = new Picker();
endDateField.setType(Display.PICKER_TYPE_DATE);

// Créer un bouton
Button abonnementButton = new Button("S'abonner");
serviceAbonnement SA=serviceAbonnement.getInstance();

// Ajouter un ActionListener pour appeler la fonction abonnement
abonnementButton.addActionListener(e -> {
    // Appeler la fonction abonnement avec les valeurs des champs de saisie
    String terrain = terrainField.getText();
    String p = SessionManager.pref.get("id","id");
    String startDate = startDateField.getText();
    String endDate = endDateField.getText();
    String coupon = couponField.getText();
    double player = Double.parseDouble(p);
    if (coupon.isEmpty()){
    Abonnement abonnement = new Abonnement (player, terrain,price,startDate,endDate);

   
    SA.ajouterAbonnement(abonnement,id,"");
    Dialog.show("Success", "abonnement ajouté avec succés", "ok","cancel");}
    else {
            Abonnement abonnement = new Abonnement (player, terrain,price,startDate,endDate);
            SA.ajouterAbonnement(abonnement,id,coupon);
                Dialog.show("Success", "abonnement ajouté avec succés", "ok","cancel");

            

    }
    
    
});

Button back = new Button("Back");
        back.addActionListener( e-> new HomeForm().showBack());

// Ajouter le bouton à la forme
form.add(terrainField);
//form.add(playerField);  
form.add(startDateField);
form.add(endDateField);
form.add(couponField);
form.add(abonnementButton);
form.add(back);


form.show();

}


    
    }    
    


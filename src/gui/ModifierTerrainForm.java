/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.spinner.Picker;
import entities.Matche;
import entities.Terrain;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import services.MatcheService;
import services.TerrainService;

/**
 *
 * @author achref
 */
public class ModifierTerrainForm extends Form{
    
    private TerrainService ts = TerrainService.getInstance();
String fileName ="";


    public ModifierTerrainForm(Terrain fi) {
        super("Add Terrain", new BoxLayout(BoxLayout.Y_AXIS));
        
        
        
        TextField Largeur = new TextField("", "Largeur", 20, TextField.NUMERIC);
        Largeur.setText(String.valueOf(fi.getLargeur()));
        TextField Longeur = new TextField("", "Longeur", 20, TextField.NUMERIC);
        Longeur.setText(String.valueOf(fi.getLongeur()));
        TextField nbr_joueur = new TextField("", "Nbr de joueur", 20, TextField.NUMERIC);
        nbr_joueur.setText(String.valueOf(fi.getNbrJoueur()));
        TextField localisation = new TextField("", "Localisation", 20, TextField.ANY);
        localisation.setText(fi.getLocalisation());
        TextField prix = new TextField("", "Prix", 20, TextField.NUMERIC);
        prix.setText(String.valueOf(fi.getPrix()));
        TextField social_media = new TextField("", "Social media", 20, TextField.ANY);
        social_media.setText(fi.getSocialMedia());
        TextField description = new TextField("", "Description", 20, TextField.ANY);
        description.setText(fi.getDescription());
        TextField num_telephone = new TextField("", "Num telephone", 20, TextField.NUMERIC);
        num_telephone.setText(String.valueOf(fi.getNumTelephone()));


        add(Largeur);
        add(Longeur);
        add(nbr_joueur);
        add(localisation);
        add(prix);
        add(social_media);
        add(description);
        add(num_telephone);


        Button imge = new Button("Modifer Image"); 

        Button addButton = new Button("Modifer");
        
        
                   imge.addActionListener(new ActionListener() {
     @Override
            public void actionPerformed(ActionEvent evt) {
               
               fileName =randomName()+".jpg"; 
                
                MultipartRequest cr = new MultipartRequest();
                String filePath = Capture.capturePhoto();
                

                String mime = "image/jpeg";
                try {
                    cr.addData("file", filePath, mime);
                } catch (IOException ex) {
                }
                cr.setFilename("file", fileName);//any unique name you want

                cr.setUrl("http://127.0.0.1/imageServer.php");
                cr.setPost(true);
               
                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                System.out.println(filePath);

                NetworkManager.getInstance().addToQueueAndWait(cr);
                fi.setImgName(fileName);

                       
            }
   
   });
        addButton.addActionListener((evt) -> {
fi.setLargeur(Double.parseDouble(Largeur.getText()));
fi.setLongeur(Double.parseDouble(Longeur.getText()));
fi.setNbrJoueur(Double.parseDouble(nbr_joueur.getText()));
fi.setLocalisation(localisation.getText());
fi.setPrix(Double.parseDouble(prix.getText()));
fi.setSocialMedia(social_media.getText());
fi.setDescription(description.getText());
fi.setNumTelephone(num_telephone.getText());





           ts.modifierTerrain(fi);
           Dialog.show("Sucess", "Terrain Updated successfully", "Got it", null);
           new TerrainForm().showBack();

        });
        add(imge);
        add(addButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new TerrainForm().showBack();
        });
    }
        
        String randomName() {
        Random rnd = new Random();
        String lettersAndNumbersAndSymbols = "abcdefghijklmnopqrstuvwxyz0123456789_";
        String name = "";
        for (int i = 0; i < 51; i++) {
            name += lettersAndNumbersAndSymbols.charAt(rnd.nextInt(lettersAndNumbersAndSymbols.length()));
        }
        return name;
    }
}
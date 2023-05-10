/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import entities.Matche;
import entities.Terrain;
import java.util.ArrayList;
import java.util.List;
import services.MatcheService;
import services.TerrainService;

/**
 *
 * @author achref
 */
public class TerrainForm extends Form{
    TerrainService ts = TerrainService.getInstance();
               ImageViewer imgv;

    public TerrainForm() {
        
        super("Terrains", BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt3) -> {
            new HomeForm().showBack();
        });
        getToolbar().addCommandToOverflowMenu("Logout", null, (evt4) -> {
            new LoginForm().showBack();
        });
        //System.out.println(ts.fetchTerrain(1));
        for (Terrain m : ts.getAllTerrain()){
            addItem(m);
        }
        getToolbar().addMaterialCommandToRightBar("Créer", FontImage.MATERIAL_ADD, (evt5) -> {
            new AddTerrainForm().show();
        });
        
    }
    
    public void addItem(Terrain m)
    {
                                    Container ct = new Container(BoxLayout.y());
                            String url = "file://C:/Users/achre/Desktop/MOBIIILLLLEEE/wetransfer_g-t-a-rar_2023-05-08_1607/G-T-A/G-T-A/public/images/terrain/"+ m.getImgName();
                            int deviceWidth = Display.getInstance().getDisplayWidth();
                            Image placeholder = Image.createImage( deviceWidth/3,  deviceWidth/3, 0xbfc9d2);
                            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                            Image i = URLImage.createToStorage(encImage, "fileNameInStoragez" + m.getImgName(),url, URLImage.RESIZE_SCALE);
                                imgv = new ImageViewer();
                                imgv.setImage(i);
                                ct.add(imgv);

                            Label localisation = new Label("Localisation :"+m.getLocalisation());
                            Label largeur = new Label(String.valueOf("Largeur :"+m.getLargeur()));
                            Label longeur = new Label(String.valueOf("Longeur :"+m.getLongeur()));
                            Label nbrJoueur = new Label(String.valueOf("Nombre de joueur :"+m.getNbrJoueur()));
                            Label prix = new Label(String.valueOf("Prix :"+m.getPrix()));
                            Label socialMedia = new Label(String.valueOf("Socail Media :"+m.getSocialMedia()));
                            Label description = new Label("Description"+m.getDescription());
                            Label numTelephone = new Label("Telephone :"+m.getNumTelephone());
                            localisation.getAllStyles().setFgColor(0xf15f5f);
                            ct.add(localisation);
                            ct.add(largeur);
                            ct.add(longeur);
                            ct.add(nbrJoueur);
                            ct.add(prix);
                            ct.add(socialMedia);
                            ct.add(description);
                            ct.add(numTelephone);

                            

                            Button Modif = new Button("Modifier");
                            Button Supprimer = new Button("Supprimer");
                            Modif.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                                                new ModifierTerrainForm(m).show();
                                                    }   
                                            });
                          Supprimer.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                if (Dialog.show("Confirmation", "Voulez vous supprimer cett publication ?", "Oui", "Annuler")) {

           ts.deleteTerrain((int) m.getId());
                      new TerrainForm().showBack();


                            }
                   
                }   
        });
                       ct.add(Modif);
                       ct.add(Supprimer);


                       Label separator = new Label("","Separator");
                       ct.add(separator);
                       add(ct);
               }
    }
    

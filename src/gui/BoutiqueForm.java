/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.MultipleCategorySeries;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.models.XYSeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.views.PieChart;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;



import com.codename1.ui.Form;
import entities.Produit;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import services.ProduitService;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import services.TwilioSMS;
import util.SessionManager;
/**
 *
 * @author user
 */
public class BoutiqueForm extends Form {
    private Form current;
private Resources theme;
 EncodedImage enc ;
 Image imgs;
 ImageViewer imgv;
 String url = "http://127.0.0.1:8000/uploads/product/";
 
 
 
 TwilioSMS sms = new TwilioSMS();

public void init(Object context) {
    // use two network threads instead of one
    updateNetworkThreadCount(2);
    


    theme = UIManager.initFirstTheme("/theme");

    // Enable Toolbar on all Forms by default
    Toolbar.setGlobalToolbar(true);
    

    // Pro only feature
    Log.bindCrashProtection(true);

    addNetworkErrorListener(err -> {
        // prevent the event from propagating
        err.consume();
        if(err.getError() != null) {
            Log.e(err.getError());
        }
        Log.sendLogAsync();
        Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
    });        
}





public void start() {
    if (current != null) {
        current.show();
        return;
    }
    
    
    Button back = new Button("Back");
        back.addActionListener( e-> new HomeForm().showBack());
    
    // Création de la page principale
    Form f = new Form();
    f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    f.setScrollableY(true);
    f.setTitle("Liste des produits");
       // Barre de recherche
    TextField tf = new TextField("", "Rechercher un produit");
    f.add(tf);
    
           
    // Bouton pour afficher la liste des produits
    Button b = new Button("Lister produits");
        Button smsButton = new Button("notify admin");
        b.getAllStyles().setBorder(RoundBorder.create().rectangle(true));

    b.getUnselectedStyle().setBgColor(0xFFFFFF);
    b.getUnselectedStyle().setBgTransparency(255);
    smsButton.getUnselectedStyle().setBgColor(0x2DB944);
    smsButton .getUnselectedStyle().setBgTransparency(255);       

    f.add(b);
    
    f.add(smsButton);
    f.add(back);
    
    smsButton.addActionListener((e)->{TwilioSMS.sendSMS("+21652991102", "problem with my order user:" + SessionManager.getUserName());});
    // Écouteur de clic sur le bouton
    ProduitService ps = new ProduitService();
    b.addActionListener((e) -> {
//ArrayList<Produit> produits = ps.searchProd(searchQuery);
        List<Produit> produits = new ArrayList();
        // Récupération des produits depuis la base de données
        f.removeAll();
        f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    f.setScrollableY(true);
    f.setTitle("Liste des produits");
       // Barre de recherche
    f.add(tf);
           
    // Bouton pour afficher la liste des produits
    f.add(b);
        if (tf.getText().isEmpty())
        {produits = ps.affichageProduits();}
        
         // Vérification si la recherche n'est pas vide
        else  {
            // Récupération des produits correspondants à la recherche
            
            produits = ps.SearchProduct(tf.getText());
            
        }
            f.add(smsButton);
            f.add(back);
       
        
        // Parcours des produits
        for (Produit p : produits) {
            // Container pour afficher un produit
            Container c = new Container(new BorderLayout());
            c.getStyle().setBorder(Border.createLineBorder(1)); // Bordure
            
            // Image du produit
            EncodedImage enc;
            try {
                enc = EncodedImage.create("/load.jpg"); // Image de chargement
            } catch (IOException eimage) {
                enc = null;
            } 
            String imagename = p.getImage();
            URLImage imgs = URLImage.createToStorage(enc, url + imagename, url + imagename, URLImage.RESIZE_SCALE);
            ImageViewer imgv = new ImageViewer(imgs);
            c.addComponent(BorderLayout.WEST, imgv);
            
            // Détails du produit
            Container details = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            details.getStyle().setMargin(10, 10, 0, 0); // Marge
            details.getStyle().setPadding(0, 0, 0, 10); // Espacement avec l'image
            
            // Nom du produit
            String nom = p.getNom();
            Label l = new Label(nom);
            l.getAllStyles().setFgColor(0x007F00); // Couleur verte
            l.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM)); // Police en gras
            details.add(l);
            
            // Marque du produit
            String marque = p.getMarque();
            Label lmarque = new Label("Brand: " + marque);
            lmarque.getStyle().setFgColor(0x007F7F); // Couleur turquoise
            lmarque.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM)); // Police en gras
            details.add(lmarque);
            
            // Description du produit
            String description = p.getDescription();
            Label ldescription = new Label("Details: " + description);
            ldescription.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM)); // Police normale
            details.add(ldescription);
            
            // Prix du produit
            Double prix = p.getPrix();
            Label lprix = new Label("Prix: " +Double.toString(prix)+ "TND");
            lprix.getStyle().setFgColor(0x7F0000); // Couleur rouge foncé
            lprix.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM)); // Police en gras
            details.add(lprix);
            
                    // Disponibilité du produit
        Boolean exist = p.isExist();
        Label lexist = new Label("existe: " + (exist ? "Oui" : "Non"));
        lexist.getStyle().setFgColor(0x007F7F); // Couleur turquoise
        lexist.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM)); // Police en gras
        details.add(lexist);
        

        // Rating du produit
            Double rating = p.getRating();
Label lrating = new Label("Rating: " + rating + "/6");
lrating.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
details.add(lrating);


       
        
        // Ajout des détails dans le container principal
        c.addComponent(BorderLayout.CENTER, details);
        
        // Ajout du container principal dans la page
        f.add(c);
    }
    
    // Affichage de la page
    f.show();
});

// Affichage de la page principale
f.show();}





public void destroy() {
}

}



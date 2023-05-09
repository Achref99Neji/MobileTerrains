/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.io.Storage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import util.SessionManager;

/**
 *
 * @author omare
 */
public class HomeForm extends Form{
    public HomeForm() {
        super("Home", BoxLayout.xCenter());
        add(new Label("Welcome"));
        getToolbar().addCommandToSideMenu("Matches", null, (evt2) -> {
            new MatchesForm().show();
        });
        getToolbar().addCommandToSideMenu("Tournoi", null, (evt5) -> {
            new TournoiForm().show();
        });
        getToolbar().addCommandToSideMenu("Boutique", null, (evt5) -> {
            new BoutiqueForm().start();
        });
        getToolbar().addCommandToSideMenu("Abonnement", null, (evt4) -> {
            new TypeForm().show();
        });
        getToolbar().addCommandToSideMenu("Terrain", null, (evt4) -> {
            new TerrainForm().show();
        });
        getToolbar().addCommandToSideMenu("Edit Account",null,(evt10)->{new EditForm().show();});
        getToolbar().addCommandToSideMenu("Logout",null,(evt3)->{
               SessionManager.pref.clearAll();
               Storage.getInstance().clearStorage();
               Storage.getInstance().clearCache();
            new LoginForm().show();});
                 
        
            
        
    }
}

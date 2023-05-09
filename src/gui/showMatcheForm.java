/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import entities.Matche;
import services.MatcheService;
import util.SessionManager;

/**
 *
 * @author omare
 */
public class showMatcheForm extends Form {
    MatcheService ms = MatcheService.getInstance();
    
    public showMatcheForm(int id, String address) {
        super("Matche", BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt3) -> {
            new HomeForm().showBack();
        });
        getToolbar().addCommandToOverflowMenu("Logout", null, (evt4) -> {
            new LoginForm().showBack();
        });
        for (Matche m : ms.showMatche(id)){
            showM(m, address);
        }
    }
    
    public void showM(Matche match, String adr){
        
        Label nameLabel = new Label("Name: " + match.getName());
        this.add(nameLabel);

        Label jmaxLabel = new Label("Jmax: " + match.getJmax());
        this.add(jmaxLabel);

        Label dateLabel = new Label("Date: " + match.getDate());
        this.add(dateLabel);

        Label timeLabel = new Label("Time: " + match.getTime());
        this.add(timeLabel);
        
        Label weatherLabel = new Label("Terrain: " + match.getTerrain().getNom());
        this.add(weatherLabel);
        
        Label addressLabel = new Label("Addresse: " + adr);
        this.add(addressLabel);

        Label etatLabel = new Label("Etat: " + match.getEtat());
        this.add(etatLabel);
        

        SpanLabel team1Label = new SpanLabel("Team 1: " + match.getTeam1());
        this.add(team1Label);
        Button joinTeam1But = new Button("Rejoindre equipe 1");
        //joinTeam1But.addActionListener( e -> ms.joinTeam1(match, Double.parseDouble(SessionManager.pref.get("id","id"))));
        joinTeam1But.addActionListener((evt) -> {
            ms.joinTeam1(match, Double.parseDouble(SessionManager.pref.get("id","id")));
            new showMatcheForm((int) match.getId(), "Activate api").show();
            
        });
        this.add(joinTeam1But);

        Label team2Label = new Label("Team 2: " + match.getTeam2());
        this.add(team2Label);
        Button JoinTeam2But = new Button("Rejoindre equipe 2");
        //JoinTeam2But.addActionListener( e -> ms.joinTeam1(match, Double.parseDouble(SessionManager.pref.get("id","id"))));
        JoinTeam2But.addActionListener((evt) -> {
            ms.joinTeam2(match, Double.parseDouble(SessionManager.pref.get("id","id")));
            new showMatcheForm((int) match.getId(), "Activate api").showBack();
            
        });
        this.add(JoinTeam2But);

        this.refreshTheme();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import entities.Matche;
import java.util.ArrayList;
import java.util.List;
import services.MatcheService;
import services.TerrainService;

/**
 *
 * @author omare
 */
public class MatchesForm extends Form{
    TerrainService ts = TerrainService.getInstance();
    MatcheService ms = MatcheService.getInstance();
    public MatchesForm() {
        
        super("Matches", BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt3) -> {
            new HomeForm().showBack();
        });
        getToolbar().addCommandToOverflowMenu("Logout", null, (evt4) -> {
            new LoginForm().showBack();
        });
        //System.out.println(ts.fetchTerrain(1));
        for (Matche m : ms.fetchMatches()){
            addItem(m);
        }
        getToolbar().addMaterialCommandToRightBar("Créer", FontImage.MATERIAL_ADD, (evt5) -> {
            new AddMatcheForm().show();
        });
        
    }
    
    public void addItem(Matche m)
    {
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label id = new Label(String.valueOf(m.getId()));
        Label name = new Label(m.getName());
        Label DateT = new Label(m.getDate() + " At " + m.getTime());
        //Label Jmax = new Label(m.getTerrain().getNom());
        Label Etat = new Label(m.getEtat());
        
        C1.add(name);
        C2.add(DateT);
        //C3.add(Jmax);
        C4.add(Etat);
        C1.add(C2);
        C1.add(C3);
        C1.add(C4);
        C1.setUIID("C1");
        Style matchContainerStyle = C1.getUnselectedStyle();
        matchContainerStyle.setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
        // Add pointer pressed listener to C1
        C1.addPointerPressedListener((e) -> {
            // Navigate to the new form here
            //m.getTerrain().getAddressFromCoordinates();
            new showMatcheForm((int) m.getId(), "Activate api").show();
        });
        // Set selected style to highlight C1 on hover
        Style matchContainerSelectedStyle = new Style(matchContainerStyle);
        matchContainerSelectedStyle.setBgColor(ColorUtil.BLUE);
        C1.setSelectedStyle(matchContainerSelectedStyle);
        this.add(C1);
        this.refreshTheme();
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import entities.Matche;
import entities.Tournoi;
import services.TournoiService;

/**
 *
 * @author omare
 */
public class TournoiForm extends Form{
    
    TournoiService ts = TournoiService.getInstance();
    public TournoiForm() {
        super("Tournoi", BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt6) -> {
            new HomeForm().showBack();
        });
        getToolbar().addCommandToOverflowMenu("Logout", null, (evt7) -> {
            new LoginForm().showBack();
        });
        for (Tournoi t : ts.fetchTournois()){
            addItem(t);
        }
    }

    
    public void addItem(Tournoi t)
    {
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container C4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label id = new Label(String.valueOf(t.getId()));
        Label name = new Label(t.getName());
        Label DateT = new Label(t.getDate());
        Label Jmax = new Label(t.getNbr_equipe() + " de " + t.getNbr_Jequipe());
        Label Etat = new Label(t.getEtat());
        
        C1.add(name);
        C2.add(DateT);
        C3.add(Jmax);
        C4.add(Etat);
        C1.add(C2);
        C1.add(C3);
        C1.add(C4);
        C1.setUIID("C1");
        Style matchContainerStyle = C1.getUnselectedStyle();
        matchContainerStyle.setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
        this.add(C1);
        this.refreshTheme();
    }
}

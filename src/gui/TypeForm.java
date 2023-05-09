/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import entities.Type;
import java.util.ArrayList;
import services.serviceType;
import com.codename1.ui.List;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nadoura kaaboura
 */
public class TypeForm extends Form{


    public TypeForm() {
        super("My List", BoxLayout.y());
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt3) -> {
            new HomeForm().showBack();
        });

        serviceType ST = new serviceType();
        ArrayList<Type> result = new ArrayList<>();
        result=ST.affichageTypes();
        
        MultiList mList = new MultiList();
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>();

        for (Type type : result) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("Line1", type.getPrice());
            listItem.put("Line2", type.getDescription());
            listItem.put("index", type.getId()); 
            model.addItem(listItem);
        }
        mList.setModel(model);
mList.addActionListener(e -> {
    float selectedIndex = (float) ((Map<String, Object>) mList.getSelectedItem()).get("index");
        float selectedPrice = (float) ((Map<String, Object>) mList.getSelectedItem()).get("Line1");

    System.out.println("index:"+selectedIndex);
    // Create an instance of AbonnementForm and show it on the screen
    AbonnementForm abonnementForm = new AbonnementForm((int)selectedIndex,(int)selectedPrice);
});
        this.add(mList);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.spinner.Picker;
import entities.Matche;
import entities.Terrain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import services.MatcheService;
import services.TerrainService;

/**
 *
 * @author omare
 */
public class AddMatcheForm extends Form{
    
        
    private TextField nameField;
    private TextField jmaxField;
    private Picker dateField;
    private Picker timeField;
    private ComboBox<String> etatField;
    private ComboBox<String> terrainField;
    private MatcheService ms = MatcheService.getInstance();
    private TerrainService ts = TerrainService.getInstance();
    private HashMap<String, Terrain> terrainMap; // to map the selected terrain name to its corresponding Terrain object

    public AddMatcheForm() {
        super("Add Match", new BoxLayout(BoxLayout.Y_AXIS));
        
        nameField = new TextField("", "Name", 20, TextField.ANY);
        jmaxField = new TextField("", "Jmax", 20, TextField.NUMERIC);
        dateField = new Picker();
        dateField.setType(Display.PICKER_TYPE_DATE);
        timeField = new Picker();
        timeField.setType(Display.PICKER_TYPE_TIME);
        etatField = new ComboBox<>("Ouvert", "Privée");
        etatField.setSelectedIndex(0);
        
        // Create the list of terrain names and the terrain map
        List<Terrain> terrains = ts.fetchTerrains();
        ArrayList<String> terrainNames = new ArrayList<>();
        terrainMap = new HashMap<>();
        for (Terrain terrain : terrains) {
            terrainNames.add(terrain.getNom());
            terrainMap.put(terrain.getNom(), terrain);
        }
        
        // Create the terrain picker and set its model to the list of terrain names
        terrainField = new ComboBox<>(terrainNames.toArray(new String[0]));
        
        addField("Name", nameField);
        addField("Jmax", jmaxField);
        addField("Date", dateField);
        addField("Time", timeField);
        addField("Etat", etatField);
        addField("Terrain", terrainField);
        
        Button addButton = new Button("Add");
        addButton.addActionListener((evt) -> {
            Terrain selectedTerrain = terrainMap.get(terrainField.getSelectedItem()); // get the selected terrain object from the map
            
            if (ms.addMatche(new Matche(nameField.getText(),Integer.parseInt(jmaxField.getText()), dateField.getText(), timeField.getText(), etatField.getSelectedItem(), selectedTerrain))) {
                Dialog.show("Sucess", "matche Inserted successfully", "Got it", null);
            }else {
                Dialog.show("Success", "matche Inserted successfully", "Got it", null);
            }
        });
        add(addButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new MatchesForm().showBack();
        });
    }

    private void addField(String label, Component field) {
        Container row = new Container(new GridLayout(2, 1));
        row.addComponent(new Label(label));
        row.addComponent(field);
        add(row);
    }
}
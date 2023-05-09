/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.URL;
import com.codename1.io.Util;
import com.codename1.ui.events.ActionListener;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

/**
 *
 * @author omare
 */
public class Terrain {
    
    private double id;
    private String nom;
    private double largeur, longeur;
    private double nbrJoueur;
    private String localisation;
    private double prix;
    private String socialMedia;
    private String description;
    private String numTelephone;
    private String imgName;
    private String adress;

    public Terrain() {
    }

    public Terrain(int id, double nbrJoueur, String localisation, double prix, String imgName) {
        this.id = id;
        this.nbrJoueur = nbrJoueur;
        this.localisation = localisation;
        this.prix = prix;
        this.imgName = imgName;
    }

    public Terrain(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public double getLongeur() {
        return longeur;
    }

    public void setLongeur(double longeur) {
        this.longeur = longeur;
    }

    public double getNbrJoueur() {
        return nbrJoueur;
    }

    public void setNbrJoueur(double nbrJoueur) {
        this.nbrJoueur = nbrJoueur;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    
    

    public String getAdress() {
        return adress;
    }
    
    
    public void getAddressFromCoordinates() {
    String[] parts = Util.split(localisation, ", ");
    double lat = Double.parseDouble(parts[0]);
    double lon = Double.parseDouble(parts[1]);
    String url = "https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=" + lat + "&lon=" + lon + "&accept-language=fr";
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setUserAgent("MyApp/1.0");
    
    request.addResponseListener((e) -> {
        try {
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            String address = (String) response.get("display_name");
            System.out.println(address);
            adress = address;
        } catch (Exception ex) {
            System.out.println("entities.Terrain.getAddressFromCoordinates()");
        }
    });

    request.addExceptionListener((e) -> {
        System.err.println("err" + e);
    });

    NetworkManager.getInstance().addToQueue(request);
}
    
    
    @Override
    public String toString() {
        return "Terrain{" + "id=" + id + ", largeur=" + largeur + ", longeur=" + longeur + ", nbrJoueur=" + nbrJoueur + ", localisation=" + localisation + ", prix=" + prix + ", socialMedia=" + socialMedia + ", description=" + description + ", numTelephone=" + numTelephone + ", imgName=" + imgName + '}';
    }
    
}

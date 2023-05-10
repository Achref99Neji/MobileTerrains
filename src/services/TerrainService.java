/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entities.Matche;
import entities.Terrain;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.Statics;

/**
 *
 * @author achref
 */
public class TerrainService {
    ConnectionRequest req;
    static TerrainService instance = null;
    
    boolean resultOK = false;
    List<Terrain> terrains = new ArrayList<>();
    
    private TerrainService(){
        req = new ConnectionRequest();
    }
    
    public static TerrainService getInstance() {
        if (instance == null) {
            instance = new TerrainService();
        }
        return instance;
    }
    
    public List<Terrain> fetchTerrains(){
        req = new ConnectionRequest();
        
        String fetchURL = Statics.BASE_URL + "/getTerrains";
        req.setUrl(fetchURL);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt){
                terrains = parseTerrains(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return terrains;
    }
    
    
    

 
public boolean addTerrain(Terrain u) {
    String url = Statics.BASE_URL + "/mobile/addterrain?"
            + "largeur=" + u.getLargeur()
            + "&longeur=" + u.getLongeur()
            + "&nbr_joueur=" + u.getNbrJoueur()
            + "&localisation=" + u.getLocalisation()
            + "&prix=10" + u.getPrix()
            + "&social_media=" + u.getSocialMedia()
            + "&description=" + u.getDescription()
            + "&num_telephone=" + u.getNumTelephone()
            + "&image=" + u.getImgName();
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    System.out.println(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}

public boolean modifierTerrain(Terrain u) {
    String url = Statics.BASE_URL + "/mobile/modifierTerrain/" + (int) u.getId() +"?"
            + "largeur=" + u.getLargeur()
            + "&longeur=" + u.getLongeur()
            + "&nbr_joueur=" + u.getNbrJoueur()
            + "&localisation=" + u.getLocalisation()
            + "&prix=" + u.getPrix()
            + "&social_media=" + u.getSocialMedia()
            + "&description=" + u.getDescription()
            + "&num_telephone=" + u.getNumTelephone()
            + "&image=" + u.getImgName();
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    System.out.println(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; 
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
    public boolean deleteTerrain(int id) {
    String url = Statics.BASE_URL + "/mobile/delete/"+id;
    req.setUrl(url);
    req.setHttpMethod("DELETE");
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; 
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
    }
    
        public boolean sendEmailApi() {
    String url = Statics.BASE_URL + "/mobile/email";
    req.setUrl(url);
    req.setHttpMethod("POST");
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; 
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
    }

        public List<Terrain> getAllTerrain(){
        req = new ConnectionRequest();
        
        String fetchURL = Statics.BASE_URL + "/mobile/terrain";
        req.setUrl(fetchURL);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt){
                terrains = parseTerrains(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return terrains;
    }
    
    public List<Terrain> parseTerrains(String jsonText){
        terrains = new ArrayList<>();
        
        JSONParser jp = new JSONParser();
        try{
            Map<String, Object> terrainsListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) terrainsListJSON.get("root");
            
            for(Map<String, Object> item : list){
                Terrain t = new Terrain();
t.setId(((double) item.get("id")));
                if (item.get("name") != null){
                                t.setNom((String) item.get("name"));

            }
                t.setNbrJoueur((double) item.get("Jmax"));
                t.setLargeur((double) item.get("largeur"));
                t.setLongeur((double) item.get("longeur"));
                t.setPrix((double) item.get("prix"));
                t.setImgName((String) item.get("img"));
                t.setLocalisation((String) item.get("localisation"));               
                      if (item.get("socialMedia") != null){
t.setSocialMedia((String) item.get("socialMedia"));
            }                          
                                            if (item.get("description") != null){
t.setDescription((String) item.get("description"));
            }  
         if (item.get("numTelephone") != null){
                t.setNumTelephone((String) item.get("numTelephone"));
            }  

                
                terrains.add(t);
            }
        }catch(IOException ex){
            System.out.println("err");
        }
        return terrains;
    }
  ////////////end  
    public Terrain fetchTerrain(double id) {
        req = new ConnectionRequest();
        
        String fetchURL = Statics.BASE_URL + "/getTerrain";
        req.setUrl(fetchURL);
        req.setPost(false);
        
        req.addArgument("id","" + id);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                terrains = parseTerrains(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return terrains.get(0);
    }
   
    
}

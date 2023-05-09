/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Matche;
import entities.Tournoi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.Statics;

/**
 *
 * @author omare
 */
public class TournoiService {
    ConnectionRequest req;
    static TournoiService instance = null;
    
    boolean resultOK = false;
    List<Tournoi> tournois = new ArrayList<>();
    
    private TournoiService(){
        req = new ConnectionRequest();
    }
    
    public static TournoiService getInstance() {
        if (instance == null) {
            instance = new TournoiService();
        }
        return instance;
    }
    
    public List<Tournoi> fetchTournois(){
        req = new ConnectionRequest();
        
        String fetchURL = Statics.BASE_URL + "/getTournois";
        req.setUrl(fetchURL);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tournois = parseMatches(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tournois;
    }
    
    public List<Tournoi> parseMatches(String jsonText){
        tournois = new ArrayList<>();
        
        JSONParser jp = new JSONParser();
        try {
            Map<String, Object> MatchesListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) MatchesListJSON.get("root");
            
            for (Map<String, Object> item : list){
                Tournoi t = new Tournoi();
                t.setId((double) item.get("id"));
                t.setName((String) item.get("name"));
                t.setNbr_equipe((double) item.get("nbr_equipe"));
                t.setDate((String) item.get("date"));
                t.setNbr_Terrain((double) item.get("nbr_Terrains"));
                t.setNbr_Jequipe((double) item.get("nbr_Jequipe"));
                t.setEtat((String) item.get("etat"));
                
                tournois.add(t);
            }
        }catch (IOException ex) {   
        }
        return tournois;
    }
    
    public boolean addMatche (Matche m) {
        
        String addURL = Statics.BASE_URL + "/addMatche";
        
        req.setUrl(addURL);
        req.setPost(false);
        
        req.addArgument("name", m.getName());
        req.addArgument("Jmax", String.valueOf(m.getJmax()));
        req.addArgument("date", m.getDate());
        req.addArgument("time", m.getTime());
        req.addArgument("etat", m.getEtat());
        
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
    
    public boolean showMatche (int id) {
        
        String addURL = Statics.BASE_URL + "/showMatche";
        
        req.setUrl(addURL);
        req.setPost(false);
        
        req.addArgument("id", String.valueOf(id));
        
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
}

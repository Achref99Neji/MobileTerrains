/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import entities.Abonnement;
import entities.Coupon;
import entities.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Service class for handling Abonnement objects
 */
public class serviceAbonnement {

    public static serviceAbonnement instance = null;
    public static boolean resultOk = true;
    private ConnectionRequest req;

    public serviceAbonnement() {
        this.req = new ConnectionRequest();
    }

    /**
     * Returns an instance of this class
     *
     * @return
     */
    public static serviceAbonnement getInstance() {
        if (instance == null) {
            instance = new serviceAbonnement();
        }
        return instance;
    }

    /**
     * Sends a request to add a new subscription
     *
     * @param abonnement The Abonnement object to add
     */
    public void ajouterAbonnement(Abonnement abonnement,int id,String coupon) {
        req.setPost(false);
        String url = "http://127.0.0.1:8000/mobile/Addabonnements";
        req.setUrl(url);

        // Ajouter les arguments de la requête
        req.addArgument("player", String.valueOf(abonnement.getPlayer()));
        req.addArgument("terrain", abonnement.getTerrain());
        req.addArgument("prixTot", Float.toString(abonnement.getPrixTot()));
        req.addArgument("startDate", abonnement.getStartDate().toString());
        req.addArgument("endDate", abonnement.getEndDate().toString());
        req.addArgument("code",coupon);
        req.addArgument("tid", Integer.toString(id));

        // Envoyer la requête
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    /**
     * Sends a request to add a new subscription
     *
     * @param abonnement The Abonnement object to add
     */
    public void ajouterAbonnementRemise(Abonnement abonnement,String coupon,int id) {
                req = new ConnectionRequest();
Coupon result = new Coupon();
req.setPost(false);
        String url1 = "http://127.0.0.1:8000/coupon/get-coupon?code="+coupon;
        req.setUrl(url1);
        NetworkManager.getInstance().addToQueueAndWait(req);
        req.addResponseListener((a)->{
              JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> mapTypes = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                System.out.println("mm:"+mapTypes);
                List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapTypes.get("red");
                
                for (Map<String, Object> obj : listOfMaps) {
                    Coupon type = new Coupon();
                    System.out.println("o:"+obj);
                    int reduction = Integer.parseInt(obj.get("pourcentageReduction").toString());
                    type.setPourcentageReductin(reduction);
                    result.setPourcentageReductin(type.getPourcentageReductin());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        String url = "http://127.0.0.1:8000/mobile/Addabonnements";
        req = new ConnectionRequest();
        req.setPost(false);
        req.setUrl(url);
        System.out.println("resu:"+result.getPourcentageReductin());
        // Ajouter les arguments de la requête
        req.addArgument("player", String.valueOf(abonnement.getPlayer()));
        req.addArgument("terrain", abonnement.getTerrain());
        req.addArgument("prixTot", Float.toString(abonnement.getPrixTot()-(abonnement.getPrixTot()*result.getPourcentageReductin()/100)));
        req.addArgument("startDate", abonnement.getStartDate().toString());
        req.addArgument("endDate", abonnement.getEndDate().toString());
        req.addArgument("tid", Integer.toString(id));

        // Envoyer la requête
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    /**
     * Sends a request to modify an existing subscription
     *
     * @param abonnement The Abonnement object to modify
     */
    public void modifierAbonnement(Abonnement abonnement) {
        String url = "http://127.0.0.1:8000/modifier";
        req = new ConnectionRequest();
        req.setPost(false);
        req.setUrl(url);

        // Ajouter les arguments de la requête
        req.addArgument("idAbonnement", Integer.toString(abonnement.getId()));
        req.addArgument("player", String.valueOf(abonnement.getPlayer()));
        req.addArgument("terrain", abonnement.getTerrain());
        req.addArgument("startDate", abonnement.getStartDate().toString());
        req.addArgument("endDate", abonnement.getEndDate().toString());

        // Envoyer la requête
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

}

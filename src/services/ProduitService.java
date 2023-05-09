/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.codename1.io.ConnectionRequest;
import entities.Produit;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import entities.Matche;
import java.io.*;
import java.util.*;

public class ProduitService {

    public static ProduitService instance = null;

    public static boolean resultOk = true;

    // initialization connection request 
    private ConnectionRequest req;
    
    List<Produit> result = new ArrayList<>();

    public static ProduitService getInstance() {
        if(instance == null )
            instance = new ProduitService();
        return instance;
    }

    public ProduitService() {
        req = new ConnectionRequest();
    }

    public List<Produit> affichageProduits() {
        //ArrayList<Produit> result = new ArrayList<>();

        req.setUrl("http://127.0.0.1:8000/product/api/produits");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapProduits = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapProduits.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Produit produit = new Produit();

                        // always use float in codename one
                        float id = Float.parseFloat(obj.get("id").toString());
                        String nom = obj.get("nom").toString();
                        Double prix = Double.valueOf(obj.get("prix").toString());
                        Double rating = Double.valueOf(obj.get("rating").toString());

                        Boolean exist = Boolean.valueOf(obj.get("existe").toString());

                        String image = ((String) obj.get("image"));
                        String description = ((String) obj.get("description"));
                        String marque = obj.get("marque").toString();
                         

                        produit.setId((int) id);
                        produit.setNom(nom);
                        produit.setDescription(description);
                        produit.setMarque(marque);
                        produit.setPrix(prix);
                        produit.setExist(exist);
                        produit.setImage(image);
                        produit.setRating(rating);
                        System.out.println("iterating");
                    
                        // insert data into ArrayList result
                        result.add(produit);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req); // execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
    }

 public ArrayList<Produit> rechercherProduitssss(String recherche) {
    ArrayList<Produit> produitsTrouves = new ArrayList<>();
    
    // Récupération des produits depuis votre projet Symfony
    ConnectionRequest request = new ConnectionRequest() {
        @Override
        protected void readResponse(InputStream input) throws IOException {
            JSONParser parser = new JSONParser();
            Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
            List<Map<String, Object>> produits = (List<Map<String, Object>>) response.get("data");

            // Parcours des produits récupérés
            for (Map<String, Object> produitMap : produits) {
                // Création d'un objet Produit à partir des données récupérées
                Produit produit = new Produit();
                produit.setId(Integer.parseInt(produitMap.get("id").toString()));
                produit.setNom(produitMap.get("nom").toString());
                produit.setMarque(produitMap.get("marque").toString());
                produit.setDescription(produitMap.get("description").toString());
                produit.setImage(produitMap.get("image").toString());
                produit.setPrix(Double.parseDouble(produitMap.get("prix").toString()));

                // Ajout du produit à la liste des produits trouvés si le nom correspond à la recherche
                if (produit.getNom().toLowerCase().contains(recherche.toLowerCase())) {
                    produitsTrouves.add(produit);
                }
            }
        }
    };
    request.setUrl("http://localhost:8000/product/api/produits");
    request.setPost(false);
    NetworkManager.getInstance().addToQueueAndWait(request);
    
    return produitsTrouves;
}

 
 
 
   //Update 
    public boolean modifierProduit(Produit Produit) {
        String url = "http://localhost:8000/product/modifier/" + Produit.getId() ;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;

    }
    
    public ArrayList<Produit> rechercherProduits(String recherche) {
    ArrayList<Produit> produitsTrouves = new ArrayList<>();
    
    // Récupération des produits depuis votre projet Symfony
    ConnectionRequest request = new ConnectionRequest() {
        @Override
        protected void readResponse(InputStream input) throws IOException {
            JSONParser parser = new JSONParser();
            Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
            List<Map<String, Object>> produits = (List<Map<String, Object>>) response.get("data");

            // Parcours des produits récupérés
            for (Map<String, Object> produitMap : produits) {
                // Création d'un objet Produit à partir des données récupérées
                Produit produit = new Produit();
                produit.setId(Integer.parseInt(produitMap.get("id").toString()));
                produit.setNom(produitMap.get("nom").toString());
                produit.setMarque(produitMap.get("marque").toString());
                produit.setDescription(produitMap.get("description").toString());
                produit.setImage(produitMap.get("image").toString());
                produit.setPrix(Double.parseDouble(produitMap.get("prix").toString()));

                // Ajout du produit à la liste des produits trouvés si le nom correspond à la recherche
                if (produit.getNom().toLowerCase().contains(recherche.toLowerCase())) {
                    produitsTrouves.add(produit);
                }
            }
        }
    };
    request.setUrl("http://localhost:8000/product/api/produits");
    request.setPost(false);
    NetworkManager.getInstance().addToQueueAndWait(request);
    
    return produitsTrouves;
}
    
    //search 
    public ArrayList<Produit> searchProd(String nom){
         ArrayList<Produit> prod =new ArrayList<>();
        req = new ConnectionRequest();
        String fetchUrl ="http://localhost:8000/product/recherche/"+nom;
        req.setUrl(fetchUrl);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //recuperer data JSON
               
              List<Produit> prod = parseprod(new String (req.getResponseData()));
                req.removeResponseListener(this);
                
            }
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    return prod;
    }
    
    //Parse JSON vers prod
    public List<Produit> parseprod(String jsonData){
    List<Produit> prod =new ArrayList<>();
    JSONParser jp = new JSONParser();
    try {
        Map<String,Object> rdvListJson = jp.parseJSON(new CharArrayReader(jsonData.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String,Object>>) rdvListJson.get("root");
        for (Map<String, Object> item : list) {
            Produit r = new Produit();
            r.setId(((Double) item.get("id")).intValue());
            r.setNom((String) item.get("nom"));
            r.setDescription((String) item.get("description"));
            Object prixValue = item.get("prix");
            if (prixValue instanceof Double) {
                r.setPrix((Double) prixValue);
            } else if (prixValue instanceof String) {
                r.setPrix(Double.valueOf((String) prixValue));
            }
            r.setMarque((String) item.get("marque"));
            prod.add(r);
        }
    } catch (IOException ex) {
    }
    return prod;
}

    
    public ArrayList<Produit> SearchProduct(String name) {
    ArrayList<Produit> result = new ArrayList<>();
    try {
        req.setUrl("http://127.0.0.1:8000/product/rechercher_produit?nom="+name);
       // NetworkManager.getInstance().addToQueueAndWait(req); // moved here
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    
   req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapProduits = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapProduits.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Produit produit = new Produit();

                        // always use float in codename one
                        float id = Float.parseFloat(obj.get("id").toString());
                        String nom = obj.get("nom").toString();
                        Double prix = Double.valueOf(obj.get("prix").toString());
                        Double rating = Double.valueOf(obj.get("rating").toString());

                        Boolean exist = Boolean.valueOf(obj.get("existe").toString());

                        String image = obj.get("image").toString();
                        if(image == null){
                        image="no-image-icon-15.png";
                        }
                        String description = obj.get("description").toString();
                        String marque = obj.get("marque").toString();

                        produit.setId((int) id);
                        produit.setNom(nom);
                        produit.setDescription(description);
                        produit.setMarque(marque);
                        produit.setPrix(prix);
                        produit.setExist(exist);
                        produit.setImage(image);
                        produit.setRating(rating);

                        // insert data into ArrayList result
                        result.add(produit);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req); // execution ta3 request sinon yet3ada chy dima nal9awha

    return result;
}


 
}





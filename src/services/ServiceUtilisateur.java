/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import gui.HomeForm;
import gui.LoginForm;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import util.SessionManager;

/**
 *
 * @author achre
 */
public class ServiceUtilisateur {
      public static ServiceUtilisateur instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceUtilisateur getInstance() {
        if(instance == null )
            instance = new ServiceUtilisateur();
        return instance ;
    }
    
    
    
    public ServiceUtilisateur() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
    public void signup(TextField username,TextField password,TextField email ) {

        try {
        req.setPost(false);
        req.setUrl("http://127.0.0.1:8000/registerMob");
        req.addArgument("username", username.getText());
        req.addArgument("email", email.getText());
        req.addArgument("password", password.getText());
        }
        catch (Exception ee){
        System.out.println(ee.getMessage());
        }
        //Control saisi
        if(username.getText().equals("") && password.getText().equals("") && email.getText().equals("")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        System.out.println("req");
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
    
    
    //SignIn
    
    public void signin(String username,String password) { 
        try {
        req.setPost(false);
        req.setUrl("http://127.0.0.1:8000/LoginUser");
        req.addArgument("username",username);
        req.addArgument("password", password);
        }
        catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("A");
        }
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            

            try {
                Map<String,Object> Response = j.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData())));
                
                String ErrorMessage = Response.get("Error").toString();
                if (!ErrorMessage.equals("")) {
                    Dialog.show("Authentification failed.",ErrorMessage,"OK",null);
                }
                else {
                    Map<String,Object> user= (Map<String,Object>) Response.get("User");   
                    //Session 
                    double id = (double) user.get("id");
                    SessionManager.setId(id);
                    SessionManager.setPassowrd((String)user.get("password").toString());
                    SessionManager.setUserName((String)user.get("username").toString());
                    SessionManager.setEmail((String)user.get("email").toString());
                    SessionManager.setPhonenumber((String)user.get("numTel"));
                new HomeForm().show();
                   
                   
             
                }   
            }catch(Exception ex) {
                System.out.println(ex.getMessage());
            }     
            
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void ModifyUser(String username,String email,String phonenumber ){
            
                try {
        req.setPost(false);
        req.setUrl("http://127.0.0.1:8000/modifyUser");
        req.addArgument("originalname",SessionManager.getUserName());
        req.addArgument("originalemail",SessionManager.getEmail());
        req.addArgument("username",username);
        req.addArgument("email",email);
        req.addArgument("PhoneNumber",phonenumber);
        }
        catch (Exception e) {
        System.out.println(e.getMessage());
        }
        req.addResponseListener((e) ->{
        JSONParser j = new JSONParser();
        try {
            Map<String,Object> Response = j.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData())));
            String ErrorMessage=(String)Response.get("Error");
            if (!ErrorMessage.equals("")) {
                Dialog.show("Request failed.",ErrorMessage,"OK",null);
                }
            else {
                String succ=(String)Response.get("Response");
                Dialog.show("Success.",succ,"OK",null);
            }
        }catch(Exception exx)
        {
        System.out.println(exx.getMessage());
        }
        
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import entities.Weather;
import java.util.Map;

/**
 *
 * @author omare
 */
public class WeatherService {
    ConnectionRequest req;
    static WeatherService instance = null;
    
    boolean resultOK = false;
    
    private WeatherService(){
        req = new ConnectionRequest();
    }
    
    public static WeatherService getInstance(){
        if(instance == null){
            instance = new WeatherService();
        }
        return instance;
    }
    
    
}

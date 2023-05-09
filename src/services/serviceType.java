package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import entities.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class serviceType {
    
    private ConnectionRequest req;
    
    public static serviceType getInstance() {
        return new serviceType();
    }
    public serviceType() {
    req=new ConnectionRequest();
    }
    
    public ArrayList<Type> affichageTypes() {
        ArrayList<Type> result = new ArrayList<>(); 
        req.setPost(false);
        req.setUrl("http://127.0.0.1:8000/mobile/type-abonnements");
        req.addResponseListener((e) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> mapTypes = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapTypes.get("root");
                for (Map<String, Object> obj : listOfMaps) {
                    Type type = new Type();
                    float id = Float.parseFloat(obj.get("id").toString());
                    float prix = Float.parseFloat(obj.get("price").toString());
                    String description = obj.get("description").toString();
                    type.setId((float) id);
                    type.setDescription(description);
                    type.setPrice(prix);
                    result.add(type);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    }
}

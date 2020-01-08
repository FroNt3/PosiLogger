package org.patrick;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ApiReader {
    
    /**
     * Contacts the server to often, use getOnlinePlayers()
     */
    @Deprecated
    public static boolean isOnline(String name) throws JSONException, IOException {
        
        JSONObject json = JsonReader.readJsonFromUrl("https://api.realliferpg.de/v1/servers");            
        JSONArray jsonArrayData = json.getJSONArray("data");                
        JSONArray jsonArrayPlayers = jsonArrayData.getJSONObject(0).getJSONArray("Players"); 
        
        for (Object player : jsonArrayPlayers) {
            if (player.toString().toLowerCase().contains(name.toLowerCase())) {
                return true;
            }
        }  
        
        return false;
    }
    
    public static ArrayList<String> getOnlinePlayers() throws JSONException, IOException {
        ArrayList<String> stringList = new ArrayList<String>();
        
        JSONObject json = JsonReader.readJsonFromUrl("https://api.realliferpg.de/v1/servers");            
        JSONArray jsonArrayData = json.getJSONArray("data");                
        JSONArray jsonArrayPlayers = jsonArrayData.getJSONObject(0).getJSONArray("Players"); 
        
        for (Object player : jsonArrayPlayers) {
            stringList.add(player.toString());
        } 
        
        return stringList;
    }
    
    public static String getPosition(String name) throws JSONException, IOException {
        String url = "https://api.realliferpg.de/v1/player/";
        
        if (Keys.getKeyJohn()[0].toLowerCase().contains(name.toLowerCase())) {
            url = url + Keys.getKeyJohn()[1];
        } else if (Keys.getKeyPaul()[0].toLowerCase().contains(name.toLowerCase())) {
            url = url + Keys.getKeyPaul()[1];
        } else if (Keys.getKeyMakarov()[0].toLowerCase().contains(name.toLowerCase())) {
            url = url + Keys.getKeyMakarov()[1];
        } else if (Keys.getKeyVladimir()[0].toLowerCase().contains(name.toLowerCase())) {
            url = url + Keys.getKeyVladimir()[1];
        } else if (Keys.getKeyHans()[0].toLowerCase().contains(name.toLowerCase())) {
            url = url + Keys.getKeyHans()[1];
        } 
        
        JSONObject json = JsonReader.readJsonFromUrl(url);
        JSONArray jsonArrayData = json.getJSONArray("data");
        String pos = jsonArrayData.getJSONObject(0).getString("pos");
        
        return pos;
    }
    
    private ApiReader() {        
    }

}

package org.patrick;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ApiReader {
    
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
    
    public static String getPosition(String key) throws JSONException, IOException {
        String url = "https://api.realliferpg.de/v1/player/" + key;
        
        JSONObject json = JsonReader.readJsonFromUrl(url);
        JSONArray jsonArrayData = json.getJSONArray("data");
        String pos = jsonArrayData.getJSONObject(0).getString("pos");
        
        return pos;
    }
    
    private ApiReader() {        
    }

}

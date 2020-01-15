package org.patrick;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.json.JSONException;

public class Stalker {

    ArrayList<Person> personList;
    
    public Stalker(ArrayList<Person> personList) {
        this.personList = personList;
    }
    
    public void updatePosi() throws JSONException, IOException {
        for (Person person : personList) {
            String tmpPosi = ApiReader.getPosition(person.getKey());
            person.setPosi(tmpPosi);
        }
    }
    
    public void updateStatus() throws JSONException, IOException {
        ArrayList<String> playerList = ApiReader.getOnlinePlayers();
        for (String player : playerList) {
            for (Person person : personList) {
                if (player.contains(person.getName())) {
                    person.setStatus(true);
                }
            } 
        }
    }
    
    public String writePosi() throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String currentTime = dtf.format(now);
        
        String log = currentTime + " Updated for:";
        
        for (Person person : personList) {
            if (person.getStatus()) {
                PosiFile.append(person.getFilename(), currentTime, person.getPosi());
                person.setStatus(false);
                log = log + " " + person.getName();
            }
        }
        
        return log;
    }
}

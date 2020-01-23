package org.patrick;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.json.JSONException;

public class Stalker {

    ArrayList<Person> personList;
    
    /**
     * Stalker objects default to all info available
     */
    public Stalker() {
        ArrayList<String[]> infoList = PrivateInfo.getAllInfo();
        ArrayList<Person> personList = new ArrayList<Person>();        
        for (String[] info : infoList) {
            personList.add(new Person(info));
        }
        this.personList = personList;
    }
    
    /**
     * If you only want a set of persons you can provide a list of them yourself
     * 
     * @param personList List of persons to stalk
     */
    public Stalker(ArrayList<Person> personList) {
        this.personList = personList;
    }
    
    public void updateStatus() throws CustomException {
        ArrayList<String> playerList;
        try {
            playerList = ApiReader.getOnlinePlayers();
        } catch (JSONException | IOException e) {            
            e.printStackTrace();
            throw new CustomException("Error while trying to update online players.");
        }
        for (String player : playerList) {
            for (Person person : personList) {
                if (player.contains(person.getName())) {
                    person.setStatus(true);
                }
            } 
        }
    }
    
    public void updatePosi() throws CustomException {
        for (Person person : personList) {
            if (person.getStatus()) {
                String tmpPosi;
                try {
                    tmpPosi = ApiReader.getPosition(person.getKey());
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                    throw new CustomException("Error while trying to update position of " + person.getName());
                }
                person.setPosi(tmpPosi);
            }
        }
    }
    
    public String writePosi() throws CustomException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String currentTime = dtf.format(now);
        
        String log = currentTime + " Updated for:";
        
        for (Person person : personList) {
            if (person.getStatus()) {
                try {
                    PosiFile.append(person.getFilename(), currentTime, person.getPosi());
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new CustomException("Error while trying to write to file: " + person.getFilename());
                }
                person.setStatus(false);
                log = log + " " + person.getName();
            }
        }
        
        return log;
    }
}

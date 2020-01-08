package org.patrick;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.json.JSONException;

public class Main {
    
    public static void main(String[] args) {
        
        System.out.println("Starting PosiLogger...");
        
        String posiJohn = "";
        String posiPaul = "";
        String posiMakarov = "";
        String posiVladimir = "";
        String posiHans = "";
        String workDone = "";
        
        boolean statusJohn = false;
        boolean statusPaul = false;
        boolean statusMakarov = false;
        boolean statusVladimir = false;
        boolean statusHans = false;
        
        System.out.println("PosiLogger online");
       
        while (true) {           
            try {
                posiJohn = ApiReader.getPosition(Keys.getKeyJohn()[0]);
                posiPaul = ApiReader.getPosition(Keys.getKeyPaul()[0]);
                posiMakarov = ApiReader.getPosition(Keys.getKeyMakarov()[0]);
                posiVladimir = ApiReader.getPosition(Keys.getKeyVladimir()[0]);
                posiHans = ApiReader.getPosition(Keys.getKeyHans()[0]);
            } catch (JSONException | IOException e) {
                System.out.println("Failed getting pos");
                e.printStackTrace();
            }
           
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            String currentTime = dtf.format(now);
            
            try {
                ArrayList<String> stringList = ApiReader.getOnlinePlayers();
                for (String player : stringList) {
                    if (player.contains(Keys.getKeyJohn()[0])) {
                        statusJohn = true;
                    } else if (player.contains(Keys.getKeyPaul()[0])) {
                        statusPaul = true;
                    } else if (player.contains(Keys.getKeyMakarov()[0])) {
                        statusMakarov = true;
                    } else if (player.contains(Keys.getKeyVladimir()[0])) {
                        statusVladimir = true;
                    } else if (player.contains(Keys.getKeyHans()[0])) {
                        statusHans = true;
                    }
                }
            } catch (JSONException | IOException e) {
                System.out.println("Failed getting online status at " + currentTime);
                e.printStackTrace();
            }    
            
            workDone = currentTime + " Updated for:";
            
            if (statusJohn) {
                try {
                    PosiFile.append("posiJohn", currentTime, posiJohn);
                    workDone = workDone + " John";
                } catch (IOException e) {
                    System.out.println("Failed writing for John");
                    e.printStackTrace();
                } 
            }
            
            if (statusPaul) {
                try {
                    PosiFile.append("posiPaul", currentTime, posiPaul);
                    workDone = workDone + " Paul";
                } catch (IOException e) {
                    System.out.println("Failed writing for Paul");
                    e.printStackTrace();
                } 
            }
            
            if (statusMakarov) {
                try {
                    PosiFile.append("posiMakarov", currentTime, posiMakarov);
                    workDone = workDone + " Makarov";
                } catch (IOException e) {
                    System.out.println("Failed writing for Makarov");
                    e.printStackTrace();
                } 
            }
            
            if (statusVladimir) {
                try {
                    PosiFile.append("posiVladimir", currentTime, posiVladimir);
                    workDone = workDone + " Vladimir";
                } catch (IOException e) {
                    System.out.println("Failed writing for Vladimir");
                    e.printStackTrace();
                } 
            }
            
            if (statusHans) {
                try {
                    PosiFile.append("posiHans", currentTime, posiHans);
                    workDone = workDone + " Hans";
                } catch (IOException e) {
                    System.out.println("Failed writing for Hans");
                    e.printStackTrace();
                } 
            }
            
            System.out.println(workDone);
            
            try {
                Thread.sleep(60*1000);
            } catch (InterruptedException e) {
                System.out.println("Failed sleeping");
                e.printStackTrace();
            }
        }      

    }

}

package org.patrick;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;

public class Main {

    public static void main(String[] args) {
        String posiJohn = "";
        String posiPaul = "";
        String posiMakarov = "";
        String posiVladimir = "";
        String posiHans = "";
        
        boolean statusJohn = false;
        boolean statusPaul = false;
        boolean statusMakarov = false;
        boolean statusVladimir = false;
        boolean statusHans = false;
       
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
                statusJohn = ApiReader.isOnline(Keys.getKeyJohn()[0]);
                statusPaul = ApiReader.isOnline(Keys.getKeyPaul()[0]);
                statusMakarov = ApiReader.isOnline(Keys.getKeyMakarov()[0]);
                statusVladimir = ApiReader.isOnline(Keys.getKeyVladimir()[0]);
                statusHans = ApiReader.isOnline(Keys.getKeyHans()[0]);
            } catch (JSONException | IOException e) {
                System.out.println("Failed getting online status");
                e.printStackTrace();
            }    
            
            if (statusJohn) {
                try {
                    PosiFile.append("posiJohn", currentTime, posiJohn);
                } catch (IOException e) {
                    System.out.println("Failed writing for John");
                    e.printStackTrace();
                } 
            }
            
            if (statusPaul) {
                try {
                    PosiFile.append("posiPaul", currentTime, posiPaul);
                } catch (IOException e) {
                    System.out.println("Failed writing for Paul");
                    e.printStackTrace();
                } 
            }
            
            if (statusMakarov) {
                try {
                    PosiFile.append("posiMakarov", currentTime, posiMakarov);
                } catch (IOException e) {
                    System.out.println("Failed writing for Makarov");
                    e.printStackTrace();
                } 
            }
            
            if (statusVladimir) {
                try {
                    PosiFile.append("posiVladimir", currentTime, posiVladimir);
                } catch (IOException e) {
                    System.out.println("Failed writing for Vladimir");
                    e.printStackTrace();
                } 
            }
            
            if (statusHans) {
                try {
                    PosiFile.append("posiHans", currentTime, posiHans);
                } catch (IOException e) {
                    System.out.println("Failed writing for Hans");
                    e.printStackTrace();
                } 
            }
            
            try {
                Thread.sleep(60*1000);
            } catch (InterruptedException e) {
                System.out.println("Failed sleeping");
                e.printStackTrace();
            }
        }      

    }

}

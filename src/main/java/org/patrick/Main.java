package org.patrick;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

public class Main {
    
    public static void main(String[] args) {
        
        System.out.println("Starting PosiLogger...");
        
        String log = "";
        
        ArrayList<String[]> infoList = PrivateInfo.getAllInfo();
        ArrayList<Person> personList = new ArrayList<Person>();
        
        for (String[] info : infoList) {
            personList.add(new Person(info));
        }       
        
        Stalker stalker = new Stalker(personList);
        
        System.out.println("PosiLogger online");
       
        while (true) {           
            try {                
                stalker.updatePosi();
            } catch (JSONException | IOException e) {
                System.out.println("Failed getting pos");
                e.printStackTrace();
            }
            
            try {
                stalker.updateStatus();
            } catch (JSONException | IOException e) {
                System.out.println("Failed getting online status");
                e.printStackTrace();
            }    
            
            try {
                log = stalker.writePosi();
            } catch (IOException e) {
                System.out.println("Failed writing to file");
                e.printStackTrace();
            }
            
            System.out.println(log);
            
            try {
                Thread.sleep(59*1000); //59 seconds since the code takes >1 second
            } catch (InterruptedException e) {
                System.out.println("Failed sleeping");
                e.printStackTrace();
            }
        }      

    }

}

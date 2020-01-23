package org.patrick;

/**
 * @author Patrick
 *
 */
public class Main {
    
    public static void main(String[] args) {
        
        System.out.println("Starting PosiLogger...");
        
        String log = "";       
        Stalker stalker = new Stalker();
        
        System.out.println("PosiLogger online");
       
        while (true) {           
            try {
                stalker.updateStatus();
                stalker.updatePosi();
                log = stalker.writePosi();
                System.out.println(log);
            } catch (CustomException error) {
                System.out.println(error.getMessage());
            }           
            
            try {
                Thread.sleep(59 * 1000); //59 seconds since the code takes >1 second
            } catch (InterruptedException e) {
                System.out.println("Failed sleeping");
                e.printStackTrace();
            }
        }      

    }

}

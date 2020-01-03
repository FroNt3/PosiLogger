package org.patrick;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public final class PosiFile {
    
    public static void append(String fileName, String time, String posi) throws IOException {
        String path = fileName + ".txt";
        FileWriter fw = new FileWriter(path, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        out.println(time + ": " + posi);
        out.close();
    }
    
    private PosiFile() {        
    }
}

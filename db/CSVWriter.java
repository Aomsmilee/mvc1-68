package db;

import java.io.*;

public class CSVWriter {
    public static void appendLine(String filePath, String line){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true))){
            bw.write(line);
            bw.newLine();
        }catch(Exception e){ e.printStackTrace(); }
    }
}

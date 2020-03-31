package es.ulpgc.as.pablopf.chatting.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Error {
    private String message;
    
    public Error() {
    }

    public Error saveError(String message)
    {
        this.message = message;
        File errorFile = new File("C:\\Users\\wwwam\\Documents\\NetBeansProjects\\AS_ChatTing\\resources\\ErrorLog.txt");
        
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = dateTimeFormatter.format(localDateTime);
        
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(errorFile, true));
            bw.write(message +" at " + date + "\n");
            bw.close();
        }catch(IOException e){
            System.out.println(ConsoleColors.BLACK + e);
        } 
        
        return this;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.ConsoleColors;
import models.Message;

public class MessageHandler extends DatabaseConnHandler{
        
    public ArrayList<Message> getAllMessages(String nameChat){
        ArrayList<Message> messages = new ArrayList<Message>();
        DatabaseConnHandler db = new DatabaseConnHandler();
        try{
            PreparedStatement pst = db.getConnection().prepareStatement("SELECT id, name, message, DATE_FORMAT(created_at, 'at %h:%i:%s %p') as date FROM " + nameChat + " ORDER BY created_at DESC LIMIT 100");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                messages.add(new Message(rs.getString("name"), rs.getString("message"), rs.getString("date")));
            }
            
            db.getConnection().close();
            rs.close();
            pst.close();
            
            System.out.println(ConsoleColors.PURPLE + "---> Messages obtained from database of chat:: " + nameChat);
        }catch(SQLException e){
            System.out.println(ConsoleColors.RED + e.getMessage());
        }
        return messages;
    }
    
    public boolean create(Message message, String nameChat){
        boolean flag = false;
        
        try{
            PreparedStatement pst = getConnection().prepareStatement("INSERT INTO " + nameChat + " (name, message, created_at) VALUES(?,?,?)");
            
            pst.setString(1, message.getUser());
            pst.setString(2, message.getContent());
            pst.setString(3, message.getCreated_at());
            
            flag = pst.executeUpdate() == 1;
            
            getConnection().close();
            pst.close();
            System.out.println(ConsoleColors.BLACK + "----->  Message created by user::" + message.getUser() + "  message::" + message.getContent() + "  date::" + message.getCreated_at() + " in chat:: " + nameChat);
        }catch(SQLException e){
             System.out.println(ConsoleColors.RED + e.getMessage());
        }
        
        return flag;
    }
}

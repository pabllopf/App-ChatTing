package ejbs.stateless;

import ejbs.singleton.LogRemote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import models.Chat;
import models.ConsoleColors;
import models.LogMessage;
import models.Message;

@Stateless
public class ChatHandler implements ChatHandlerRemote {

    @PostConstruct
    @Override
    public void init(){
        System.out.println("ChatHandler::init - @PostConstruct Stateless");
    }
    
    @Override
    public boolean existsChat(String nameChat) {
        System.out.println("ChatHandler::existsChat::'" + nameChat + "' - @Override Stateless");
        
        /*try(Connection connection = new Database().getConnection()){
            return connection.getMetaData().getTables(null, null, nameChat, null).next();
        }catch(SQLException e){
             System.out.println(ConsoleColors.RED + e.getMessage());
        }
        */
        return false;
    }
    
    @Override
    public boolean createChat(String nameChat) {
        System.out.println("ChatHandler::createChat::'" + nameChat + "' - @Override Stateless");
        
        /*String sql = "CREATE TABLE " + nameChat + " (id int(11) NOT NULL AUTO_INCREMENT, name VARCHAR(25) NOT NULL, message VARCHAR(1000) NOT NULL, created_at TIMESTAMP NOT NULL, PRIMARY KEY (id))";
        try(PreparedStatement pst = new Database().getConnection().prepareStatement(sql)){
            pst.executeUpdate();
            return true;
        }catch(SQLException e){
             System.out.println(ConsoleColors.RED + e.getMessage());
        }
        */
        return false;
    }
    
    @Override
    public Chat loadChat(String nameChat){
        System.out.println("ChatHandler::loadChat::'" + nameChat + "' - @Override Stateless");
        
        
        /*String sql = "SELECT id, name, message, DATE_FORMAT(created_at, 'at %h:%i:%s %p') as date FROM " + nameChat + " ORDER BY created_at DESC LIMIT 100";
        ArrayList<Message> messages = new ArrayList<Message>();
        
        try(PreparedStatement pst = new Database().getConnection().prepareStatement(sql); ResultSet rs = pst.executeQuery()){
            while(rs.next()){
                messages.add(new Message(rs.getString("id"), rs.getString("name"), rs.getString("message"), rs.getString("date")));
            }
        }catch(SQLException e){
             System.out.println(ConsoleColors.RED + e.getMessage());
        }
        */
        return null;
    }
    
    @Override
    public boolean sendMessageTo(String nameChat, Message message){
        System.out.println("ChatHandler::sendMessageTo::'" + nameChat + "' by "+ message.getUser() + " content " + message.getContent() + " at " + message.getCreated_at() + " - @Override Stateless");
        /*String sql = "INSERT INTO " + nameChat + " (name, message, created_at) VALUES(?,?,?)";
        
        
        try(PreparedStatement pst = new Database().getConnection().prepareStatement(sql)){
            pst.setString(1, message.getUser());
            pst.setString(2, message.getContent());
            pst.setString(3, message.getCreated_at());
            return pst.executeUpdate() == 1;
        }catch(SQLException e){
             System.out.println(ConsoleColors.RED + e.getMessage());
        }
        */
        return false;
    }
    
    @Override
    public boolean exitsMessage(Message message, Chat chat){
         System.out.println("ChatHandler::exitsMessage::'" + chat.getName() + "' - @Override Stateless");
         
         /*Chat chatDatabase = loadChat(chat.getName());
         
         for(Message i : chatDatabase.getMessages()){
             if(i == message){
                 return true;
             }
         }*/
        return false;
    }
    
    @Override
    public boolean deleteMessage(Message message, Chat chat){
        System.out.println("ChatHandler::deleteMessage::message:: '" + message.getContent() + "' - @Override Stateless");
        
        /*String sql = "DELETE FROM " + chat.getName() + " WHERE id = ?";
        
        try(PreparedStatement pst = new Database().getConnection().prepareStatement(sql)){
            pst.setString(1, message.getId());
            return pst.executeUpdate() == 1;
        }catch(SQLException e){
             System.out.println(ConsoleColors.RED + e.getMessage());
        }
        */
        return false;
    }
    
    @PreDestroy
    @Override
    public void destroy() {
        
        System.out.println("ChatHandler::destroy - @PreDestroy Stateless");
    }
}

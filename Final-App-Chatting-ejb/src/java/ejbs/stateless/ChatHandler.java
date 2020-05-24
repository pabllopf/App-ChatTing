package ejbs.stateless;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import models.Chat;
import models.ConsoleColors;
import models.Message;

@Stateless
public class ChatHandler{
    
    private Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:derby://localhost:1527/chat;create=true", "app", "app");
        }catch(SQLException e){
            System.err.println(e);
        }
        return null;        
    }

    @PostConstruct
    public void init(){
        System.out.println("ChatHandler::init - @PostConstruct Stateless");
    }
    
    public boolean existsChat(String nameChat) {
        nameChat = nameChat.toUpperCase();
        System.out.println("ChatHandler::existsChat::'" + nameChat + "' - @Override Stateless");
        
        try(Connection connection = getConnection()){
            return connection.getMetaData().getTables(null, null, nameChat, null).next();
        }catch(SQLException e){
             System.out.println(ConsoleColors.RED + e.getMessage());
        }
        
        return false;
    }
    
    public boolean createChat(String nameChat) throws ClassNotFoundException, SQLException {
        System.out.println("ChatHandler::createChat::'" + nameChat + "' - @Override Stateless");
        
        String sql = ""
                + "CREATE TABLE " + nameChat + " (" 
                + "Id INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                + "name VARCHAR(255) NOT NULL,"
                + "message VARCHAR(255) NOT NULL,"
                + "created_at TIMESTAMP NOT NULL,"
                + "PRIMARY KEY (Id)"
                + ")";
        
        try(PreparedStatement pst = getConnection().prepareStatement(sql)){
            pst.executeUpdate();
            return true;
        }catch(SQLException e){
             System.out.println(ConsoleColors.RED + e.getMessage());
        }
        
        return true;
    }
    
    
    public Chat loadChat(String nameChat){
        System.out.println("ChatHandler::loadChat::'" + nameChat + "' - @Override Stateless");

        String sql = "SELECT id, name, message, created_at FROM " + nameChat + " ORDER BY created_at DESC";
        
        ArrayList<Message> messages = new ArrayList<>();
        
        try(PreparedStatement pst = getConnection().prepareStatement(sql); ResultSet rs = pst.executeQuery()){
            while(rs.next()){
                messages.add(new Message(rs.getString("id"), rs.getString("name"), rs.getString("message"), rs.getTime("created_at").toString()));
                System.out.println("ChatHandler::message::'" + rs.getString("id") + rs.getString("name") + rs.getString("message") + rs.getTime("created_at").toString());
            }
        }catch(SQLException e){
            System.out.println(ConsoleColors.RED + e.getMessage());
        }
        
        return new Chat(nameChat, messages);
    }
    
    public Chat loadChat(String nameChat, int numPage){
        System.out.println("ChatHandler::loadChat::'" + nameChat + "' - @Override Stateless");

        String sql = "SELECT id, name, message, created_at FROM " + nameChat + " ORDER BY created_at DESC";
        
        ArrayList<Message> messages = new ArrayList<>();
        
        try(PreparedStatement pst = getConnection().prepareStatement(sql); ResultSet rs = pst.executeQuery()){
            while(rs.next()){
                messages.add(new Message(rs.getString("id"), rs.getString("name"), rs.getString("message"), rs.getTime("created_at").toString()));
                System.out.println("ChatHandler::message::'" + rs.getString("id") + rs.getString("name") + rs.getString("message") + rs.getTime("created_at").toString());
            }
        }catch(SQLException e){
            System.out.println(ConsoleColors.RED + e.getMessage());
        }
       
        return new Chat(nameChat, messages);
    }
    
    
    
    
    public boolean sendMessageTo(String nameChat, Message message){
        System.out.println("ChatHandler::sendMessageTo::'" + nameChat + "' by "+ message.getUser() + " content " + message.getContent() + " at " + message.getCreated_at() + " - @Override Stateless");
        
        String sql = "INSERT INTO " + nameChat + " (name, message, created_at) VALUES(?,?,?)";
        
        try(PreparedStatement pst = getConnection().prepareStatement(sql)){
            pst.setString(1, message.getUser());
            pst.setString(2, message.getContent());
            pst.setString(3, message.getCreated_at());
            return pst.executeUpdate() == 1;
        }catch(SQLException e){
             System.out.println(ConsoleColors.RED + e.getMessage());
        }
        
        return false;
    }
    
    public boolean exitsMessage(Message message, Chat chat){
        System.out.println("ChatHandler::exitsMessage::'" + chat.getName() + "' - @Override Stateless");         
        return loadChat(chat.getName()).getMessages().stream().anyMatch((i) -> (i == message));
    }
    
    public boolean deleteMessage(Message message, Chat chat){
        System.out.println("ChatHandler::deleteMessage::message:: '" + message.getContent() + "' - @Override Stateless");
        
        String sql = "DELETE FROM " + chat.getName() + " WHERE id = ?";
        
        try(PreparedStatement pst = getConnection().prepareStatement(sql)){
            pst.setString(1, message.getId());
            return pst.executeUpdate() == 1;
        }catch(SQLException e){
             System.out.println(ConsoleColors.RED + e.getMessage());
        }
        return false;
    }
    
    @PreDestroy
    public void destroy() {
        System.out.println("ChatHandler::destroy - @PreDestroy Stateless");
    }
}

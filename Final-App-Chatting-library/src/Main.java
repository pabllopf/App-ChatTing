import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import models.ConsoleColors;
import models.Message;

/**
 *
 * @author amit
 */
public class Main {

    public Main() {
    }

    private static Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:derby://localhost:1527/chat;create=true", "app", "app");
        }catch(SQLException e){
            System.err.println(e);
        }
        return null;        
    }
    
    public static void main(String[] args) {
        String nameChat = "PUBLICROOM";
        Message message = new Message("paco", "jajajaj", "2020-05-23 11:17:49.222");
        
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
        }catch(SQLException e){
             System.out.println(ConsoleColors.RED + e.getMessage());
        }
        
    }

}
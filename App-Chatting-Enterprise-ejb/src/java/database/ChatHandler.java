package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.ConsoleColors;

public class ChatHandler extends DatabaseConnHandler{
    
    public boolean exists(String nameChat){
        Connection connection = getConnection();
        
        try{
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, nameChat, null);
            
            System.out.println(ConsoleColors.BLUE + "----> Check if chat exists: (" + nameChat + ")");
            return tables.next();
        }catch(SQLException e){
             System.out.println(ConsoleColors.RED + e.getMessage());
        }
        return false;
    }
    
    
    public boolean createNewChat(String nameChat){
        boolean flag = false;
        
        try{
            PreparedStatement pst = getConnection().prepareStatement(
                    "CREATE TABLE "+ nameChat + " ("
                    + "id int(11) NOT NULL AUTO_INCREMENT,"
                    + "name VARCHAR(25) NOT NULL,"
                    + "message VARCHAR(1000) NOT NULL,"
                    + "created_at TIMESTAMP NOT NULL,"
                    + "PRIMARY KEY (id))");

            flag = pst.executeUpdate() == 1;
            
            getConnection().close();
            pst.close();
            System.out.println(ConsoleColors.BLUE + "----> Chat created: (" + nameChat + ")");
        }catch(SQLException e){
             System.out.println(ConsoleColors.RED + e.getMessage());
        }
        
        return flag;
    }
}

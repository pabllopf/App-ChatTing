package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import models.ConsoleColors;

public class DatabaseConnHandler {
    private String className = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/chat?autoReconnect=true&useSSL=false";
    private Connection connection;

    public DatabaseConnHandler(){
        try{
            Class.forName(className);
            this.connection = DriverManager.getConnection(url, "root", "");
            
            if(connection != null){
                System.out.println(ConsoleColors.GREEN + "-> Connection established with the database at: " + "jdbc:mysql://localhost:3306/chat");
            }
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(ConsoleColors.RED + e.getMessage());
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
}

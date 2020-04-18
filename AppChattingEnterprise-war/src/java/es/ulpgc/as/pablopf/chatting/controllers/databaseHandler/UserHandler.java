package es.ulpgc.as.pablopf.chatting.controllers.databaseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import es.ulpgc.as.pablopf.chatting.models.ConsoleColors;
import es.ulpgc.as.pablopf.chatting.models.User;

public class UserHandler  extends DatabaseConnHandler{
    
    public boolean exists(String user){
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection connection = getConnection();
        String sqlStatement = "SELECT count(id) FROM users WHERE user = ?";
        boolean result = true;
        
        try{
            preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, user);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                result = resultSet.getBoolean(1);
            }
            
            connection.close();
            resultSet.close();
            preparedStatement.close();
            System.out.println(ConsoleColors.CYAN + "--> User checked in the database correctly");            
            
            return result;
            
        }catch(SQLException e){
            System.out.println(ConsoleColors.RED + e.getMessage());
        }
        
        return result;
    }
    
    public boolean login(User account){
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection connection = getConnection();
        String sqlStatement = "SELECT * FROM users WHERE user = ? and password = ?";
        boolean result = false;
        
        try{
            preparedStatement = connection.prepareStatement(sqlStatement);
            
            preparedStatement.setString(1, account.getUser());
            preparedStatement.setString(2, account.getPassword());
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                account.setUser(resultSet.getString("user"));
                account.setPassword(resultSet.getString("password"));
                result = true;
            }
            
            connection.close();
            resultSet.close();
            preparedStatement.close();
            
            if(result){
                System.out.println(ConsoleColors.CYAN + "--> User logged (" + account.getUser() +") in the database correctly"); 
            }else{
                System.out.println(ConsoleColors.CYAN + "--> User try to login (" + account.getUser() +") in the database"); 
            }
            
            return result;
            
        }catch(SQLException e){
            System.out.println(ConsoleColors.RED + e.getMessage());
        }

        return result;
    }
    
    public boolean createUser(User account){
            PreparedStatement preparedStatement;
            Connection connection = getConnection();
            String sqlStatement = "INSERT INTO users (user, password) VALUES(?,?)";
            boolean flag = false;
        
        try{
            preparedStatement = connection.prepareStatement(sqlStatement);
            
            preparedStatement.setString(1, account.getUser());
            preparedStatement.setString(2, account.getPassword());
            
            flag = preparedStatement.executeUpdate() == 1;
            
            connection.close();
            preparedStatement.close();
            
            System.out.println(ConsoleColors.CYAN + "--> User created    user:" + account.getUser());
        }catch(SQLException e){
             System.out.println(ConsoleColors.RED + e.getMessage());
        }
        
        return flag;
    }
}

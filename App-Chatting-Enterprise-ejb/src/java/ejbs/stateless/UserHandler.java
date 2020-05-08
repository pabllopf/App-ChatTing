package ejbs.stateless;

import database.Database;
import ejbs.singleton.LogRemote;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import models.ConsoleColors;
import models.LogMessage;
import models.User;

@Stateless
public class UserHandler implements UserHandlerRemote {
    @PostConstruct
    @Override
    public void init(){
        System.out.println("UserHandler::init - @PostConstruct Stateless");
        

    }
    
    @Override
    public boolean login(User account) {
        System.out.println("UserHandler::login::'" + account.getUser() + "' - @Override Stateless");
        String sql = "SELECT * FROM users WHERE user = " + account.getUser() + " and password = " + account.getPassword() + "";
        
        try(PreparedStatement pst = new Database().getConnection().prepareStatement(sql); ResultSet rs = pst.executeQuery()){
            while(rs.next()){
                return (rs.getString("user").equals(account.getUser()) && rs.getString("password").equals(account.getPassword()));
            }
        }catch(SQLException e){
             System.out.println(ConsoleColors.RED + e.getMessage());
        }
        
        
        return true;
    }

    @Override
    public boolean signUp(User account) {
        System.out.println("UserHandler::signUp::'" + account.getUser() + "' - @Override Stateless");
        
        String sql = "INSERT INTO users (user, password) VALUES(?,?)";
        
        try(PreparedStatement pst = new Database().getConnection().prepareStatement(sql)){
            pst.setString(1, account.getUser());
            pst.setString(2, account.getPassword());
            return pst.executeUpdate() == 1;
        }catch(SQLException e){
             System.out.println(ConsoleColors.RED + e.getMessage());
        }
       
        return false;
    }

    @Override
    public boolean exists(User account) {
        System.out.println("UserHandler::exists::'" + account.getUser() + "' - @Override Stateless");
        
        
        String sql = "SELECT count(id) FROM users WHERE user = " + account.getUser() + "";
        try(PreparedStatement pst = new Database().getConnection().prepareStatement(sql); ResultSet rs = pst.executeQuery()){
           return rs.getBoolean(1);
        }catch(SQLException e){
            System.out.println(ConsoleColors.RED + e.getMessage());
        }
        
        return false;
    }

    @PreDestroy
    @Override
    public void destroy() {
        System.out.println("UserHandler::destroy - @PreDestroy Stateless");
        
    }
}

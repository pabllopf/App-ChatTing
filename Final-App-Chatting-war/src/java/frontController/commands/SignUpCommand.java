package frontController.commands;

import ejbs.singleton.StatisticsRemote;
import ejbs.stateless.controllers.TableUsersFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Error;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import models.LogMessage;
import models.User;

public class SignUpCommand extends AbstractCommand{
    @Override
    public void process() {
        logRemote.add(new LogMessage("SignUpCommand::process"));
        
        String userParam = request.getParameter("userText");
        String passwordParam = request.getParameter("passwordText");
        String passwordRepeatParam = request.getParameter("passwordrepeatText");
        
        Pattern pattern = Pattern.compile("[A-Z\\s\\d\\W]+.*");
        Matcher matcher= pattern.matcher(userParam);
        
        if(userParam.length() <= 0 || passwordParam.length() <= 0 || passwordRepeatParam.length() <= 0){
            request.getSession().setAttribute("Error", new Error().saveError("Empty user or password are not supported."));
            forward("/SignUp.jsp");
            return;
        }
        
        if(matcher.find()){
            request.getSession().setAttribute("Error", new Error().saveError("Incorrect user: enter only lowercase letters without spaces."));
            forward("/SignUp.jsp"); 
            return;
        }
        
        if(!passwordParam.equals(passwordRepeatParam)){
            request.getSession().setAttribute("Error", new Error().saveError("Introduce the same password."));
            forward("/SignUp.jsp");
            return;
        }
        
        User newUser = new User(userParam, passwordParam);
        
        if(userHandler.exists(newUser)){
            request.getSession().setAttribute("Error", new Error().saveError("User already exists."));
            forward("/SignUp.jsp");
            return;
        }
        
        logRemote.add(new LogMessage("SignUp::" + newUser.getUser()));
        
        userHandler.signUp(newUser);
        statistics.countNewsSingUp();
        forward("/Login.jsp");
    }
}

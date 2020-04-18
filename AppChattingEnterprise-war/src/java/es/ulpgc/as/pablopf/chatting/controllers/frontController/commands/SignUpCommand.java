package es.ulpgc.as.pablopf.chatting.controllers.frontController.commands;

import es.ulpgc.as.pablopf.chatting.controllers.frontController.AbstractCommand;
import es.ulpgc.as.pablopf.chatting.models.User;
import es.ulpgc.as.pablopf.chatting.models.Error;
import es.ulpgc.as.pablopf.chatting.controllers.databaseHandler.UserHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpCommand extends AbstractCommand{
    @Override
    public void process() {
        UserHandler userModel = new UserHandler();

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

        if(userModel.exists(userParam)){
            request.getSession().setAttribute("Error", new Error().saveError("User already exists."));
            forward("/SignUp.jsp");
            return;
        }
        
        userModel = new UserHandler();
        User account = new User(userParam, passwordParam);
        userModel.createUser(account);
        forward("/Login.jsp");
    }
}

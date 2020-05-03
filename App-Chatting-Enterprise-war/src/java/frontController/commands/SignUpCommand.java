package frontController.commands;

import ejbs.stateless.UserHandler;
import models.Error;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.User;

public class SignUpCommand extends AbstractCommand{
    @Override
    public void process() {
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
        
        if(new UserHandler().exists(newUser)){
            request.getSession().setAttribute("Error", new Error().saveError("User already exists."));
            forward("/SignUp.jsp");
            return;
        }
        
        new UserHandler().signUp(newUser);
        forward("/Login.jsp");
    }
}

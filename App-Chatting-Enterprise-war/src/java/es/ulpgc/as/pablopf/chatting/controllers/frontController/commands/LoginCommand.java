package es.ulpgc.as.pablopf.chatting.controllers.frontController.commands;

import es.ulpgc.as.pablopf.chatting.controllers.frontController.AbstractCommand;
import es.ulpgc.as.pablopf.chatting.models.User;
import es.ulpgc.as.pablopf.chatting.models.Error;
import es.ulpgc.as.pablopf.chatting.controllers.databaseHandler.UserHandler;

public class LoginCommand extends AbstractCommand{

    @Override
    public void process() {
        User account = new User();
        UserHandler userModel = new UserHandler();
        
        String user = request.getParameter("userText");
        String password = request.getParameter("passwordText");
        
        account.setUser(user);
        account.setPassword(password);
        
        if(user.length() <= 0 || password.length() <= 0){
            request.getSession().setAttribute("Error", new Error().saveError("Incorrect username or password."));
            forward("/Login.jsp");
            return;
        }
        
        if(userModel.login(account)){
            request.getSession().setAttribute("currentAccount", account);
            forward("/RefreshChat.jsp");
        }else{
            request.getSession().setAttribute("Error", new Error().saveError("Incorrect username or password."));
            forward("/Login.jsp");
        }
    }
}

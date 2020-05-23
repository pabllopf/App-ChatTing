package frontController.commands;

import models.LogMessage;
import models.User;

public class ChangePasswordCommand  extends AbstractCommand{
    @Override
    public void process() {
        logRemote.add(new LogMessage("CleanHistoryCommand::process"));
        
        String passwordParam = request.getParameter("passwordText");
        String passwordRepeatParam = request.getParameter("passwordrepeatText");
        
        if(passwordParam.length() <= 0 || passwordRepeatParam.length() <= 0){
            request.getSession().setAttribute("Error", new models.Error().saveError("Password EMPTY are not supported."));
            forward("/ChangePassword.jsp");
            return;
        }
        
        if(!passwordParam.equals(passwordRepeatParam)){
            request.getSession().setAttribute("Error", new models.Error().saveError("Introduce the same password."));
            forward("/SignUp.jsp");
            return;
        }
        
        User currentAccount = (User) request.getSession().getAttribute("currentAccount");
        
        currentAccount.setPassword(passwordParam);
        
        userHandler.changePassword(currentAccount);
        
        forward("/Chat.jsp");
    }
    
}

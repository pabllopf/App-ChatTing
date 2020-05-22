package frontController.commands;
import models.Error;
import models.LogMessage;
import models.User;

public class LoginCommand extends AbstractCommand{
    @Override
    public void process() {
        logRemote.add(new LogMessage("LoginCommand::process"));
        
        User account = new User(request.getParameter("userText"), request.getParameter("passwordText"));
        
        if(userHandler.login(account)){
            request.getSession().setAttribute("currentAccount", account);
            statistics.countNewLoggin();
            logRemote.add(new LogMessage("Logged::name::" + account.getUser()));
            forward("/RefreshChat.jsp");
        }else{
            request.getSession().setAttribute("Error", new Error().saveError("Incorrect username or password."));
            forward("/Login.jsp");
        }
    }
}

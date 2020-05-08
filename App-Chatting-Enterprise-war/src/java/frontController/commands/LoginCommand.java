package frontController.commands;
import ejbs.singleton.LogRemote;
import ejbs.singleton.StatisticsRemote;
import ejbs.stateless.UserHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import models.Error;
import models.LogMessage;
import models.User;

public class LoginCommand extends AbstractCommand{
    @Override
    public void process() {
        logRemote.add(new LogMessage("LoginCommand::process"));
        
        User account = new User(request.getParameter("userText"), request.getParameter("passwordText"));
        if(new UserHandler().login(account)){
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

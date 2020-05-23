package frontController.commands;
import ejbs.singleton.LogRemote;
import javax.ejb.EJB;
import models.Error;
import models.LogMessage;

public class ErrorLogCommand extends AbstractCommand{
    @Override
    public void process() {
        logRemote.add(new LogMessage("ErrorLogCommand::process"));
        
        Error error = (Error) request.getSession().getAttribute("Error");
        if(error != null){
            new Error().saveError(error.getMessage());
            logRemote.add(new LogMessage("ErrorLogCommand::Error::" + error.getMessage()));
        }
        
        Error errorPage = (Error) request.getSession().getAttribute("ErrorPage"); 
        if(errorPage != null){
            new Error().saveError(errorPage.getMessage());
            logRemote.add(new LogMessage("ErrorLogCommand::Error::" + errorPage.getMessage()));
        }
        
        
        forward("/index.jsp");
    }
}

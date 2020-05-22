package frontController.commands;

import models.LogMessage;

public class ConfigLogCommand extends AbstractCommand{

    @Override
    public void process() {
        String timeIntroduced = request.getParameter("timeIntroduced");
        logRemote.add(new LogMessage("ConfigLogCommand::withTimeInMs:: " + timeIntroduced));
        
        logRemote.setTimeToTimeServide(Integer.parseInt(timeIntroduced));
        
        forward("/RefreshChat.jsp");
    }
    
}

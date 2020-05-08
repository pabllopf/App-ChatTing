package frontController.commands;

import models.LogMessage;
import models.User;


public class LogOutCommand extends AbstractCommand{
    @Override
    public void process() {
        logRemote.add(new LogMessage("LogOutCommand::LogOut::" + ((User)request.getSession().getAttribute("currentAccount")).getUser()));
        request.getSession().setAttribute("currentAccount", null);
        forward("/index.jsp");
    }
}

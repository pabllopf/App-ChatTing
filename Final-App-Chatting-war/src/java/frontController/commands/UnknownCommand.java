package frontController.commands;

import models.Error;
import models.LogMessage;

public class UnknownCommand extends AbstractCommand{
    @Override
    public void process() {
        logRemote.add(new LogMessage("UnknownCommand::process"));
        request.getSession().setAttribute("ErrorPage", new Error().saveError("UnknownCommand"));
        forward("/ErrorPage.jsp");
    }
}

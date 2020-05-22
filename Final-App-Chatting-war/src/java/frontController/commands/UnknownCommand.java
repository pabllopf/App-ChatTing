package frontController.commands;

import models.Error;
import models.LogMessage;

public class UnknownCommand extends AbstractCommand{
    @Override
    public void process() {
        logRemote.add(new LogMessage("UnknownCommand::process"));
        Error errorMessage = new Error().saveError("Unknown Command");
        request.getSession().setAttribute("ErrorPage", errorMessage);
        forward("/ErrorPage.jsp");
    }
}

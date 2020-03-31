package es.ulpgc.as.pablopf.chatting.controllers.frontController.commands;

import es.ulpgc.as.pablopf.chatting.controllers.frontController.AbstractCommand;
import es.ulpgc.as.pablopf.chatting.models.Error;
public class UnknownCommand extends AbstractCommand{
    @Override
    public void process() {
        Error errorMessage = new Error().saveError("Unknown Command");
        request.getSession().setAttribute("ErrorPage", errorMessage);
        forward("/ErrorPage.jsp");
    }
}

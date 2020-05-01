package es.ulpgc.as.pablopf.chatting.controllers.frontController.commands;

import es.ulpgc.as.pablopf.chatting.controllers.frontController.AbstractCommand;

public class LogOutCommand extends AbstractCommand{
    
    @Override
    public void process() {
        request.getSession().setAttribute("currentAccount", null);
        forward("/index.jsp");
    }
}

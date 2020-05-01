package es.ulpgc.as.pablopf.chatting.controllers.frontController.commands;

import es.ulpgc.as.pablopf.chatting.controllers.frontController.AbstractCommand;
import es.ulpgc.as.pablopf.chatting.models.Error;
public class ErrorLogCommand extends AbstractCommand{
    
    @Override
    public void process() {
        Error error = (Error) request.getSession().getAttribute("Error");
        if(error != null){
            new Error().saveError(error.getMessage());
        }
        
        Error errorPage = (Error) request.getSession().getAttribute("ErrorPage"); 
        if(errorPage != null){
            new Error().saveError(errorPage.getMessage());
        }
        
        forward("/index.jsp");
    }
}

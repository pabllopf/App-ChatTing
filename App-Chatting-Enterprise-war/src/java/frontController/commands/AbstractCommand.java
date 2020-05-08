package frontController.commands;


import ejbs.singleton.LogRemote;
import ejbs.singleton.StafulContainerRemote;
import ejbs.singleton.StatisticsRemote;
import ejbs.stateful.MessagePackRemote;
import frontController.commands.UnknownCommand;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.LogMessage;

public abstract class AbstractCommand {
    protected ServletContext context; 
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    
    @EJB
    protected LogRemote logRemote;
    
    @EJB
    protected StatisticsRemote statistics;
    
    @EJB 
    protected StafulContainerRemote stafulContainer;
    
    @EJB
    protected MessagePackRemote packToDelete;
    
    public void init(ServletContext context, HttpServletRequest request, HttpServletResponse response)
    {
        this.context = context;
        this.request = request;
        this.response = response;
        
        try {
            logRemote = (LogRemote)InitialContext.doLookup("java:global/App-Chatting-Enterprise/App-Chatting-Enterprise-ejb/Log");
        } catch (NamingException ex) {
            Logger.getLogger(MessageToDeleteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            statistics = (StatisticsRemote)InitialContext.doLookup("java:global/App-Chatting-Enterprise/App-Chatting-Enterprise-ejb/Statistics");
        } catch (NamingException ex) {
            Logger.getLogger(MessageToDeleteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            stafulContainer = (StafulContainerRemote)InitialContext.doLookup("java:global/App-Chatting-Enterprise/App-Chatting-Enterprise-ejb/StafulContainer");
        } catch (NamingException ex) {
            Logger.getLogger(MessageToDeleteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            packToDelete = (MessagePackRemote) InitialContext.doLookup("java:global/App-Chatting-Enterprise/App-Chatting-Enterprise-ejb/MessagePack");
            stafulContainer.add("messagePack", "java:global/App-Chatting-Enterprise/App-Chatting-Enterprise-ejb/MessagePack");
        } catch (NamingException ex) {
            Logger.getLogger(MessageToDeleteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
   
    abstract public void process();
    
     public void forward(String target){
         try{
            RequestDispatcher dp= context.getRequestDispatcher(target);
           
            dp.forward(request, response);
        }
        catch (ServletException | IOException ex) {
            Logger.getLogger(UnknownCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

package frontController.commands;


import ejbs.stateless.controllers.TableUsersFacade;
import ejbs.singleton.LogRemote;
import ejbs.singleton.StafulContainerRemote;
import ejbs.singleton.StatisticsRemote;
import ejbs.stateful.MessagePackRemote;
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

public abstract class AbstractCommand {
    protected ServletContext context; 
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    
    @EJB
    protected TableUsersFacade userHandler;
    
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
            userHandler = (TableUsersFacade)InitialContext.doLookup("java:global/Final-App-Chatting/Final-App-Chatting-ejb/TableUsersFacade!ejbs.stateless.controllers.TableUsersFacade");
        } catch (NamingException ex) {
            Logger.getLogger(MessageToDeleteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            logRemote = (LogRemote)InitialContext.doLookup("java:global/Final-App-Chatting/Final-App-Chatting-ejb/Log!ejbs.singleton.LogRemote");
        } catch (NamingException ex) {
            Logger.getLogger(MessageToDeleteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            statistics = (StatisticsRemote)InitialContext.doLookup("java:global/Final-App-Chatting/Final-App-Chatting-ejb/Statistics");
        } catch (NamingException ex) {
            Logger.getLogger(MessageToDeleteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            stafulContainer = (StafulContainerRemote)InitialContext.doLookup("java:global/Final-App-Chatting/Final-App-Chatting-ejb/StafulContainer");
        } catch (NamingException ex) {
            Logger.getLogger(MessageToDeleteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            packToDelete = (MessagePackRemote) InitialContext.doLookup("java:global/Final-App-Chatting/Final-App-Chatting-ejb/MessagePack!ejbs.stateful.MessagePackRemote");
            stafulContainer.add("messagePack", "java:global/Final-App-Chatting/Final-App-Chatting-ejb/MessagePack!ejbs.stateful.MessagePackRemote");
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

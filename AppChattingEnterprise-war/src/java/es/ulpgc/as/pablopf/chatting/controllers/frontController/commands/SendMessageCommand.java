package es.ulpgc.as.pablopf.chatting.controllers.frontController.commands;

import es.ulpgc.as.pablopf.chatting.controllers.frontController.AbstractCommand;
import es.ulpgc.as.pablopf.chatting.models.User;
import es.ulpgc.as.pablopf.chatting.models.Chat;
import es.ulpgc.as.pablopf.chatting.models.Error;
import es.ulpgc.as.pablopf.chatting.models.Message;
import es.ulpgc.as.pablopf.chatting.controllers.databaseHandler.MessageHandler;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SendMessageCommand extends AbstractCommand{

    @Override
    public void process() { 
        MessageHandler messageModel = new MessageHandler();
        
        Chat currentChat = (Chat) request.getSession().getAttribute("currentChat");
        User currentAccount = (User) request.getSession().getAttribute("currentAccount");
        
        String messageContent = request.getParameter("sendMessage");
        
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = dateTimeFormatter.format(localDateTime);
        
        if(messageContent.length() <= 0 || messageContent.length() > 1000){
            request.getSession().setAttribute("Error", new Error().saveError("Incorrect message: enter a message between 1 and 1000 characters."));
            forward("/Chat.jsp");
        }else{
            Message message = new Message(currentAccount.getUser(), messageContent, date);
            messageModel.create(message, currentChat.getName());
        
            forward("/RefreshChat.jsp");
        }
    } 
}
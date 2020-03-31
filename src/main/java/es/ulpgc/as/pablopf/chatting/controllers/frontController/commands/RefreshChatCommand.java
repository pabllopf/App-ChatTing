package es.ulpgc.as.pablopf.chatting.controllers.frontController.commands;

import es.ulpgc.as.pablopf.chatting.controllers.frontController.AbstractCommand;
import es.ulpgc.as.pablopf.chatting.models.Chat;
import es.ulpgc.as.pablopf.chatting.controllers.databaseHandler.MessageHandler;

public class RefreshChatCommand extends AbstractCommand{

    @Override
    public void process() {
        MessageHandler messageModel = new MessageHandler();
        Chat currentChat = (Chat) request.getSession().getAttribute("currentChat");
        
        if(currentChat == null){
            currentChat = new Chat();
            currentChat.setName("publicroom");
            currentChat.setMessages(messageModel.getAllMessages("publicroom"));
        }
        
        currentChat.setMessages(messageModel.getAllMessages(currentChat.getName()));
        
        request.getSession().setAttribute("currentChat", currentChat);
        forward("/Chat.jsp");
    } 
}

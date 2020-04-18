package es.ulpgc.as.pablopf.chatting.models;

import es.ulpgc.as.pablopf.chatting.models.Message;
import java.util.ArrayList;

public class Chat {
    private String name;
    private ArrayList<Message> messages;

    public Chat(String name, ArrayList<Message> messages) {
        this.name = name;
        this.messages = messages;
    }
    
    public Chat() {
        name = "";
        messages = new ArrayList<Message>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
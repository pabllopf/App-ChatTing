package tables;

import javax.persistence.Embeddable;

@Embeddable
public class TableUserChat {
    private String chatList;
    
    public TableUserChat(){
        chatList = "";
    }
    
    public void setChat(String chat){
        this.chatList = chat;
    }
    
    public String getChat(){
        return chatList;
    }
}

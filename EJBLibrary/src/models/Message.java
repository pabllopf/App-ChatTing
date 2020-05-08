package models;

public class Message {
    private String id;
    private String user;
    private String content;
    private String created_at;
    
    public Message(String id, String user, String content, String created_at) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.created_at = created_at;
    }
    
    public Message(String user, String content, String created_at) {
        this.user = user;
        this.content = content;
        this.created_at = created_at;
    }

    public String getId(){
        return id;
    }
    
    public String getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public String getCreated_at() {
        return created_at;
    }
}

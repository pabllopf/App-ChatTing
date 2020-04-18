package es.ulpgc.as.pablopf.chatting.models;

public class Message {
    private int id;
    private String name;
    private String message;
    private String created_at;

    public Message(int id, String name, String message, String created_at) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.created_at = created_at;
    }

    public Message(String name, String message, String created_at) {
        this.name = name;
        this.message = message;
        this.created_at = created_at;
    }

    public Message() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
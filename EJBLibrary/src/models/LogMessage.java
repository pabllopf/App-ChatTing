package models;

public class LogMessage {
    private String content;

    public LogMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getLog(){
        return "LOG:: " + content;
    }
}

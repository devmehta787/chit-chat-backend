package models;

public class Message {
    private String sessionId;
    private Long senderId;
    private Long receiverId;
    private String content;

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId2) {
        Long sender_id=Long.parseLong(senderId2);
        this.senderId = sender_id;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId2) {
        Long receiver_id=Long.parseLong(receiverId2);
        this.receiverId = receiver_id;
    }

    // Constructors (you can add more if needed)
    public Message() {
    }

    public Message(String from, String content) {
        this.content = content;
    }

    // Getters and Setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void sessionId(String id) {
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}

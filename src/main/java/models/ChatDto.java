package models;

import java.sql.Time;

public class ChatDto {
    private Long message_id;
    private Long sender_id;
    private Long receiver_id;
    private String message_content;
    public String getMessage_content() {
        return message_content;
    }
    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }
    private Time time_stamp;
    public Long getMessage_id() {
        return message_id;
    }
    public void setMessage_id(Long message_id) {
        this.message_id = message_id;
    }
    public Long getSender_id() {
        return sender_id;
    }
    public void setSender_id(Long sender_id) {
        this.sender_id = sender_id;
    }
    public Long getReceiver_id() {
        return receiver_id;
    }
    public void setReceiver_id(Long receiver_id) {
        this.receiver_id = receiver_id;
    }
    
    public Time getTimestamp() {
        return time_stamp;
    }
    public void setTimestamp(Time time_stamp) {
        this.time_stamp = time_stamp;
    }
}

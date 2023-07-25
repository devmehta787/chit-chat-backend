package models;

import java.math.BigInteger;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="MessageTable",schema="public")
public class Chat {
    @Id
    @Column(name="message_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public BigInteger message_id;

    @Column(name="sender_id")
    public Long sender_id;

    @Column(name="receiver_id")
    public Long receiver_id;

    @Column(name="message_content")
    public String message_content;

    @Column(name="time_stamp")
    public Time time_stamp;

    
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

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public Time getTimestamp() {
        return time_stamp;
    }

    public void setTimestamp(Time time_stamp) {
        this.time_stamp = time_stamp;
    }

    public Chat() {
    }
    
    public Chat(Long sender_id, Long receiver_id, String message_content) {
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.message_content = message_content;
        this.time_stamp = new Time(System.currentTimeMillis());
    }
}

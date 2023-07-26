package models;

import java.sql.Time;
import java.util.Date;

import javax.annotation.processing.Generated;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "messagetable", schema = "public")
@NamedQueries({
        @NamedQuery(name = "messagetable.getChatData", query = "SELECT x From Chat x WHERE x.sender_id = :paramId"),
        @NamedQuery(name = "messagetable.getChatDataBySenderIdAndReceiverId", query = "SELECT x From Chat x WHERE (x.sender_id = :paramId AND x.receiver_id = :paramId2)UNION (x.sender_id = :paramId2 AND x.receiver_id = :paramId) sort by message_id)")
})


public class Chat {
    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long message_id;

    @Column(name = "sender_id")
    public Long sender_id;
    @Column(name = "receiver_id")
    public Long receiver_id;
    @Column(name = "message_content")
    public String message_content;
    @Column(name = "time_stamp")
    public Time time_stamp;

    // public Long getMessage_id() {
    //     return message_id;
    // }
    // public void setMessage_id(Long message_id) {
    //     this.message_id = message_id;
    // }

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

    public Time getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(Time time_stamp) {
        this.time_stamp = time_stamp;
    }

    public Chat() {

    }

    public Chat(Long sender_id, Long receiver_id, String message_content) {
        super();
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.message_content = message_content;
        long timeInMillis = System.currentTimeMillis();
        Date date = new Date(timeInMillis);
        this.time_stamp = new Time(date.getTime());
    }
}

package models;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="friend",schema="public")
public class Friend {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name="userid")
    private BigInteger userId;

    @Column(name="friendid")
    private BigInteger friendId;

    // public BigInteger getId() {
    //     return id;
    // }

    // public void setId(BigInteger id) {
    //     this.id = id;
    // }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public BigInteger getFriendId() {
        return friendId;
    }

    public void setFriendId(BigInteger friendId) {
        this.friendId = friendId;
    }

    // public Friend() {
        
    // }
    public Friend(BigInteger userId, BigInteger friendId) {
        super();
        this.userId = userId;
        this.friendId = friendId;
    }
    
}

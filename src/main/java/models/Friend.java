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
    public BigInteger id;

    @Column(name="userid")
    public Long userId;

    @Column(name="friendid")
    public Long friendId;

    // public BigInteger getId() {
    //     return id;
    // }

    // public void setId(BigInteger id) {
    //     this.id = id;
    // }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public Friend() {
        
    }
    public Friend(Long userId, Long friendId) {
        super();
        this.userId = userId;
        this.friendId = friendId;
    }
    
}

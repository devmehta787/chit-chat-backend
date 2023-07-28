package models;

import java.math.BigInteger;

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
@Table(name = "friend", schema = "public")
@NamedQueries({
    @NamedQuery(name = "friend.getFriendData", query = "SELECT f FROM Friend f WHERE f.userId = :paramId")
})

public class Friend {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

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

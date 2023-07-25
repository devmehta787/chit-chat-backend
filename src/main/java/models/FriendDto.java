package models;

import java.math.BigInteger;

// import java.math.BigInteger;

public class FriendDto {
    private Long id;
    private BigInteger userId;
    private BigInteger friendId;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    
}

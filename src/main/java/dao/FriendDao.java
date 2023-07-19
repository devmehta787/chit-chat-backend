package dao;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import models.Friend;
import models.FriendDto;

public class FriendDao {
    @Inject
    Provider<EntityManager> entityManagerProvider;

    @Transactional
    public FriendDto addNewFriend(FriendDto friendDto) {
        EntityManager entityManager = entityManagerProvider.get();
        Friend friend = new Friend();
        friend.setUserId(friendDto.getUserId());
        friend.setFriendId(friendDto.getFriendId());
        entityManager.persist(friend);
        return friendDto;
    }
    
}

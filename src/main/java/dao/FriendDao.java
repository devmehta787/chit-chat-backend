package dao;

import java.math.BigInteger;

import javax.persistence.EntityManager;

// import org.hibernate.mapping.List;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import models.Friend;
import models.FriendDto;
import models.User;

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
    public Friend getFriendById(BigInteger userId) {
        EntityManager entityManager = entityManagerProvider.get();
        List<Friend> f = entityManager.createQuery("SELECT x FROM Friend x WHERE userId= :idparam",
            Friend.class)
                .setParameter("idparam", userId)
                .getResultList();
                Friend friend = f.get(0);
                
        return friend;
    }
    
}

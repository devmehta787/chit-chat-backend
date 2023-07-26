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

    @Transactional
    public Friend getFriendById(Long userId) {
        EntityManager entityManager = entityManagerProvider.get();
        List<Friend> f = entityManager.createQuery("SELECT x FROM Friend x WHERE x.userId= :idparam",
                Friend.class)
                .setParameter("idparam", userId)
                .getResultList();
        if(f.size()==0){
            return null;
        }
        Friend friend = f.get(0);

        return friend;
    }
    
    public boolean delete(Long id) {
        return deleteFriend(id);
    }

    @Transactional
    public boolean deleteFriend(Long friend_id) {
        try {
            EntityManager entityManager = entityManagerProvider.get();
            Friend friend = entityManager.find(Friend.class, friend_id);
            entityManager.remove(friend);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        // EntityManager entityManager = entityManagerProvider.get();
        // Friend friend = entityManager.find(Friend.class, id);
        // if (friend != null) {
        //     entityManager.remove(friend);
        //     return true;
        // }
        // return false;
    }

    // public boolean delete(int friendId) {
    //     EntityManager entityManager = entityManagerProvider.get();
    //     ("DELETE x FROM Friend x WHERE friendId= :idparam");
        
    // }
}

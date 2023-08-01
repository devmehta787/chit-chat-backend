package dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import models.Chat;
import models.ChatDto;

import java.util.List;

import javax.persistence.EntityManager;


public class ChatDao {
    @Inject
    Provider<EntityManager> entityManagerProvider;

    @Transactional
    public ChatDto addNewChat(ChatDto chatDto) {
        EntityManager entityManager = entityManagerProvider.get();
        Chat chat = new Chat();

        chat.setSender_id(chatDto.getSender_id());
        chat.setReceiver_id(chatDto.getReceiver_id());
        chat.setMessage_content(chatDto.getMessage_content());

        entityManager.persist(chat);

        return chatDto;
    }
    
    @Transactional
    public List<Chat> getChatsBySenderId(Long senderId) {
        EntityManager entityManager = entityManagerProvider.get();
        List<Chat> c = entityManager.createNamedQuery("messagetable.getChatData",
                Chat.class)
                .setParameter("paramId", senderId)
                .getResultList();
        if (c.size() == 0) {
            return null;
        }
        // Chat chat = c.get(0);

        return c;
    }
    
    @Transactional
    public List<Chat> getChatBySenderIdAndSenderId(Long senderId, Long receiverId) {
        EntityManager entityManager = entityManagerProvider.get();
        List<Chat> c1 = entityManager.createNamedQuery("messagetable.getChatDataBySenderIdAndReceiverId",
                Chat.class)
                .setParameter("paramId", senderId)
                .setParameter("paramId2", receiverId)
                .getResultList();
        List<Chat> c2 = entityManager.createNamedQuery("messagetable.getChatDataBySenderIdAndReceiverId",
                Chat.class)
                .setParameter("paramId2", senderId)
                .setParameter("paramId", receiverId)
                .getResultList();
        for (int i = 0; i < c2.size(); i++) {
            c1.add(c2.get(i));
        }
        c1.sort((Chat c3, Chat c4) -> c3.getMessage_id().compareTo(c4.getMessage_id()));
        if (c1.size() == 0) {
            return null;
        }
        return c1;
    }

    @Transactional
    public boolean delete(Long id) {
        try{
            EntityManager entityManager = entityManagerProvider.get();
            Chat chat = entityManager.find(Chat.class, id);
            entityManager.remove(chat);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

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
        List<Chat> c = entityManager.createNamedQuery("messagetable.getChatDataBySenderIdAndReceiverId",
                Chat.class)
                .setParameter("paramId", senderId)
                .setParameter("paramId2", receiverId)
                .getResultList();
        if (c.size() == 0) {
            return null;
        }
        return c;
    }
}

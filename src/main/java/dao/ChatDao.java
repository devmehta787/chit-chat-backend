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
    public Chat getChatsBySenderId(Long senderId) {
        EntityManager entityManager = entityManagerProvider.get();
        List<Chat> c = entityManager.createQuery(" SELECT m FROM chat m WHERE m.sender_id= :idparam",
                Chat.class)
                .setParameter("idparam", senderId)
                .getResultList();
        if(c.size()==0){
            return null;
        }
        Chat chat = c.get(0);

        return chat;
    }
}

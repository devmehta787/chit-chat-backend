package dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import models.Chat;
import models.ChatDto;

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
}

package dao;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import models.Chat;
import models.ChatDto;

public class ChatDao {
    @Inject
    Provider<EntityManager> entityManagerProvider;

    public boolean startNewChat(ChatDto chatDto) {
        EntityManager entityManager = entityManagerProvider.get();
        Chat newchat = new Chat();

        newchat.setSender_id(chatDto.getSender_id());
        newchat.setReceiver_id(chatDto.getReceiver_id());

        entityManager.persist(newchat);

        return true;
    }

    public Chat getChatByUserId(Long user_id) {
        EntityManager entityManager = entityManagerProvider.get();
        Chat chat = entityManager.find(Chat.class, user_id);
        return chat;
    }

    public Chat getChatByChatId(Long chat_id) {
        EntityManager entityManager = entityManagerProvider.get();
        Chat chat = entityManager.find(Chat.class, chat_id);
        return chat;
    }


    public boolean sendMessage(ChatDto chatDto) {
        try {
            EntityManager entityManager = entityManagerProvider.get();
            Chat chat = entityManager.find(Chat.class, chatDto.getMessage_id());
            chat.setMessage_content(chatDto.getMessage());
            entityManager.persist(chat);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean endChat(Long chat_id) {
        try {
            EntityManager entityManager = entityManagerProvider.get();
            Chat chat = entityManager.find(Chat.class, chat_id);
            entityManager.remove(chat);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

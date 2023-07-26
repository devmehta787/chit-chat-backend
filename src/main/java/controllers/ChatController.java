package controllers;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.inject.Inject;
import dao.ChatDao;
import models.Chat;
import models.ChatDto;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;

public class ChatController {
    @Inject
    ChatDao chatDao;
    private static final Logger log = LogManager.getLogger(ChatController.class);

    public Result startChat(ChatDto chatDto) {
        chatDao.addNewChat(chatDto);
        return Results.ok().json().render("New Chat Started");
    }

    public Result getChats(@PathParam("sender_id") Long sender_id) {
        // try {
        //     ChatDto chat = chatDao.getChatsBySenderId(sender_id);
        //     return Results.ok().json().render(chat);
        // } catch (Exception e) {
        //     e.printStackTrace();

        //     return Results.ok().json().render("Not Found");
        // }
        log.info(sender_id);
        // Long sender_id1 = Long.parseLong(sender_id);
        Chat c = chatDao.getChatsBySenderId(sender_id);
        return Results.ok().json().render(c);
    }

}

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
// import ninja.websockets.WebSocketHandshake;

public class ChatController {
    @Inject
    ChatDao chatDao;
    private static final Logger log = LogManager.getLogger(ChatController.class);

    public Result startChat(ChatDto chatDto) {
        log.info(chatDto);
        chatDao.addNewChat(chatDto);
        return Results.ok().json().render("New Chat Started");
            // .addHeader("Access-Control-Allow-Origin", "*")
            // .addHeader("Access-Control-Allow-Credentials", "true");
    }
    public Result getChats(@PathParam("sender_id") Long sender_id) {
        log.info(sender_id);
        List<Chat> c = chatDao.getChatsBySenderId(sender_id);
        return Results.ok().addHeader("Access-Control-Allow-Origin", "*").json().render(c);
    }

    public Result getChat(@PathParam("sender_id") Long sender_id, @PathParam("receiver_id") Long receiver_id) {
        // log.info(sender_id);
        // log.info(receiver_id);
        List<Chat> c = chatDao.getChatBySenderIdAndSenderId(sender_id, receiver_id);
        return Results.ok().addHeader("Access-Control-Allow-Origin", "*").json().render(c);
    }

    public Result deleteChat(@PathParam("chatId") Long message_id) {
        chatDao.delete(message_id);
        return Results.ok().addHeader("Access-Control-Allow-Origin", "*").json().render("Chat Ended");
    }

}

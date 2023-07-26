package controllers;

import com.google.inject.Inject;
import dao.ChatDao;
import models.ChatDto;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;

public class ChatController {
    @Inject
    ChatDao chatDao;

    public Result startChat(ChatDto chatDto) {
        chatDao.addNewChat(chatDto);
        return Results.ok().json().render("New Chat Started");
    }

}

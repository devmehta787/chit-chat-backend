package controllers;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.ChatDao;
import models.ChatDto;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;


public class ChatController {

    @Inject
    ChatDao chatDao;
    // private static final Logger log = LogManager.getLogger(ChatController.class);
    
    public Result startChat(ChatDto chatDto) {
        try {
            chatDao.startNewChat(chatDto);
            return Results.ok().json().render("Chat Started");
        } catch (Exception e) {
            e.printStackTrace();
            return Results.ok().json().render("Chat Not Started");
        }
    }
    

    public Result getChats(@PathParam("userId") Long userId) {
        // log.info(name);
        try {
            ChatDto chat = chatDao.getChatByUserId(userId);
            return Results.ok().json().render(chat);
        } catch (Exception e) {
            e.printStackTrace();

            return Results.ok().json().render("Not Found");
        }
    }
    
    public Result getChat(@PathParam("chatId") Long chatId) {
        // log.info(name);
        try {
            ChatDto chat = chatDao.getChatByChatId(chatId);
            return Results.ok().json().render(chat);
        } catch (Exception e) {
            e.printStackTrace();

            return Results.ok().json().render("Not Found");
        }
    }

    public Result sendMessage(ChatDto chatDto) {
        try {
            chatDao.sendMessage(chatDto);
            return Results.ok().json().render("Message Sent");
        } catch (Exception e) {
            e.printStackTrace();
            return Results.ok().json().render("Message Not Sent");
        }
    }

    public Result endChat(@PathParam("chatId") Long chatId) {
        try {
            chatDao.endChat(chatId);
            return Results.ok().json().render("Chat Ended");
        } catch (Exception e) {
            e.printStackTrace();
            return Results.ok().json().render("Chat Not Ended");
        }
    }
}

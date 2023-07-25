package controllers;

import com.google.inject.Inject;

import java.math.BigInteger;

import javax.websocket.server.PathParam;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.FriendDao;
import models.FriendDto;
import models.Friend;
// import models.UserDto;
import ninja.Result;
import ninja.Results;

public class FriendController {
    @Inject
    FriendDao friendDao;
    // private static final Logger log = LogManager.getLogger(FriendController.class);
    
    public Result addFriend(FriendDto friendDto) {
        friendDao.addNewFriend(friendDto);
        return Results.ok().json().render("New Friend Added");
    }

    public Result getFriends(@PathParam("userId") BigInteger userId) {
        // log.info(name);
        try{
            Friend friend = friendDao.getFriendById(userId);
            return Results.ok().json().render(friend);
        }
        catch(Exception e){
            e.printStackTrace();

            return Results.ok().json().render("Not Found");
        }
    }

    public Result deleteFriend(@PathParam("friendId") Long friend_id){
        // BigInteger big=BigInteger.valueOf(id);
	boolean status = friendDao.delete(friend_id);
    if (status == true) {
        return Results.ok().json().render("Deleted");
    }
    return Results.ok().json().render("Not Found");
    }
}

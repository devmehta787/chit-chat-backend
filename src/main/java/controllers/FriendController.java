package controllers;

import com.google.inject.Inject;

import java.math.BigInteger;
import java.util.List;

// import javax.websocket.server.PathParam;
import ninja.params.PathParam;

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
    private static final Logger log = LogManager.getLogger(FriendController.class);
    
    public Result addFriend(FriendDto friendDto) {
        friendDao.addNewFriend(friendDto);
        return Results.ok().json().render("New Friend Added");
    }

    public Result getFriends(@PathParam("userId") Long userId) {
        log.info(userId);
        // try{
            List<Friend> f = friendDao.getFriendById(userId);
            return Results.ok().addHeader("Access-Control-Allow-Origin", "*").json().render(f);
        // } catch(Exception e){
        //     e.printStackTrace();
        //     return Results.ok().json().render("Not Found");
        // }
    }

    public Result getFriendsById(@PathParam("id") Long userId) {
        log.debug(userId);
        return Results.ok().json().render(userId);
    }

    public Result deleteFriend(@PathParam("friend_id") Long friend_id){
        // BigInteger big=BigInteger.valueOf(id);
	    boolean status = friendDao.delete(friend_id);
        if (status == true) {
            return Results.ok().json().render("Deleted");
        }
        return Results.ok().json().render("Not Found");
    }
}

// package controllers;

// import javax.naming.spi.DirStateFactory.Result;

// import dao.UserDao;
// import models.UserDto;
// import ninja.Context;
// import ninja.Results;

// import org.apache.log4j.Logger; 
// import org.apache.log4j.LogManager;


package controllers;

import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.UserDao;
import models.User;
import models.UserDto;
// import models.UsersDto;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;



public class UserController {

    @Inject
    UserDao userDao;
    private static final Logger log = LogManager.getLogger(UserController.class);
    
    public Result addUser(UserDto userDto) {
        // log.info(userDto);
        userDao.addNewUser(userDto);
        // context.getFlashScope().success("New user Addded");
        return Results.ok().json().render("New User Added");


        // add condition for already existing user
    }

    public Result loginUser(UserDto userDto) {
        User user = userDao.isUserAndPasswordValid(userDto.getUsername(), userDto.getPassword());
        if (user.username != null) {
            return Results.ok().json().render(user);
        } else {
            return Results.ok().json().render("Login Failed");
        }
    }

    public Result getUserById(@PathParam("name") String name) {
        log.info(name);
        User user = userDao.getUserByName(name);
        return Results.ok().json().render(user);
    }
    public Result getUser() {
        // log.info(name);
        List<User> userList = userDao.getUser();
        return Results.ok().json().render(userList);
    }
}

/**
 * Copyright (C) the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Copyright (C) 2012-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package conf;

// import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import ninja.utils.NinjaProperties;

import com.google.inject.Inject;

// import controllers.ApiController;
import controllers.ApplicationController;
import controllers.ChatController;
// import controllers.ArticleController;
// import controllers.ChatController;
import controllers.FriendController;
// import controllers.LoginLogoutController;
import controllers.UserController;

public class Routes implements ApplicationRoutes {
    
    @Inject
    NinjaProperties ninjaProperties;

    /**
     * Using a (almost) nice DSL we can configure the router.
     * 
     * The second argument NinjaModuleDemoRouter contains all routes of a
     * submodule. By simply injecting it we activate the routes.
     * 
     * @param router
     *            The default router of this application
     */
    @Override
    public void init(Router router) {  
        
        // puts test data into db:
        if (!ninjaProperties.isProd()) {
                router.GET().route("/setup").with(ApplicationController::setup);
        }
        
//        router.GET().route("/login").with(LoginLogoutController::login);
//         ///////////////////////////////////////////////////////////////////////
        // Login / Logout
        ///////////////////////////////////////////////////////////////////////
        // router.GET().route("/login").with(LoginLogoutController::login);
        // router.POST().route("/login").with(LoginLogoutController::loginPost);
        // router.GET().route("/logout").with(LoginLogoutController::logout);
        
        ///////////////////////////////////////////////////////////////////////
        // Create new article
        ///////////////////////////////////////////////////////////////////////
//        router.GET().route("/article/new").with(ArticleController::articleNew);
//        router.POST().route("/article/new").with(ArticleController::articleNewPost);
        
        ///////////////////////////////////////////////////////////////////////
        // Create new article
        ///////////////////////////////////////////////////////////////////////
//        router.GET().route("/article/{id}").with(ArticleController::articleShow);

        ///////////////////////////////////////////////////////////////////////
        // Api for management of software
        ///////////////////////////////////////////////////////////////////////
//        router.GET().route("/api/{username}/articles.json").with(ApiController::getArticlesJson);
//        router.GET().route("/api/{username}/article/{id}.json").with(ApiController::getArticleJson);
//        router.GET().route("/api/{username}/articles.xml").with(ApiController::getArticlesXml);
//        router.POST().route("/api/{username}/article.json").with(ApiController::postArticleJson);
//        router.POST().route("/api/{username}/article.xml").with(ApiController::postArticleXml);
 
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
//        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController::serveWebJars);
//        router.GET().route("/assets/{fileName: .*}").with(AssetsController::serveStatic);
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        //        router.GET().route("/.*").with(ApplicationController::index);


        // 1. **Account Creation/Login Functionality:**
        //     - POST /API/register: Create a new user account.
        //     - POST /API/login: Authenticate and log in as a user.

        router.POST().route("/user/new").with(UserController::addUser);
        router.POST().route("/user/login").with(UserController::loginUser);

        router.GET().route("/user/getByName/{name}").with(UserController::getUserById);


        // 2. **Friend Management:**
        //     - POST /API/friends: Add a friend using their contact number.
        //     - GET /API/friends: Retrieve the list of a user's friends.
        //     - DELETE /API/friends/(friend Id): Remove a friend from the user's friend list.
        
        router.POST().route("/user/addFriend").with(FriendController::addFriend);
        router.GET().route("/user/getFriends/{userId}").with(FriendController::getFriends);
        router.DELETE().route("/user/deleteFriend/{friendId}").with(FriendController::deleteFriend);
        



        // 3. **Chat Functionality:**
        //     - POST /API/chats/(friend Id): Start a new chat with a friend.
        //     - GET /API/chats: Retrieve a list of ongoing chats for the logged-in user.
        //     - GET /API/chats/(chat Id): Retrieve the chat history for a specific chat.
        //     - POST /API/chats/(chat Id)/message: Send a message in a chat.
        //     - GET /API/chats/(chat Id)/message: Retrieve the messages for a specific chat.
        //     - DELETE /API/chats/(chat Id): End a chat with a friend.


        router.POST().route("/user/startChat").with(ChatController::startChat);

        router.GET().route("/user/getChats/{sender_id}").with(ChatController::getChats);
        // router.GET().route("/user/getChat/{chatId}").with(ChatController::getChat);

        // router.POST().route("/user/sendMessage").with(ChatController::sendMessage);
        
        
        // router.DELETE().route("/user/endChat/{chatId}").with(ChatController::endChat);
        



        // 4. **Data Retrieval:**
        //     - GET /API/user/(user Id): Retrieve the user's profile information.
        //     - GET /API/user/(user Id)/chats: Retrieve the list of chats for a specific user.
        //     - GET /API/user/(user Id)/friends: Retrieve the user's friend list.
    }

}

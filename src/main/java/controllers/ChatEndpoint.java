// package controllers;

// import java.io.IOException;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.Set;
// import java.util.concurrent.CopyOnWriteArraySet;

// import models.Message;

// import javax.websocket.EncodeException;
// import javax.websocket.Endpoint;
// import javax.websocket.EndpointConfig;
// import javax.websocket.MessageHandler;
// import javax.websocket.OnClose;
// import javax.websocket.OnError;
// import javax.websocket.OnMessage;
// import javax.websocket.OnOpen;
// import javax.websocket.Session;

// import ninja.Result;
// import ninja.Results;
// import ninja.params.PathParam;
// import ninja.websockets.WebSocketHandshake;

// public class ChatEndpoint extends Endpoint implements MessageHandler.Whole<String> {
//     private Session session;
//     private static Map<Long, ChatEndpoint> chatEndpoints = new HashMap<>();
//     // private static Set<ChatEndpoint> chatEndpoints = new CopyOnWriteArraySet<>();
//     private static HashMap<String, String> users = new HashMap<>();

//     public Result handshake(WebSocketHandshake handshake, @PathParam("senderId") Long senderId, @PathParam("receiverId") Long receiverId) {
//         handshake.selectProtocol("chat");
//         return Results.webSocketContinue();
//     }

//     @OnOpen
//     public void onOpen(Session session, EndpointConfig endpointConfig) {
//         // changes
//         endpointConfig.getUserProperties();
//         // change 2 lines
//         String senderId = session.getRequestParameterMap().get("senderId").get(0);
//         String receiverId = session.getRequestParameterMap().get("receiverId").get(0);
        
//         this.session = session;
//         chatEndpoints.add(senderId, this);
//         users.put(session.getId(), senderId);
//         Message message = new Message();
//         message.sessionId(session.getId());
//         message.setSenderId(senderId);
//         message.setReceiverId(receiverId);
//         message.setContent("Connected!");
//         broadcast(message);
//     }

//     @OnMessage
//     public void onMessage(Session session) throws IOException{

//         Message message;
        
//         Long senderId = message.getSenderId();
//         Long receiverId = message.getReceiverId();
//         ChatEndpoint rChatEndpoint = chatEndpoints.get(receiverId);
//         rChatEndpoint.session.getBasicRemote().sendObject(message);
//         if(rChatEndpoint != null) {
//             rChatEndpoint.session.getBasicRemote().sendObject(message);
//         }
//         broadcast(message);
//     }
    
//     @OnClose
//     public void onClose(Session session) {
//         chatEndpoints.remove(this);        
//         session.close();
//     }

//     @OnError
//     public void onError(Session session) {
//         session.close();
//     }

//     private static void broadcast(Message message) throws IOException, EncodeException {
//         chatEndpoints.forEach(endpoint -> {
//             synchronized (endpoint) {
//                 try {
//                     endpoint.session.getBasicRemote()
//                         .sendObject(message);
//                 } catch (IOException | EncodeException e) {
//                     e.printStackTrace();
//                 }
//             }
//         });       
//     }

//     @Override
//     public void onMessage(String message) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'onMessage'");
//     }

// }

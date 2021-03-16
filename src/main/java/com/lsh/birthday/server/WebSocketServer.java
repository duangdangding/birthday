package com.lsh.birthday.server;

import cn.hutool.core.util.StrUtil;
import com.lsh.birthday.config.GetHttpSessionConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ServerEndpoint(value = "/birthday/server",configurator = GetHttpSessionConfigurator.class)
public class WebSocketServer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    /** 记录当前在线连接数 */
    private static AtomicInteger onlineCount = new AtomicInteger(0);
    //线程安全的Map  
    private static ConcurrentHashMap<String,Session> webSocketMap = new ConcurrentHashMap<>();
    private HttpSession httpSession ;
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session,EndpointConfig config) {
        onlineCount.incrementAndGet(); // 在线数加1
        httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        String username = (String) httpSession.getAttribute("username");
        webSocketMap.put(username,session);
        logger.info("当前人数是" + onlineCount.get());
//        log.info("有新连接加入：{}，当前在线人数为：{}", session.getId(), onlineCount.get());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        onlineCount.decrementAndGet(); // 在线数减1
        Map<String, String> map = session.getPathParameters();
//        String username = map.get("username");
        try {
            String username = (String) httpSession.getAttribute("username");
            logger.info(username + "离开了~~~当前人数是" + onlineCount.get());
        } catch (IllegalStateException e) {
            
        }
//        log.info("有一连接关闭：{}，当前在线人数为：{}", session.getId(), onlineCount.get());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     *            客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        message = message.trim();
        if (!StrUtil.hasEmpty(message)) {
            int length = message.length();
            if (length > 100) {
                message.substring(0,100);
            }
            //        log.info("服务端收到客户端[{}]的消息:{}", session.getId(), message);
            String username = (String) httpSession.getAttribute("username");
            if (message.indexOf("cmd_") != -1) {
                String dong = message.split("cmd_")[1];
            /*
            if ("xvyuan".equals(dong)) {
                message = "正在许愿哦~~~嘘~~~等待妹子许好愿了";
            } else if ("chuilazhu".equals(dong)) {
                message = "fu~~~蜡烛灭了，接下来就开始分蛋糕了哦~";
            } else if ("fendangao".equals(dong)) {
                message = "开始分蛋糕了~~~";
            } else if ("welcome".equals(dong)) {
                if (username.indexOf("冲") != -1 || username.indexOf("妹子") != -1) {
                    message = "欢迎今天的主角" + username + "~~~";
                } else {
                    message = "欢迎" + username + "~~~";
                }
                
            } else {
                message = "大家一起祝福哦~";
            }*/
                if ("getCount".equals(dong)) {
                    message = message + "_" + onlineCount;
                }
            } else {
                message = username + "：" + message;
            }
            logger.info(message);
            for(String user:webSocketMap.keySet()) {
                this.sendMessage(message, webSocketMap.get(user));
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
//        log.error("发生错误");
        error.printStackTrace();
        this.sendMessage(error.getMessage(),session);
    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
//            log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
//            log.error("服务端发送消息给客户端失败：{}", e);
        }
    }
}

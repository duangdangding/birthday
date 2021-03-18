package com.lsh.birthday.server;

import cn.hutool.core.util.StrUtil;
import com.lsh.birthday.config.GetHttpSessionConfigurator;
import com.lsh.birthday.entry.Comment;
import com.lsh.birthday.mapper.CommentMapper;
import com.lsh.birthday.utils.RedisUtil;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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

    private static RedisUtil redisUtil_wr;
    @Autowired
    private RedisUtil redisUtil ;

    private static CommentMapper commentMapper_bk;
    @Autowired
    private CommentMapper commentMapper;

    @PostConstruct
    public void init() {
        redisUtil_wr = this.redisUtil;
        commentMapper_bk = this.commentMapper;
    }
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session,EndpointConfig config) {
        onlineCount.incrementAndGet(); // 在线数加1
        httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        String username = (String) httpSession.getAttribute("username");
        webSocketMap.put(username,session);
        if (!redisUtil_wr.hasKey("into_num")) {
            redisUtil_wr.set("into_num",0);
        }
//        入场次数
        long into_num = redisUtil_wr.incr("into_num", 1);
        logger.info("总入场次数：" + into_num);
        logger.info("当前人数是" + onlineCount.get());
//        log.info("有新连接加入：{}，当前在线人数为：{}", session.getId(), onlineCount.get());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        onlineCount.decrementAndGet(); // 在线数减1
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
        message = Jsoup.clean(message, Whitelist.relaxed()).trim();
        if (!StrUtil.hasEmpty(message)) {
            int length = message.length();
            if (length > 200) {
                message.substring(0,200);
            }
            String username = (String) httpSession.getAttribute("username");
            Comment comment = new Comment(username + "：" + message);
            Long aLong = commentMapper_bk.addComm(comment);
            logger.info("添加数据库内容:" + comment);
            //        log.info("服务端收到客户端[{}]的消息:{}", session.getId(), message);
            if (message.indexOf("cmd_") != -1) {
                String dong = message.split("cmd_")[1];
                /*if ("getCount".equals(dong)) {
                    message = message + "_" + onlineCount;
                } else */
                if (message.indexOf("cmd_zhufu_") != -1) {
                    String zfname = message.split("cmd_zhufu_")[1];
                    if (!redisUtil_wr.hasKey("send_zfnum")) {
                        redisUtil_wr.set("send_zfnum",0);
                    }
                    long send_zfnum = redisUtil_wr.incr("send_zfnum", 1);
//                    logger.info("总送出祝福：" + send_zfnum);
                    message = zfname + "送上了一份祝福！~";
                } else if (message.indexOf("cmd_welcome_") != -1) {
                    String welcome = message.split("cmd_welcome_")[1];
                    if (welcome.indexOf("冲") != -1) {
                        message = "今天的主角" + welcome + "来了~~~";
                    } else {
                        message = "欢迎" + welcome + "入场~~~";
                    }
                } else if (message.indexOf("cmd_xvyuan") != -1) {
                    message = "正在许愿哦~~~嘘~~~等待许愿完成哦~";
                }
            }
            message = username + "：" + message;
            logger.info("开始发送消息："+message);
            for(String user:webSocketMap.keySet()) {
                this.sendMessage(message, webSocketMap.get(user));
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
//        logger.error(error.getMessage());
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

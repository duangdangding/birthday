package com.lsh.birthday.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.lsh.birthday.entry.ResultDto;
import com.lsh.birthday.entry.ResultDtoManager;
import com.lsh.birthday.entry.UserMsg;
import com.lsh.birthday.service.UserMsgService;
import com.lsh.birthday.utils.ApiUtil;
import com.lsh.birthday.utils.RedisUtil;
import com.lsh.birthday.utils.ip.IPHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginCtr {

    private static final Logger logger = LoggerFactory.getLogger(LoginCtr.class);
    
    @Autowired
    private RedisUtil redisUtil;
    
    @Autowired
    private UserMsgService userMsgService;
    
    @RequestMapping("/login")
    public ResultDto<String> toLogin(String username,String userXz, HttpSession session, HttpServletRequest request) {
        String url = request.getRequestURL().toString();
//        ws://129.211.113.87:11203/birthday/server
        String ip = "localhost";
        if (!StrUtil.isEmpty(url)) {
            String s = url.split(":")[1];
            ip = s.substring(1);
        }
        String server = "ws://" + ip + ":11203/birthday/server";
        session.setAttribute("websocket_ip",server);
        if (!StrUtil.hasEmpty(username)) {
            int length = username.length();
            if (length > 20) {
                username = username.substring(0,20);
            }
//           总入场人数
            if (!redisUtil.hasKey("peo_num")) {
                redisUtil.set("peo_num",0);
            }
            long peo_num = redisUtil.incr("peo_num", 1);
            logger.info("总入场人数：" + peo_num);
        } else {
            username = "无名氏";
        }
        String ipAddr = IPHelper.getIpAddr(request);
        if ("0:0:0:0:0:0:0:1".equals(ipAddr) || "localhost".equals(ipAddr) || "127.0.0.1".equals(ipAddr)) {
            ipAddr = IPHelper.getIp();
        }
        UserMsg userMsg = new UserMsg();
        userMsg.setUserName(username);
        userMsg.setUserIp(ipAddr);
        userMsg.setUserXz(userXz);
//        http://ip.geo.iqiyi.com/cityjson?format=json&ip=61.153.252.254
        //查询Ip信息的接口，返回json 
        //		{"code":"A00000","data":{"country":"中国","province":"浙江","city":"金华","country_id":48,"province_id":10,"city_id":10007,"location_id":26010007,"isp_id":26,"isp":"电信","longitude":0,"latitude":0,"ip":"61.153.252.254"}}
        String urlApi = "http://ip.geo.iqiyi.com/cityjson";
        Map param = new HashMap();
        param.put("format","json");
        param.put("ip",ipAddr);
        /*如果限制查询次数  则存进数据库*/
        String address = ApiUtil.httpRequest(urlApi,param);
        userMsg.setUserAddress(address);
//        判断是否存在相同的ip和名字
        UserMsg msg = userMsgService.findBynameip(userMsg);
        Integer res = 0;
        if (msg == null) {
            res = userMsgService.addUser(userMsg);
            session.setAttribute("user",userMsg);
        } else {
            res = userMsgService.updateSum(msg);
            session.setAttribute("user",msg);
        }
        String result = "";
        if (res != null) {
            result = "操作成功~";
        } else {
            result = "操作失败~";
        }
        return ResultDtoManager.success(result);
    }

    /**
     * 页面有规律的获取数据 into_num,peo_num,send_zfnum
     * @return
     */
    @RequestMapping("/interval/num")
    public Map<String,Object> getOtherNum() {
        Map<String,Object> numMap = new HashMap<>();
        Integer into_num = (Integer) redisUtil.get("into_num");
        if (ObjectUtil.hasNull(into_num)) {
            into_num = 0;
        }
        numMap.put("into_num",into_num.longValue());
        Integer peo_num = (Integer)redisUtil.get("peo_num");
        if (ObjectUtil.hasNull(peo_num)) {
            peo_num = 0;
        }
        numMap.put("peo_num",peo_num.longValue());
        Integer send_zfnum = (Integer) redisUtil.get("send_zfnum");
        if (ObjectUtil.hasNull(send_zfnum)) {
            send_zfnum = 0;
        }
        numMap.put("send_zfnum",send_zfnum.longValue());
        return numMap;
    }
    
    @RequestMapping("/changBg")
    public ResultDto<Object> changeBg(UserMsg userMsg,HttpSession session) {
        int i = userMsgService.updateBg(userMsg);
        UserMsg user = (UserMsg) session.getAttribute("user");
        UserMsg userMsg1 = userMsgService.findBynameip(user);
        session.setAttribute("user",userMsg1);
        return ResultDtoManager.success(i);
    }
}

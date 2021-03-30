package com.lsh.birthday.service.impl;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lsh.birthday.service.DateUtil;
import com.lsh.birthday.service.RedisService;
import com.lsh.birthday.utils.ApiUtil;
import com.lsh.birthday.utils.PinYinUtils;
import com.lsh.birthday.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RedisServiceImpl implements RedisService {
    public static String SP = "<p>";
    public static String EP = "</p>";

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void addBannadWords(String words) {
        redisUtil.sSet("bannaword",words);
    }

    @Override
    public Set<Object> getAllbanad() {
        Set<Object> bannaword = redisUtil.sGet("bannaword");
        return bannaword;
    }

    @Override
    public Set<Object> getAllRandName() {
        Set<Object> randomName = redisUtil.sGet("randomName");
        return randomName;
    }

    @Override
    public String getRandomName() {
        String randomName = (String) redisUtil.sGetRan("randomName");
        return randomName;
    }

    @Override
    public long setRandomName(String username) {
        long l = redisUtil.sSet("randomName", username);
        return l;
    }

    @Override
    public int findBannadWords(String words) {
        int res = 0;
        if (!words.isEmpty()) {
            String replace = words.replace(" ", "").replace(",", "").replace("、", "")
                    .replace("，", "").replace("。", "").replace("/", "")
                    .replace("\\", "").replace("|", "").replace("*", "")
                    .replace("%", "").replace("&", "").replace("#", "")
                    .replace("^", "").replace("@", "").replace("~", "");
            Set<Object> bannaword = redisUtil.sGet("bannaword");
            Iterator<Object> iterator = bannaword.iterator();
            while (iterator.hasNext()) {
                String next = (String) iterator.next();
                if (replace.contains(next)) {
                    res = 1;
                    break;
                }
            }
        }
        return res;
    }

    @Override
    public String getXzys(String xz) {
        String s = PinYinUtils.toFirstPinYin(xz);
        String result = "";
        if (redisUtil.hasKey(s)) {
            result = (String) redisUtil.get(s);
        } else {
//            consName	string	是	星座名称，如:双鱼座
//            type	string	是	运势类型：today,tomorrow,week,month,year
//            http://web.juhe.cn:8080/constellation/getAll?consName=双子座&type=today&key=6622151a9040b5bab11d22349e6fc511
//            {"date":20210330,"name":"双子座","QFriend":"双鱼座","color":"银灰色","datetime":"2021年03月30日","health":"75","love":"70",
//                    "work":"85","money":"60","number":14,
//                    "summary":"今天双子座会思考关于跳槽的事宜，职场需要不断的发展和前行，但同时也要做好万全的准备，更加不要让同事知道你的想法。晚间外出时可能会着凉，多加一件外套。",
//                    "all":"75","resultcode":"200","error_code":0}
            String requestUrl = "http://web.juhe.cn:8080/constellation/getAll";
            //params用于存储要请求的参数
            Map params = new HashMap();
            //showapi_appid的值，把###替换成你的appid
            params.put("key","6622151a9040b5bab11d22349e6fc511");
            //我们请求的字符串
            params.put("type","today");
            //数字签名，###填你的数字签名，可以在你的个人中心看到
            params.put("consName",xz);
            //调用httpRequest方法，这个方法主要用于请求地址，并加上请求参数
            result = ApiUtil.httpRequest(requestUrl,params);
            long time = DateUtil.subTodayDate();
            redisUtil.set(s,result,time);
        }
        JSONObject jsonObject = new JSONObject(result);
        Object name = jsonObject.get("name");
        Object QFriend = jsonObject.get("QFriend");
        Object color = jsonObject.get("color");
        Object datetime = jsonObject.get("datetime");
        Object health = jsonObject.get("health");
        Object love = jsonObject.get("love");
        Object work = jsonObject.get("work");
        Object money = jsonObject.get("money");
        Object number = jsonObject.get("number");
        Object summary = jsonObject.get("summary");
        Object all = jsonObject.get("all");
        StringBuffer sb = new StringBuffer();
        sb.append(SP).append("今日日期：").append(datetime).append(EP).append(SP).append("健康指数：").append(health).append(EP).append(SP).append("恋爱指数：").append(love).append(EP)
                .append(SP).append("事业指数：").append(work).append(EP).append(SP).append("财富指数：").append(money).append(EP)
                .append(SP).append("综合指数：").append(all).append(EP).append(SP).append("幸运数字：").append(number).append(EP)
                .append(SP).append("幸运颜色：").append(color).append(EP).append(SP).append("星座速配：").append(QFriend).append(EP)
                .append(SP).append("详情概括：").append(summary).append(EP).append("<span style='display:none' id='xz_name'>").append(name).append(EP);
        return sb.toString();
    }

    public static void main(String[] args) {
        String result = "{\"date\":20210330,\"name\":\"双子座\",\"QFriend\":\"双鱼座\",\"color\":\"银灰色\",\"datetime\":\"2021年03月30日\",\"health\":\"75\",\"love\":\"70\",\"work\":\"85\",\"money\":\"60\",\"number\":14,\"summary\":\"今天双子座会思考关于跳槽的事宜，职场需要不断的发展和前行，但同时也要做好万全的准备，更加不要让同事知道你的想法。晚间外出时可能会着凉，多加一件外套。\",\"all\":\"75\",\"resultcode\":\"200\",\"error_code\":0}";
        JSONObject jsonObject = new JSONObject(result);
        Object date = jsonObject.get("date");
        System.out.println(date);
    }
}

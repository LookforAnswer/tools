package com.qxy.tools.controller;

import com.alibaba.fastjson.JSONObject;
import com.qxy.tools.common.ToolsConstants;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Description: 类信息描述
 * @Author: qinxiaoyun001@lianjia.com
 * @Date: 2019/4/16 11:16 AM
 * @Version: 1.0
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("page")
    public String page() {
        return "index";
    }

    private final static String SPLIT = "&";

    /**
     * 按照规则加密,该方法不能随意变更
     *
     * @param appkey
     * @param token
     * @param timestamp
     * @return
     */
    public static String encode(String appkey, String token, long timestamp) {
        StringBuilder codeBuilder = new StringBuilder();

        codeBuilder.append(appkey);
        codeBuilder.append(SPLIT);

        codeBuilder.append(token);
        codeBuilder.append(SPLIT);

        codeBuilder.append(timestamp);

        return DigestUtils.md5Hex(codeBuilder.toString());
    }

    @GetMapping("/getAuthByAppkeyAndSecret")
    @ResponseBody
    public JSONObject getAuthByAppkeyAndSecret(@RequestParam("appKey") String appKey,
                                               @RequestParam("secret") String secret,
                                               @RequestParam(value = "timestamp",required = false) Long timestamp) {
        JSONObject result = new JSONObject();
        result.put("appKey", appKey);
        if (timestamp == null){
            timestamp = System.currentTimeMillis();
        }
        result.put("timestamp", timestamp);
        result.put("signCode", encode(appKey, secret, timestamp));
        result.put("traceId", UUID.randomUUID().toString().replaceAll(ToolsConstants.HYPHEN, ToolsConstants.EMPYT_STRING));
        return result;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
}

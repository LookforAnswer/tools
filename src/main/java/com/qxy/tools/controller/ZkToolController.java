package com.qxy.tools.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description: 类信息描述
 * @Author: qinxiaoyun001@lianjia.com
 * @Date: 2019/8/21 12:11 AM
 * @Version: 1.0
 */
@RequestMapping("/zktool")
@RestController
public class ZkToolController {

    private static final BASE64Encoder encoder = new BASE64Encoder();


    @GetMapping("/getDigestPassword")
    public String getDigestPassword(@RequestParam("info") String info) throws NoSuchAlgorithmException {
        String parts[] = info.split(":", 2);
        byte digest[] = MessageDigest.getInstance("SHA1").digest(info.getBytes());
        return parts[0] + ":" + encoder.encode(digest);
    }


}

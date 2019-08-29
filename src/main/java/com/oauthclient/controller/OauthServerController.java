package com.oauthclient.controller;

import com.oauthclient.annotation.ApiToken;
import com.oauthclient.entity.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
public class OauthServerController {
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("/userList")
    @ApiToken("user-list")
    public ApiResult getUserList(){
        ValueOperations valueOperations=redisTemplate.opsForValue();
        String code="1";
        String message="";
        HashMap<Object,Object> a=null;
        long total=0;
        String data="code";
        return  ApiResult.buildSuccessApiResult(code,message,redisTemplate.hasKey("name")?"":redisTemplate.opsForValue().get("name").toString(),total);
    }

}

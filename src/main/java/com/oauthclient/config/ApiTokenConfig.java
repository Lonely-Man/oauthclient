package com.oauthclient.config;

import com.oauthclient.annotation.ApiToken;
import com.oauthclient.entity.ApiResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
public class ApiTokenConfig {
    @Autowired
    private RedisTemplate redisTemplate;
    private static final String type="token";
    @Autowired
    private HttpServletRequest request;
    @Around("@annotation(com.oauthclient.annotation.ApiToken)")
    public ApiResult tokenCheck(ProceedingJoinPoint joinPoint ) throws Throwable{
        String token=request.getHeader(type);
        if(token==null){
            return ApiResult.buildFailApiResult(null,1,"1","无令牌");
        }
      if(!redisTemplate.hasKey(token)){
          return ApiResult.buildFailApiResult(null,1,"1","失效令牌");
      }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        ApiToken apiToken=signature.getMethod().getAnnotation(ApiToken.class);
        String value=apiToken.value();
        //todo 业务判断


        return (ApiResult)joinPoint.proceed();
    }
}

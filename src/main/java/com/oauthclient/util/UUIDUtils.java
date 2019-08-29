package com.oauthclient.util;

import java.util.UUID;

public class UUIDUtils {
    /**
     * 生成uuid
     * @return
     */
    public  static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成clientId
     * @return
     */
    public  static String createClientId(){
        return UUID.randomUUID().toString();

    }

    public static void main(String[] args) {
        Boolean t=null;
        if(t){
            System.out.println("2");
        }
    }
}

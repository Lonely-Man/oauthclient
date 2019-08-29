package com.oauthclient.util;

import java.util.Base64;

public class Base64Utils {
    private final static Base64.Decoder decoder = Base64.getDecoder();
    private final static Base64.Encoder encoder = Base64.getEncoder();
    private final static String code="UTF-8";

    /**
     * 普通加密
     * @param jsonData
     * @return
     * @throws Exception
     */
   public static String encoderData(String jsonData) throws Exception{
        byte[] textByte = jsonData.getBytes(code);
        return encoder.encodeToString(textByte);
   }

    /**
     * 普通解密
     * @param encoderData
     * @return
     * @throws Exception
     */
    public static String decoderData(String encoderData) throws Exception{
        return new String(decoder.decode(encoderData), code);
    }
    /**
     * 加盐加密
     * @param jsonData
     * @return
     * @throws Exception
     */
    public static String encoderDataBySalt(String jsonData,String salt) throws Exception{
        String saltData=encoder.encodeToString(salt.getBytes(code));
        byte[] textByte = jsonData.getBytes(code);
        return saltData+encoder.encodeToString(textByte)+saltData;
    }

    /**
     * 加盐解密
     * @param encoderData
     * @return
     * @throws Exception
     */
    public static String decoderDataBySalt(String encoderData, String salt) throws Exception{
        String saltData=encoder.encodeToString(salt.getBytes(code));
        String str[]=encoderData.split(saltData);
        encoderData=str[1];
        String data=new String(decoder.decode(encoderData), code);
        return  data;
    }

    public static void main(String[] args)throws Exception {
        String data="{\"id\":\"123456\",\"name\":\"堕落\"}";
        String salt="username5555555";
        String encoderDataBySalt=encoderDataBySalt(data,salt);
        String decoderDataBySalt=decoderDataBySalt(encoderDataBySalt,salt);
        System.out.println(encoderDataBySalt);
        System.out.println(decoderDataBySalt);

    }
}

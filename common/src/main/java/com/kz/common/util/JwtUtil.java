package com.kz.common.util;


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtil {
    // JWT密钥
    private final static String key = "kz12306";
    /**
     * 生成JWT Token
     * @param id
     * @param mobile
     * @return
     */
    public static String createToken(long id, String mobile) {
        DateTime now = DateTime.now();
        DateTime offsetTime = now.offsetNew(DateField.HOUR, 24);
        Map<String, Object> payLoad = new HashMap<>();
        //签发时间
        payLoad.put(JWTPayload.ISSUED_AT, now);
        //过期时间
        payLoad.put(JWTPayload.EXPIRES_AT, offsetTime);
        //生效时间
        payLoad.put(JWTPayload.NOT_BEFORE, now);
        //用户信息
        payLoad.put("id", id);
        payLoad.put("mobile", mobile);
        //生成token
        String token = JWTUtil.createToken(payLoad, key.getBytes());
        log.info("生成的token:{}", token);
        return token;
    }

    public static JSONObject getJSONObject(String token) {
        // 解析JWT TokensetKey 是为了设置用于验证 JWT 签名的密钥。
        //解析 JWT 时，如果不设置密钥，只能读取 payload，无法校验 token 是否被篡改。
        //设置密钥后，jwt.validate(0) 或 jwt.verify() 才能校验签名是否合法，保证 token 的安全性。
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
        JSONObject payloads = jwt.getPayloads();
        payloads.remove(JWTPayload.ISSUED_AT);
        payloads.remove(JWTPayload.EXPIRES_AT);
        payloads.remove(JWTPayload.NOT_BEFORE);
        log.info("token解析结果:{}", payloads);
        return payloads;
    }
    public static boolean validate(String token) {
        // 解析JWT Token
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
        // 校验签名
        boolean isValid = jwt.validate(0);
        if (isValid) {
            log.info("token校验通过");
            JSONObject payloads = getJSONObject(token);
        } else {
            log.error("token校验失败");
        }
        return isValid;
    }
    public static void main(String[] args) {
        createToken(1L,"12323");
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE3NDk3MzU3MzgsIm1vYmlsZSI6IjEyMzIzIiwiaWQiOjEsImV4cCI6MTc0OTgyMjEzOCwiaWF0IjoxNzQ5NzM1NzM4fQ.N1W215F2uNppBPlsZqmdj7z1gIVo-4iiUTh53Bkj7ZI";
        validate(token);
    }
}

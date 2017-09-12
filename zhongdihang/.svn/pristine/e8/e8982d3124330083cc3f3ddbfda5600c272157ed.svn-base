package com.zhongdihang.bankdispatch.core.security;

import com.zhongdihang.bankdispatch.core.redis.RedisClient;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil implements Serializable {

    @Autowired
    protected RedisClient redisClient;

    private static final long serialVersionUID = -3301605591108950415L;

    private static final String CLAIM_KEY_USERNAME = "sub";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Integer expiration;

    @Value("${spring.redis.token-prefix}")
    private String tokenPrefix;


    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/15 14:38
     * @MenthodName: getUsernameFromToken
     * @Description:解析token
     * @param token token串
     * @Return: java.lang.String
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/15 14:39
     * @MenthodName: getClaimsFromToken
     * @Description: 获取token中的信息
     * @param token token串
     * @Return: io.jsonwebtoken.Claims
     */
    private Claims getClaimsFromToken(String token) {

        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }


    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/15 14:39
     * @MenthodName: generateToken
     * @Description: 生成token串
     * @param userDetails 用户实体
     * @Return: java.lang.String
     */
    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        return generateToken(userDetails.getUsername(),claims);
    }

    private String generateToken(String userName,Map<String, Object> claims) {
        String token= Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        redisClient.set(tokenPrefix+userName,token);
        redisClient.expire(tokenPrefix+userName,expiration);
        return token;
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/15 14:40
     * @MenthodName: validateToken
     * @Description:验证token串
     * @param token
     * @param username
     * @Return: java.lang.Boolean
     */
    public Boolean validateToken(String token, String username) {

        if ( StringUtils.isEmpty(username)){
            return false;
        }
        String redisToken=redisClient.get(tokenPrefix+username);
        if (redisToken==null)return false;
        boolean result=redisToken.equals(token);
        if (result){
            redisClient.expire(tokenPrefix+username,expiration);
        }
        return token.equals(redisToken);
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/15 14:40
     * @MenthodName: validateToken
     * @Description:删除token串
     * @param username
     * @Return: java.lang.Boolean
     */
    public void removeToken(String username) {
        redisClient.del(tokenPrefix+username);
    }
}


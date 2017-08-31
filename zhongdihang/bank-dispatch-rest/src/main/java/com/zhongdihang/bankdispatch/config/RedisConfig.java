package com.zhongdihang.bankdispatch.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/15 12:31
 * @Modified By：
 * @Version: V1.0.0
 */
@Configuration
@EnableAutoConfiguration
public class RedisConfig {
    private static Logger logger = Logger.getLogger(RedisConfig.class);
    @Value("${spring.redis.host}")
    private String hostName;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public JedisPoolConfig getRedisConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(500);
        config.setMaxIdle(10);
        config.setMaxWait(1000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        config.setTestWhileIdle(true);
        return config;
    }

    @Bean
    public JedisShardInfo getJedisShardInfo(){
        JedisShardInfo jedisShardInfo=new JedisShardInfo(hostName,port);
//        jedisShardInfo.setPassword(password);
        return jedisShardInfo;
    }

    @Bean
    public ShardedJedisPool getShardedJedisPool(){
        List<JedisShardInfo> jedisShardInfos=new ArrayList<>();
        jedisShardInfos.add(getJedisShardInfo());
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(getRedisConfig(),jedisShardInfos);
        logger.info("init JedisPool ...");
        return shardedJedisPool;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

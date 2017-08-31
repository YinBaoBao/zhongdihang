package com.zhongdihang.bankdispatch.core.redis;

import redis.clients.jedis.ShardedJedis;

/**
 * @Author:Arthur Han
 * @Description:
 * @CreateDate:2017/3/318:15
 * @Modified By:
 */
public interface RedisDataSource {
    ShardedJedis getRedisClient();
    void returnResource(ShardedJedis shardedJedis);
    void returnResource(ShardedJedis shardedJedis, boolean broken);
}

package com.zhongdihang.bankdispatch.core.redis;

import redis.clients.jedis.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author:Arthur Han
 * @Description:
 * @CreateDate:2017/3/1410:33
 * @Modified By:
 */
public interface RedisClient {
    void disconnect();

    String set(String key, String value);

    String get(String key);

    Boolean exists(String key);

    String type(String key);

    Long expire(String key, int seconds);

    Long expireAt(String key, long unixTime);

    Long ttl(String key);

    boolean setbit(String key, long offset, boolean value);

    boolean getbit(String key, long offset);

    long setrange(String key, long offset, String value);

    String getrange(String key, long startOffset, long endOffset);

    String getSet(String key, String value);

    Long setnx(String key, String value);

    String setex(String key, int seconds, String value);

    Long decrBy(String key, long integer);

    Long decr(String key);

    Long incrBy(String key, long integer);

    Long incr(String key);

    Long append(String key, String value);

    String substr(String key, int start, int end);

    Long hset(String key, String field, String value);

    String hget(String key, String field);

    Long hsetnx(String key, String field, String value);

    String hmset(String key, Map<String, String> hash);

    List<String> hmget(String key, String... fields);

    Long hincrBy(String key, String field, long value);

    Boolean hexists(String key, String field);

    Long del(String key);

    Long hdel(String key, String field);

    Long hlen(String key);

    Set<String> hkeys(String key);

    List<String> hvals(String key);

    Map<String, String> hgetAll(String key);

    // ================list ====== l表示 list或 left, r表示right====================
    Long rpush(String key, String string);

    Long lpush(String key, String string);

    Long llen(String key);

    List<String> lrange(String key, long start, long end);

    String ltrim(String key, long start, long end);

    String lindex(String key, long index);

    String lset(String key, long index, String value);

    Long lrem(String key, long count, String value);

    String lpop(String key);

    String rpop(String key);

    //return 1 add a not exist value ,
    //return 0 add a exist value
    Long sadd(String key, String member);

    Set<String> smembers(String key);

    Long srem(String key, String member);

    String spop(String key);

    Long scard(String key);

    Boolean sismember(String key, String member);

    String srandmember(String key);

    Long zadd(String key, double score, String member);

    Set<String> zrange(String key, int start, int end);

    Long zrem(String key, String member);

    Double zincrby(String key, double score, String member);

    Long zrank(String key, String member);

    Long zrevrank(String key, String member);

    Set<String> zrevrange(String key, int start, int end);

    Set<Tuple> zrangeWithScores(String key, int start, int end);

    Set<Tuple> zrevrangeWithScores(String key, int start, int end);

    Long zcard(String key);

    Double zscore(String key, String member);

    List<String> sort(String key);

    List<String> sort(String key, SortingParams sortingParameters);

    Long zcount(String key, double min, double max);

    Set<String> zrangeByScore(String key, double min, double max);

    Set<String> zrevrangeByScore(String key, double max, double min);

    Set<String> zrangeByScore(String key, double min, double max, int offset, int count);

    Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count);

    Set<Tuple> zrangeByScoreWithScores(String key, double min, double max);

    Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min);

    Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count);

    Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count);

    Long zremrangeByRank(String key, int start, int end);

    Long zremrangeByScore(String key, double start, double end);

    Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value);

    String set(byte[] key, byte[] value);

    byte[] get(byte[] key);

    Boolean exists(byte[] key);

    String type(byte[] key);

    Long expire(byte[] key, int seconds);

    Long expireAt(byte[] key, long unixTime);

    Long ttl(byte[] key);

    byte[] getSet(byte[] key, byte[] value);

    Long setnx(byte[] key, byte[] value);

    String setex(byte[] key, int seconds, byte[] value);

    Long decrBy(byte[] key, long integer);

    Long decr(byte[] key);

    Long incrBy(byte[] key, long integer);

    Long incr(byte[] key);

    Long append(byte[] key, byte[] value);

    byte[] substr(byte[] key, int start, int end);

    Long hset(byte[] key, byte[] field, byte[] value);

    byte[] hget(byte[] key, byte[] field);

    Long hsetnx(byte[] key, byte[] field, byte[] value);

    String hmset(byte[] key, Map<byte[], byte[]> hash);

    List<byte[]> hmget(byte[] key, byte[]... fields);

    Long hincrBy(byte[] key, byte[] field, long value);

    Boolean hexists(byte[] key, byte[] field);

    Long hdel(byte[] key, byte[] field);

    Long hlen(byte[] key);

    Set<byte[]> hkeys(byte[] key);

    Collection<byte[]> hvals(byte[] key);

    Map<byte[], byte[]> hgetAll(byte[] key);

    Long rpush(byte[] key, byte[] string);

    Long lpush(byte[] key, byte[] string);

    Long llen(byte[] key);

    List<byte[]> lrange(byte[] key, int start, int end);

    String ltrim(byte[] key, int start, int end);

    byte[] lindex(byte[] key, int index);

    String lset(byte[] key, int index, byte[] value);

    Long lrem(byte[] key, int count, byte[] value);

    byte[] lpop(byte[] key);

    byte[] rpop(byte[] key);

    Long sadd(byte[] key, byte[] member);

    Set<byte[]> smembers(byte[] key);

    Long srem(byte[] key, byte[] member);

    byte[] spop(byte[] key);

    Long scard(byte[] key);

    Boolean sismember(byte[] key, byte[] member);

    byte[] srandmember(byte[] key);

    Long zadd(byte[] key, double score, byte[] member);

    Set<byte[]> zrange(byte[] key, int start, int end);

    Long zrem(byte[] key, byte[] member);

    Double zincrby(byte[] key, double score, byte[] member);

    Long zrank(byte[] key, byte[] member);

    Long zrevrank(byte[] key, byte[] member);

    Set<byte[]> zrevrange(byte[] key, int start, int end);

    Set<Tuple> zrangeWithScores(byte[] key, int start, int end);

    Set<Tuple> zrevrangeWithScores(byte[] key, int start, int end);

    Long zcard(byte[] key);

    Double zscore(byte[] key, byte[] member);

    List<byte[]> sort(byte[] key);

    List<byte[]> sort(byte[] key, SortingParams sortingParameters);

    Long zcount(byte[] key, double min, double max);

    Set<byte[]> zrangeByScore(byte[] key, double min, double max);

    Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count);

    Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max);

    Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count);

    Set<byte[]> zrevrangeByScore(byte[] key, double max, double min);

    Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count);

    Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min);

    Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count);

    Long zremrangeByRank(byte[] key, int start, int end);

    Long zremrangeByScore(byte[] key, double start, double end);

    Long linsert(byte[] key, BinaryClient.LIST_POSITION where, byte[] pivot, byte[] value);

    List<Object> pipelined(ShardedJedisPipeline shardedJedisPipeline);

    Jedis getShard(byte[] key);

    Jedis getShard(String key);

    JedisShardInfo getShardInfo(byte[] key);

    JedisShardInfo getShardInfo(String key);

    String getKeyTag(String key);

    Collection<JedisShardInfo> getAllShardInfo();

    Collection<Jedis> getAllShards();
}

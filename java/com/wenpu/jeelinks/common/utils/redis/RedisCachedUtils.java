package com.wenpu.jeelinks.common.utils.redis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.zip.CRC32;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import java.util.Set;  

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.beans.factory.annotation.Qualifier;  
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;  

  
import redis.clients.jedis.Jedis;  



/** 
 * 封装redis 缓存服务器服务接口 
 * @author hk 
 * 
 * 2012-12-16 上午3:09:18 
 */  
@Component
public class RedisCachedUtils{
	
	
		private static String keyPrefix="com.links.redis.";
		  //操作redis客户端  
	    private static Jedis jedis;  
	    @Resource  
	    @Qualifier("jedisConnectionFactory")  
	    private JedisConnectionFactory jedisConnectionFactory;  
	    @Resource  
	    private StringRedisTemplate stringRedisTemplate;  
	    @Resource  
	    private RedisTemplate<String,Object> redisTemplate;  
		private int dbNum = 16; 
		public static long SAVE_EXPRIE_DAY=86400;//数据有效期，一天
		public static long SAVE_EXPRIE_WEEK=604800;//数据有效期，一星期
		public static long SAVE_EXPRIE_MONTH=2592000;//数据有效期，30天
	    /** 
	     * 获取一个jedis 客户端 
	     * @return 
	     */  
	    private Jedis getJedis(){  
	        if(jedis == null){  
//	            return jedisConnectionFactory.getShardInfo().createResource();  
	        	 JedisConnection jedisConnection = jedisConnectionFactory.getConnection();  
	             jedis = jedisConnection.getNativeConnection();  
	        }  
	        return jedis;  
	    }  
	    private RedisCachedUtils (){  
	    }  
	    public void add(String key,Object value){
	    	add(key,0, value);
	    }
	    //保存数据
	    public void add(String key,final long exprieInSecond,Object value){
	    	del("testname");//需要先删除，在添加缓存，假如同一个key存储了两个不同的数据类型，会报错
	    	key=keyPrefix+key;
	    	if(exprieInSecond==0){
	    		redisTemplate.opsForValue().set(key,value);
	    	}else{
	    		redisTemplate.opsForValue().set(key,value,exprieInSecond,TimeUnit.SECONDS);
	    	}
	    }
	    public Object get(String key){
	    	key=keyPrefix+key;
	    	return (Object) redisTemplate.opsForValue().get(key);
	    }
	   /* 
	    //保存字符串数据
	    public void add(String key,String value){
			add(key,0, value);
	    }
	    
	    public void add(String key,final long exprieInSecond,String value){
	    	if(exprieInSecond==0){
	    		redisTemplate.opsForValue().set(key,value);
	    	}else{
	    		redisTemplate.opsForValue().set(key,value,exprieInSecond,TimeUnit.SECONDS);
	    	}
	    }
	    public String getString(final String key){
	    	return (String) redisTemplate.opsForValue().get(key);
	    }
	    
	    //保存List数据
	    public void add(String key,List value){
	    	add(key,0, value);
	    }
	    public void add(String key,final long exprieInSecond,List value){
    		redisTemplate.opsForList().leftPush(key, value);
    		if(exprieInSecond!=0){
    			redisTemplate.expire(key, exprieInSecond, TimeUnit.SECONDS);
    		}
	    }
	    
	    public List getList(final String key){
	    	return (List) redisTemplate.opsForList().leftPop(key);
	    }
	    //保存Set数据
	    public void add(String key,Set value){
	    	add(key,0, value);
	    }
	    public void add(String key,final long exprieInSecond,Set value){
	    	redisTemplate.opsForSet().add(key, value);
	    	if(exprieInSecond!=0){
	    		redisTemplate.expire(key, exprieInSecond, TimeUnit.SECONDS);
	    	}
	    }
	    
	    public Set getSet(final String key){
	    	return (Set) redisTemplate.opsForSet().pop(key);
	    }
	   
	    //保存Map数据
	    public void add(String key,Map value){
	    	add(key,0, value);
	    }
	    public void add(String key,final long exprieInSecond,Map value){
	    	redisTemplate.opsForHash().putAll(key, value);
	    	if(exprieInSecond!=0){
	    		redisTemplate.expire(key, exprieInSecond, TimeUnit.SECONDS);
	    	}
	    }
	    
	    public Map getMap(final String key){
	    	return  redisTemplate.opsForHash().entries(key);
	    }*/
	    
	    
	    //删除数据
	    public void del(String key){
	    	 redisTemplate.delete(key);
	    }
	    /** 
	     * 检查是否连接成功 
	     * @return 
	     */  
	    public String ping(){  
	        return this.getJedis().ping();  
	    }  

}

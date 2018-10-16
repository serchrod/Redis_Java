
package com.redis;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;
import com.lambdaworks.redis.RedisURI;
import java.util.Map;


//Redis class wrapper from lettuce. simple and easy way to use
public class Redis {
   private static Redis redis=null;
   private  RedisClient redisClient=null;
   private RedisConnection<String, String> connection=null;
    
    //default configurations of Redis client
    public Redis(){
     RedisClient redisClient = new RedisClient(
     RedisURI.create("redis://localhost/"));
     this.redisClient=redisClient;
     RedisConnection<String, String> connection = redisClient.connect();
     this.connection=connection;
    }
    
    public Redis(String host){
        RedisClient redisClient = new RedisClient(
        RedisURI.create("redis://"+host+"/"));
        this.redisClient=redisClient;
        RedisConnection<String, String> connection = redisClient.connect();
        this.connection=connection;
    }
    
    public Redis(String host,String password,int port){
        RedisClient redisClient = new RedisClient(
        RedisURI.create("redis://"+password+"@"+host+":"+port));
        this.redisClient=redisClient;
        RedisConnection<String, String> connection = redisClient.connect();
        this.connection=connection;
    }
    
    //singleton method to use a object if you need
    public static Redis getInstance() {
        if (redis== null){
            try {
                redis = new Redis();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return redis;
    }
    
    public boolean hset(String key,String field,String value){
          boolean success=false;
          success=connection.hset(key, field,value);
          return success;
    }
    
    public String hget(String key,String field ){
        String result="";
        result=connection.hget(key, field);
        return result;
    }
    
    public Map<String,String> hgetall(String key){
       Map<String,String> result=null;
       result=connection.hgetall(key);
       return result;
    }
    
    public boolean hexist(String key,String value){
        boolean result=false;
        result=connection.hexists(key, value);
        return result;
    }
    
    public void makeBackup(String key){
        connection.bgsave();
    }
    
    public void Delete(String key){
        connection.del(key);
    }
    
    public void safeDelete(String key){
        connection.bgsave();
        connection.del(key);
    }
    
    public void closeConnection(){
        connection.close();
        redisClient.shutdown();
    }
    
    
}

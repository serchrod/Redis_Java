
package com.redis;


/**
 *
 * @author serch
 */
public class Main {
    
    public static void main (String [] args){
     Redis redis= new Redis("localhost");
     redis.hset("new", "test","1");
     System.out.println(redis.hget("new","test"));
     System.out.println(redis.hgetall("new"));
     System.out.println(redis.hexist("new", "test"));
     redis.closeConnection();
  }
     
    
    
}

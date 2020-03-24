package com.aistar;

import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

public class JedisDemo {
    public static void main(String[] args) {
        Jedis jedis1 = new Jedis();

        //1.字符串类型
        //存储
        jedis1.set("username","mike");
        //获取
        String username = jedis1.get("username");
        System.out.println("用户名："+username);

        //可以使用setex()方法存储可以指定过期时间的 key value,4 表示4秒后自动删除该键值对
        jedis1.setex("password",4,"123");
        String password = jedis1.get("password");
        System.out.println("密码1："+password);
        //删除

        System.out.println("密码："+password);
        jedis1.close();

        //2.hash map格式
        //获取连接
        Jedis jedis2 = new Jedis();
        //存储hash
        jedis2.hset("customer","name","lisa");
        jedis2.hset("customer","age","20");
        jedis2.hset("customer","gender","female");
        //获取
        String name = jedis2.hget("customer","name");
        System.out.println("姓名:"+name);
        //删除age ======> 没成功
        Long age = jedis2.hdel("customer","age");
        System.out.println("年龄："+age);//1
        //获取hash的所有map中的数据
        Map<String,String> customer = jedis2.hgetAll("customer");
        Set<String> keySet = customer.keySet();
        for(String key:keySet){
            String value = customer.get(key);
            System.out.println(key+":"+value);
        }
        jedis2.close();

        //3. 无序存储
        Jedis jedis3 = new Jedis();
        jedis3.sadd("myset","java","php","c++");
        // set 获取
        Set<String> myset = jedis3.smembers("myset");
        System.out.println(myset);
        //删除所有
        myset.clear();
        System.out.println(myset);
        jedis3.close();


        //4.有序集合类型 sortedset：不允许重复元素，且元素有顺序 (从小到大)
        //1. 获取连接
        Jedis jedis4 = new Jedis();//如果使用空参构造，默认值 "localhost",6379端口
        //2. 操作
        // sortedset 存储
        jedis4.zadd("mysortedset",58,"亚瑟");
        jedis4.zadd("mysortedset",30,"后裔");
        jedis4.zadd("mysortedset",55,"孙悟空");

        // sortedset 获取
        Set<String> mysortedset = jedis4.zrange("mysortedset", 0, -1);

        System.out.println(mysortedset);
        //3. 关闭连接
        jedis4.close();

    }
}

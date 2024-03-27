### 前端

react

### 后端

springboot，mybatisplus，redis，mysql，
rabbitmq，
> https://blog.csdn.net/qq_35387940/article/details/100514134

Slf4j

redis实现思路：https://www.cnblogs.com/qlqwjy/p/9784956.html

前端消息发送到MQ入口，经过消息队列之后进入后端服务

springboot

mybatis

redis：
教程：
> https://juejin.cn/post/7076244567569203208
> 
> https://xie.infoq.cn/article/c8a917dd5773a3ff1d9b408af

### 系统流程

> 前端发送消息 -> 后端websocket接收到消息 
>
> -> 消息存入消息队列 -> 消费者1：将消息存入redis（1. 定期删除存储的数据。2. 用户上线使用websocket推送当前聊天记录）
>
> ​                                  -> 消费者2：将消息存入mysql（用于消息记录查询服务）
>
> -> 使用websocket转发消息

### 数据库设计

```
# redis
# 聊天室消息存储
# id
room_id
# 存储类型 sorted set
# 成员格式
{
	username: ...,
	toname: ...,
	message: ...,
	time: ...   # 作为排序score
}
# mysql
群聊表名: room_id id为创建者的用户id
用户之间聊天使用：room_id1_id2 id为两个用户的id，两个id按照大小顺序排列
{
    username:
    toName:
    message:
    time: 
}
房间号存储 room_list
{
    room_id,
    room_id1_id2
}

```


```
拦截器和过滤器区别？
https://zhuanlan.zhihu.com/p/484289805

事务管理？失败如何回退？

OPTIONS请求是什么?

跨域原理，如何解决？

spingboot如何注入静态成员变量？
```


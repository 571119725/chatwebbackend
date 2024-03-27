package com.indi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indi.dao.RoomListDao;
import com.indi.dao.TableDao;
import com.indi.domain.Message;
import com.indi.domain.User;
import com.indi.mq.DirectPublisher;
import com.indi.service.MessageService;
import com.indi.service.UserService;
import com.indi.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChatwebApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    TableDao tableDao;
    @Autowired
    DirectPublisher directPublisher;
    @Autowired
    RoomListDao roomListDao;
    @Autowired
    MessageService messageService;
    @Test
    void testLogin() {
        User user = new User(11, "测试", "test");
        System.out.println(userService.login(user));
    }
//    @Test
//    void testRedis() {
//        redisUtils.addMessage("test", "room_1", 21313L);
//    }
//    @Test
//    void testGetAllRedis(){
//        String str = redisUtils.getHistoryMessageJson();
//        System.out.println(str);
////        System.out.println(set.toString());
//    }
    @Test
    void testRabbitmq() {
        Message message = new Message();
        message.setMessage("helloworld");
        message.setFromName("caozheng");
        message.setToName("room_sfasd");
        message.setTime("202403221731");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String str = objectMapper.writeValueAsString(message);
            directPublisher.sendMessage(str);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void testCreateTable(){
        tableDao.createTable("room_1");
    }
    @Test
    void testRoomList(){
        System.out.println(roomListDao.selectList(null));
    }
    @Test
    void testMessageService(){
        Message message = new Message();
        message.setMessage("helloworld");
        message.setFromName("caozheng");
        message.setToName("room_1");
        message.setTime("202403221731");
        messageService.insertMessage("room_1", message);
    }
}

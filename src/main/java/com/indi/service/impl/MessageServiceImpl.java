package com.indi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.indi.dao.RoomListDao;
import com.indi.dao.TableDao;
import com.indi.domain.Message;
import com.indi.domain.Room;
import com.indi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    TableDao tableDao;
    @Autowired
    RoomListDao roomListDao;

    public void insertMessage(String tableName, Message message) {
        if(!checkTableName(tableName)){
            return;
        }
        LambdaQueryWrapper<Room> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Room::getRoomId, tableName);
        Room room = roomListDao.selectOne(lqw);
        if (room == null) {
            tableDao.createTable(tableName);
            roomListDao.insert(new Room(tableName));
        }
        tableDao.insertMessage(tableName, message);
    }

    boolean checkTableName(String tableName) {
        String[] code = tableName.split("_");
        if ("room".equals(code[0])) {
            if (code.length == 3){
                return isNumber(code[1]) && isNumber(code[2]);
            }
            return isNumber(code[1]);
        }
        return false;
    }

    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }
}

package com.indi.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("roomList")
public class Room {
    private Integer id;
    @TableField("room_id")
    private String roomId;

    public Room(String roomId) {
        this.roomId = roomId;
    }
}

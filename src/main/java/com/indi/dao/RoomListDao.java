package com.indi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.indi.domain.Room;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomListDao extends BaseMapper<Room> {
}

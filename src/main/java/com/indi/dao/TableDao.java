package com.indi.dao;

import com.indi.domain.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TableDao {
    public void createTable(String tableName);
    @Insert("insert into " +
            "${tableName} (fromName, toName, message, time) " +
            "values(#{message.fromName}, #{message.toName}, #{message.message}, #{message.time})")
    public void insertMessage(@Param("tableName") String tableName, @Param("message") Message message);
}

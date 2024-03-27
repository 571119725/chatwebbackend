package com.indi.service;

import com.indi.domain.Message;

public interface MessageService {
    public void insertMessage(String tableName, Message message);
}

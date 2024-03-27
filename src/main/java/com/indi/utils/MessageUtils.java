package com.indi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indi.domain.Message;
import com.indi.domain.ResultMessage;

public class MessageUtils {
    public static String ONLINE = "ONLINE";
    public static String USER = "USER";
    public static String HISTORY = "HISTORY";
    public static String generateMessage(String isSystem, Object message) {
        try {
            ResultMessage resultMessage = new ResultMessage(isSystem, message);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(resultMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

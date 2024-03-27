package com.indi.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenUtils {
    private static Map<String, String> tokenSet = new HashMap<>();
    public static boolean checkToken(String token) {return tokenSet.containsKey(token);}
    public static String getUserName(String token) {return tokenSet.get(token);}
    public static String generateToken(String username) {
        String token = UUID.randomUUID().toString();
        tokenSet.put(token, username);
        return token;
    }
}

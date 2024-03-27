package com.indi.controller;

import com.indi.domain.Code;
import com.indi.domain.Result;
import com.indi.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    RedisUtils redisUtils;

    @GetMapping
    public Result getHistory() {
        return new Result(Code.GET_OK, redisUtils.getHistoryMessageJson(), "success");
    }
}

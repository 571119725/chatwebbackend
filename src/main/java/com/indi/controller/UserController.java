package com.indi.controller;

import com.indi.domain.Code;
import com.indi.domain.Result;
import com.indi.domain.User;
import com.indi.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@CrossOrigin(allowedHeaders ="*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public Result login(User user, HttpSession session) {
        String token = userService.login(user);
        if (token != null) {
            session.setAttribute("username", user.getUsername());
            return new Result(Code.GET_OK, token, "success");
        }else{
            return new Result(Code.GET_OK, null, "failed");
        }
    }
}

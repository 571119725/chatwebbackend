package com.indi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.indi.dao.UserDao;
import com.indi.domain.User;
import com.indi.service.UserService;
import com.indi.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public String login(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(null != user.getUsername(), User::getUsername, user.getUsername())
                .eq(null != user.getPassword(), User::getPassword, user.getPassword());
        User loginUser = userDao.selectOne(lqw);
        if(loginUser == null) return null;
        return TokenUtils.generateToken(user.getUsername());
    }
}

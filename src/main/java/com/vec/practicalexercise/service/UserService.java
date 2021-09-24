package com.vec.practicalexercise.service;

import com.vec.practicalexercise.entity.User;
import com.vec.practicalexercise.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User getUser(String email){
        return userDao.getUser(email);
    }

    public int addUser(User user){
        return userDao.addUser(user);
    }

    public int updateUser(String email, User user){
        return userDao.updateUser(email, user);
    }

    public int deleteUser(String email){
        return userDao.deleteUser(email);
    }
}

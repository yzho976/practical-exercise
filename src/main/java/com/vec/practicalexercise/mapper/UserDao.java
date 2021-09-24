package com.vec.practicalexercise.mapper;

import com.vec.practicalexercise.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    User getUser(String email);

    int addUser(User user);

    int updateUser(@Param("keyEmail") String email, @Param("content") User user);

    int deleteUser(String email);

}

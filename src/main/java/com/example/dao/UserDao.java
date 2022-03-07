package com.example.dao;

import com.example.entity.User;

public interface UserDao {

    void save(User user);

    User findByName(String name);

}

package com.example.service;

import com.example.dao.UserDao;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
//        System.out.println("==================================");
//        System.out.println("username = " + username);
//        System.out.println("password = " + password);
        User userDB = userDao.findByName(user.getUsername());
        if (userDB == null) {
            throw new RuntimeException("user does not exist");
        } else {
            if (userDB.getPassword().equals(user.getPassword())) {
                return userDB;
            } else {
                throw new RuntimeException("password is wrong");
            }
        }
    }

    @Override
    public void register(User user) {
        User userByFindByName = userDao.findByName(user.getUsername());
        if (userByFindByName == null) {
            user.setStatus("bingo");
            user.setRegisterTime(new Date());
            userDao.save(user);
        } else {
            throw new RuntimeException("username exists");
        }
    }
}

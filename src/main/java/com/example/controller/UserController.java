package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("getImage")
    public String getImageCode(HttpServletRequest request) throws IOException {
        String code = VerifyCodeUtils.generateVerifyCode(4);

        request.getServletContext().setAttribute("code", code);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(180, 60, byteArrayOutputStream, code);
        return "data:image/png;base64," + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
    }

    @PostMapping("register")
    public Map<String, Object> register(@RequestBody User user, String code, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            String key = (String) request.getServletContext().getAttribute("code");
            if (key.equalsIgnoreCase(code)) {
                userService.register(user);
                map.put("state", true);
                map.put("msg", "bingo");
            } else {
                throw new RuntimeException("code wrong");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", e.getMessage());
        }

        return map;
    }

    @PostMapping("login")
    public Map<String, Object> login(@RequestBody User user) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            User userDB = userService.login(user);
            map.put("state", true);
            map.put("msg","success");
            map.put("user", userDB);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

}

package com.example.controller;

import com.alibaba.druid.sql.visitor.functions.Substring;
import com.example.entity.Emp;
import com.example.service.EmpService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.FieldPosition;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("emp")
public class EmpController {
    @Autowired
    private EmpService empService;

    @Value("${photo.dir}")
    private String realPath;

    @RequestMapping("lists")
    public List<Emp> lists() {
        return empService.findAll();
    }

    @PostMapping("save")
    public Map<String, Object> save(Emp emp, MultipartFile photo) throws IOException {
        HashMap<String, Object> map = new HashMap<>();

        try {
            String extension = photo.getOriginalFilename().substring(photo.getOriginalFilename().indexOf("."));
            String newFileName = UUID.randomUUID().toString() + extension;
            photo.transferTo(new File(realPath, newFileName));

            emp.setPath(newFileName);

            empService.save(emp);
            map.put("state", true);
            map.put("msg", "success");
        } catch (IOException e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "false");
        }
        return map;
    }

    @GetMapping("delete")
    public Map<String, Object> delete(String id) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            empService.delete(id);
            map.put("state", true);
            map.put("msg", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "false");
        }

        return map;
    }

    @GetMapping("findOne")
    public Emp findOne(String id) {
        return empService.findOne(id);
    }

    @PostMapping("update")
    public Map<String, Object> udpate(Emp emp, MultipartFile photo) throws IOException {
        HashMap<String, Object> map = new HashMap<>();

        try {
            String extension = photo.getOriginalFilename().substring(photo.getOriginalFilename().indexOf("."));
            String newFileName = UUID.randomUUID().toString() + extension;
            photo.transferTo(new File(realPath, newFileName));

            emp.setPath(newFileName);

            empService.save(emp);
            map.put("state", true);
            map.put("msg", "success");
        } catch (IOException e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "false");
        }
        return map;
    }

}

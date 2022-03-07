package com.example.service;

import com.example.entity.Emp;

import java.util.List;

public interface EmpService {

    List<Emp> findAll();

    void save(Emp emp);

    void delete(String id);

    Emp findOne(String id);
}

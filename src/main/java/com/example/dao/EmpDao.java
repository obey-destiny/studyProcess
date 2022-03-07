package com.example.dao;

import com.example.entity.Emp;

import java.util.List;

public interface EmpDao {

    List<Emp> findAll();

    void save(Emp emp);

    void delete(String id);

    Emp findOne(String id);
}

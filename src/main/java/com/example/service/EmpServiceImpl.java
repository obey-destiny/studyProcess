package com.example.service;

import com.example.dao.EmpDao;
import com.example.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpDao empDao;

    @Override
    public List<Emp> findAll() {
        return empDao.findAll();
    }

    @Override
    public void save(Emp emp) {
        empDao.save(emp);
    }

    @Override
    public void delete(String id) {
        empDao.delete(id);
    }

    @Override
    public Emp findOne(String id) {
        return empDao.findOne(id);
    }
}

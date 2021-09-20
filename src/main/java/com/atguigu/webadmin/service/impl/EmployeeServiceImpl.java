package com.atguigu.webadmin.service.impl;

import com.atguigu.webadmin.bean.Employee;
import com.atguigu.webadmin.dao.EmployeeDao;
import com.atguigu.webadmin.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao,Employee> implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;
    Counter counter;

    public EmployeeServiceImpl(MeterRegistry meterRegistry){
        counter = meterRegistry.counter("getById");
    }

    @Override
    public void insertEmp(Employee employee) {

        employeeDao.insertEmp(employee);
    }

    @Override
    public Employee getById(Serializable id) {
        counter.increment();
        return this.getBaseMapper().selectById(id);
    }
}

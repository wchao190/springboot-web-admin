package com.atguigu.webadmin.service;

import com.atguigu.webadmin.bean.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService extends IService<Employee> {

    void insertEmp(Employee employee);


}

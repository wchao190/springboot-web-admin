package com.atguigu.webadmin.dao;

import com.atguigu.webadmin.bean.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EmployeeDao extends BaseMapper<Employee> {

    Employee getEmpById(Integer id);

    @Insert("insert tbl_employee(id,last_name,gender,email,dpt_id) values(#{id},#{lastName},#{gender},#{email},#{dptId})")
    @Options(useGeneratedKeys=true,keyProperty = "id")
    int insertEmp(Employee employee);
}

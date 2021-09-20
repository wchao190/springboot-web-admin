package com.atguigu.webadmin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@TableName(value ="tbl_employee")
public class Employee {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String lastName;
    private String gender;
    private String email;
    private String dptId;
}

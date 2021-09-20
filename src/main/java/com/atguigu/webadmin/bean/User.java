package com.atguigu.webadmin.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String name;
    private Integer age;
    private String email;
    private Integer deptId;
}

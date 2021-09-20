package com.atguigu.webadmin.controller;

import com.alibaba.druid.util.Utils;
import com.atguigu.webadmin.bean.Car;
import com.atguigu.webadmin.bean.Employee;
import com.atguigu.webadmin.bean.Student;
import com.atguigu.webadmin.bean.User;
import com.atguigu.webadmin.service.EmployeeService;
import com.atguigu.webadmin.service.UserService;
import com.auguitu.hello.service.HelloService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.commons.codec.binary.Base64;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;

@Controller
@Slf4j
public class IndexController {
    /**
     * 前往登录页
     * @return
     */
    @Autowired
    JdbcTemplate  jdbcTemplate;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    UserService userService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @GetMapping(value = {"/","/login"})
    public String loginPage(){
        return "login";
    }

    /**
     * 主页
     */
    @PostMapping("/login")
    public String mainPage(User user, HttpSession session, Model model){
        if(StringUtils.hasLength(user.getUserName()) && "123456".equals(user.getPassword())){
            session.setAttribute("user",user);
            return "redirect:main.index";
        }else{
            model.addAttribute("msg","用户名或密码错误");
            return "login";
        }
    }

    @GetMapping("/main.index")
    public String getMainPage(HttpSession session,Model model){
        /**
        Object user = session.getAttribute("user");
        if(user != null){
            return "main2";
        }else{
            model.addAttribute("msg","请重新登录");
            return "login";
        }
         */
        String s = stringRedisTemplate.opsForValue().get("/main.index");
        model.addAttribute("mainCount",s);
        return "main";
    }
    @GetMapping("/basic_table")
    public String getBasicTable(){
        return "table/basic_table";
    }
    @GetMapping("/dynamic_table")
    public String getDynamicTable(){
        return "table/dynamic_table";
    }
    @GetMapping("/editable_table")
    public String getEditableTable(){
        return "table/editable_table";
    }
    @GetMapping("/responsive_table")
    public String getResponsiveTable(){
        return "table/responsive_table";
    }
    @GetMapping("/pricing_table")
    public String getPricingTable(){
        return "table/pricing_table";
    }

    @GetMapping("/form_layouts")
    public String getFormLayouts(){
        return "form/form_layouts";
    }

    @SneakyThrows
    @PostMapping("/upload")
    public String fileHandle(@RequestParam("email")String email, @RequestParam("userName")String name,
                             @RequestPart("headerImg") MultipartFile headerImg,
                             @RequestPart("photos")MultipartFile[] photos){
            log.info("上传的信息：email={},name={},headerImg={},photos={}",email,name,headerImg.getSize(),photos.length);
            if(headerImg != null){
                String originalFilename = headerImg.getOriginalFilename();
                headerImg.transferTo(new File("F:\\cache\\"+originalFilename));
            }


        if(photos.length >0){
            for (MultipartFile p:photos) {
                String originalFilename = p.getOriginalFilename();
                System.out.println("文件名字："+originalFilename);
                p.transferTo(new File("F:\\cache\\photo\\"+originalFilename));
            }
        }
        return "main";
    }
    @GetMapping("/sql")
    @ResponseBody
    public String getSql(){
        Long aLong = jdbcTemplate.queryForObject("select count(*) from tbl_employee", Long.class);
        return String.valueOf(aLong);
    }

    @GetMapping("/del")
    @ResponseBody
    public String delSql(){
        int drop_table_tb_user = jdbcTemplate.update("drop table tb_user");
        return String.valueOf(drop_table_tb_user);
    }

    @PostMapping("/download")
    @ResponseBody
    public String getTemplate(HttpServletRequest request,HttpServletResponse response) throws IOException {

        downExcel(request,response);

        return "下载成功";
    }


    public void downExcel(HttpServletRequest request,HttpServletResponse response) throws IOException {
        ClassPathResource resource = new ClassPathResource("file" + File.separator + "客户信息.xlsx");
        File file = resource.getFile();
        String filename = resource.getFilename();

        response.setContentType("application/vnd.ms-excel");

        if(request.getHeader("User-Agent").contains("Firefox")){
            response.setHeader("Content-Disposition","attachment;filename==?UTF-8?B?"+ new BASE64Encoder().encode(filename.getBytes("UTF-8"))+"?=");
        }else{
            response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(filename,"UTF-8"));
        }

        FileInputStream fis = new FileInputStream(file);
        ServletOutputStream os = response.getOutputStream();
        Utils.copy(fis,os);

        /**
        byte[] b = new byte[1024];
        FileInputStream is = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(is);
        ServletOutputStream os = response.getOutputStream();
        int len;
        while ((len = bis.read(b)) != -1){
            os.write(b,0,len);
        }
         */
    }

    @GetMapping("/empId/{eId}")
    @ResponseBody
    public String getEmp(@PathVariable("eId") Integer id){
        Employee empById = employeeService.getById(id);
        return empById.toString();
    }
    @GetMapping("/insert")
    @ResponseBody
    public String InsertEmp(){
        Employee emp = new Employee();
        emp.setLastName("邵老师");
        emp.setGender("女");
        emp.setEmail("shao@saic.com");
        emp.setDptId("1001");
        //boolean empById = employeeService.save(emp);
        employeeService.insertEmp(emp);
        return emp.toString();
    }
    @GetMapping("/getUser/{uId}")
    @ResponseBody
    public String getUser(@PathVariable("uId") Integer id){
        User user = userService.getUserById(id);
        return user.toString();
    }

    @Value("${st.name:李四}")
    private String name;

    @Autowired
    Car car;
    @GetMapping("/getCar")
    @ResponseBody
    public String getCar(){
        return "Hello! "+ car;
    }

    @Autowired
    Student st;
    @GetMapping("/st")
    @ResponseBody
    public String getSt(){
        return "Hello! "+ st;
    }

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return helloService.sayHello("张三");
    }

}


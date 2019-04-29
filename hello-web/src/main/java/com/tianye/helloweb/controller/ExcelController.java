package com.tianye.helloweb.controller;

import com.tianye.hello.excel.model.Student;
import com.tianye.hello.excel.util.ExcelUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
* @Author:tianye
* @Description:
* @Date: 15:39 2018/5/8/008
*/
@RestController
@RequestMapping("/hello")
public class ExcelController {

    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public void excel(HttpServletResponse response) throws Exception {
        String[] head = {"身份证号","姓名","年龄","性别","电话号码","地址","出生日期"};

        List studentList = new ArrayList<Student>();
        studentList.add(new Student("1111","aaa",11,"男","110","北京市西城区",new Date()));
        studentList.add(new Student("2222","bbbb",12,"男","1101111","北京市朝阳区八里庄",new Date()));
        studentList.add(new Student("3333","cccc",13,"男","1102222","北京市朝阳区八里庄慈云寺",new Date()));
        studentList.add(new Student("4444","dddd",14,"男","110333333","北京市朝阳区八里庄慈云寺苏宁易购",new Date()));
        studentList.add(new Student("5555","eeee",15,"男","110111111123233","北京市朝阳区八里庄慈云寺苏宁易购14层",new Date()));

        String fileName = "hello2.xlsx";

        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        OutputStream out = response.getOutputStream();
        ExcelUtil.exportExcel( studentList, out);


    }

}

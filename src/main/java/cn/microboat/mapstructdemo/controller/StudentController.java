package cn.microboat.mapstructdemo.controller;

import cn.microboat.mapstructdemo.pojo.po.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/list")
    public List<Student> listStudent() {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student().setId("1"));
        return list;
    }
}

package cn.microboat.mapstructdemo;

import cn.microboat.mapstructdemo.enums.GenderEnum;
import cn.microboat.mapstructdemo.mapper.IStudentMapper;
import cn.microboat.mapstructdemo.mapper.StudentMapper;
import cn.microboat.mapstructdemo.pojo.dto.StudentDTO;
import cn.microboat.mapstructdemo.pojo.po.Student;
import cn.microboat.mapstructdemo.utils.MyBeanUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static cn.microboat.mapstructdemo.mapper.customMappers.GenderTransForm.gender2GenderEnum;
import static cn.microboat.mapstructdemo.mapper.customMappers.GenderTransForm.genderEnum2Gender;
import static cn.microboat.mapstructdemo.utils.DateUtils.date2Format;

@SpringBootTest
class MapStructDemoApplicationTests {

    @Test
    void contextLoads() {
        Student student = new Student().setId("1").setAge(19).setName("张三").setGender("2").setAdmissionTime(new Date()).setHeight(178.9);
        System.out.println(student);
        StudentDTO studentDTO = StudentMapper.INSTANCE.student2StudentDTO(student);
        System.out.println(studentDTO);
    }

    @Test
    void contextLoads2() {
        StudentDTO studentDTO = new StudentDTO().setAgeNum(29).setGenderEnum(GenderEnum.MALE).setName("里斯").setFirstTime("2022-11-9 12:19:18").setHeight(180);
        System.out.println(studentDTO);
        Student student = StudentMapper.INSTANCE.studentDTO2Student(studentDTO);
        System.out.println(student);
    }

    @Test
    void test01() {
        LinkedList<Student> studentLinkedList = new LinkedList<>();
        Student student;
        for (int i = 1; i <= 100000; i++) {
            student = new Student().setId(i + "").setAge(18 + i).setName("张三" + i).setGender("1");
            studentLinkedList.add(student);
        }
        long oldTime = System.currentTimeMillis();
        List<StudentDTO> studentDTOS = StudentMapper.INSTANCE.studentList2StudentDTOList(studentLinkedList);
        long newTime = System.currentTimeMillis();
        // 10 9 8 9 11
        System.out.println("mapStruct 耗时：" + (newTime - oldTime));
        for (StudentDTO studentDTO : studentDTOS) {
            System.out.println(studentDTO);
        }
    }

    @Test
    void test02() {
        /*
        * BeanUtils 利用反射机制，转换非常慢
        * 转换的两个类要求 数据类型 和 名字 都一样
        * */
        LinkedList<Student> studentLinkedList = new LinkedList<>();
        LinkedList<StudentDTO> studentDTOLinkedList = new LinkedList<>();
        Student student;
        for (int i = 1; i <= 100000; i++) {
            student = new Student().setId(i + "").setAge(18 + i).setName("张三" + i);
            studentLinkedList.add(student);
        }
        long oldTime = System.currentTimeMillis();
        for (Student stu : studentLinkedList) {
            StudentDTO studentDTO = new StudentDTO();
            BeanUtils.copyProperties(stu, studentDTO);
            studentDTOLinkedList.add(studentDTO);
        }
        long newTime = System.currentTimeMillis();
        // 235 234 204 217 219
        System.out.println("BeanUtils 耗时：" + (newTime - oldTime));
//        for (StudentDTO studentDTO : studentDTOLinkedList) {
//            System.out.println(studentDTO);
//        }
    }

    @Test
    void test03() {
        LinkedList<Student> studentLinkedList = new LinkedList<>();
        LinkedList<StudentDTO> studentDTOLinkedList = new LinkedList<>();
        Student student;
        for (int i = 1; i <= 100000; i++) {
            student = new Student().setId(i + "").setAge(18 + i).setName("张三" + i);
            studentLinkedList.add(student);
        }

        long oldTime = System.currentTimeMillis();
        for (Student student1 : studentLinkedList) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setAgeNum(student1.getAge());
            studentDTO.setName(student1.getName());
            studentDTOLinkedList.add(studentDTO);
        }
        long newTime = System.currentTimeMillis();
        // 13 10 8 9 7
        System.out.println("get/set 耗时：" + (newTime - oldTime));

//        for (StudentDTO studentDTO : studentDTOLinkedList) {
//            System.out.println(studentDTO);
//        }
    }

    @Test
    void test04() {
        String gender = genderEnum2Gender(GenderEnum.MALE);
        System.out.println(gender);

        GenderEnum genderEnum = gender2GenderEnum(gender);
        System.out.println(genderEnum);
    }

    @Test
    void test05() {
        /*
        * 千万级别下，LinkedList 耗时14658ms  ArrayList 耗时19228ms   LinkedList 稍快
        * 百万级别下，LinkedList 耗时741ms    ArrayList 耗时126ms     ArrayList 更快
        * */
        LinkedList<Student> studentLinkedList = new LinkedList<>();
        LinkedList<StudentDTO> studentDTOLinkedList = new LinkedList<>();
        Student student;
        long oldTime = System.currentTimeMillis();
        for (int i = 1; i <= 1000000; i++) {
            student = new Student().setId(i + "").setAge(18 + i).setName("张三" + i).setHeight(i + 0.0).setGender("1").setAdmissionTime(new Date());
            studentLinkedList.add(student);
        }
        long newTime = System.currentTimeMillis();
        System.out.println("LinkedList 耗时" + (newTime - oldTime));

        List<Student> students = new ArrayList<>();
        List<StudentDTO> studentDTOArrayList = new ArrayList<>();
        long oldTime2 = System.currentTimeMillis();
        for (int i = 1; i <= 1000000; i++) {
            student = new Student().setId(i + "").setAge(18 + i).setName("张三" + i).setHeight(i + 0.0).setGender("1").setAdmissionTime(new Date());
            students.add(student);
        }
        long newTime2 = System.currentTimeMillis();
        System.out.println("ArrayList 耗时" + (newTime2 - oldTime2));
    }

    @Test
    void test06() {
        List<Student> students = new ArrayList<>();
        Student student;
        for (int i = 1; i <= 4; i++) {
            student = new Student().setId(i + "").setAge(18 + i).setName("张三" + i).setHeight(i + 0.0).setGender("1").setAdmissionTime(new Date());
//            System.out.println(student);
            students.add(student);
        }
        MyBeanUtils.copyListProperties(students, StudentDTO::new, (s, t) -> {
            t.setAgeNum(s.getAge());

            System.out.println(s);
            System.out.println(t);
        });
//        for (StudentDTO studentDTO : studentDTOList) {
//            System.out.println(studentDTO);
//        }
    }

    @Test
    void test07() {
        Date date = new Date();
        String s = date2Format(date);
        System.out.println(s);
    }

    @Test
    void test08() {
        Student student = new Student().setId("1").setName("赵六").setHeight(190.1).setAdmissionTime(new Date()).setAge(23).setGender("1");
        StudentDTO studentDTO = IStudentMapper.INSTANCE.entity2Model(student);
        System.out.println(studentDTO);
    }


}

package cn.microboat.mapstructdemo.mapper;

import cn.microboat.mapstructdemo.pojo.dto.StudentDTO;
import cn.microboat.mapstructdemo.pojo.po.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    /*
    * 类型一致，名字一致，可以直接转换
    * 类型一致，名字不一致，通过 source/target 可以转换
    * 类型不一致，且不完全是基本数据类型或者对应的包装类，通过 target/expression 可以转换，需要写一个转换类
    * 【特殊】一个是 Date 日期类，一个 String，通过 source/target/dateFormat 可以转换
    * 【特殊】两个都是基本数据类型或者对应的包装类，可以直接转换，但会缺失精度
    * 【特殊】被List<>泛型包裹后，如果已经存在单个之间的互相转换 @Mapping 注解，不需要在List转换上再写
    * 【特殊】枚举可以直接转换，但默认取的是枚举的 name，如果要取 value，只能通过 target/expression 转换，要写一个转换类
    * */

    @Mappings({
            @Mapping(source = "age", target = "ageNum"),
            @Mapping(source = "admissionTime", target = "firstTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
//            @Mapping(source = "gender", target = "genderEnum"),
            @Mapping(target = "genderEnum", expression = "java(cn.microboat.mapstructdemo.mapper.customMappers.GenderTransForm.gender2GenderEnum(student.getGender()))")
    })
    StudentDTO student2StudentDTO (Student student);

    @Mappings({
            @Mapping(source = "ageNum", target = "age"),
            @Mapping(source = "firstTime", target = "admissionTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
//            @Mapping(source = "genderEnum", target = "gender"),
            @Mapping(target = "gender", expression = "java(cn.microboat.mapstructdemo.mapper.customMappers.GenderTransForm.genderEnum2Gender(studentDTO.getGenderEnum()))")
    })
    Student studentDTO2Student (StudentDTO studentDTO);

    /*
    * 这里不需要加 @Mappings 注解，List 之间转换会调用单个转换的方法，只要单个转换里有对应的 mapping，就能转换成功
    * */
//    @Mappings({
//            @Mapping(source = "age", target = "ageNum"),
//            @Mapping(target = "genderEnum", expression = "java(cn.microboat.mapstructdemo.mapper.customMappers.GenderTransForm.gender2GenderEnum(student.getGender()))")
//    })
    List<StudentDTO> studentList2StudentDTOList(List<Student> students);

//    @Mappings({
//            @Mapping(source = "ageNum", target = "age"),
//            @Mapping(target = "gender", expression = "java(cn.microboat.mapstructdemo.mapper.customMappers.GenderTransForm.genderEnum2Gender(studentDTO.getGenderEnum()))")
//    })
    List<Student> studentDTOList2studentList(List<StudentDTO> studentDTOS);
}

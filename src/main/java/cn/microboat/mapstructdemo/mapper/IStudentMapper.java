package cn.microboat.mapstructdemo.mapper;

import cn.microboat.mapstructdemo.pojo.dto.StudentDTO;
import cn.microboat.mapstructdemo.pojo.po.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
* IStudentMapper 继承 IBaseMapper<StudentDTO, Student> 后，获得的父类的抽象方法
* */
@Mapper
public interface IStudentMapper extends IBaseMapper<StudentDTO, Student> {
    IStudentMapper INSTANCE = Mappers.getMapper(IStudentMapper.class);
}

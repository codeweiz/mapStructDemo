package cn.microboat.mapstructdemo.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author zhouwei
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String id;
    private int age;
    private String name;
    private String gender;
    private Date admissionTime;
    private Double height;
}

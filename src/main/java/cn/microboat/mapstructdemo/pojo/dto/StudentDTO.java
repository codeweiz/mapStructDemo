package cn.microboat.mapstructdemo.pojo.dto;

import cn.microboat.mapstructdemo.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author zhouwei
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Integer ageNum;
    private String name;
    private GenderEnum genderEnum;
    private String firstTime;
    private Integer height;
}

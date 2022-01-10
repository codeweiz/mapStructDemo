package cn.microboat.mapstructdemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhouwei
 */
@AllArgsConstructor
@Getter
public enum GenderEnum {
    /*
    * UNKNOWN 未知 对应数据库里字段为 "-1"
    * FEMALE 女 对应数据库里字段为 "0"
    * MALE 男 对应数据库里字段为 "1"
    * */
    UNKNOWN("-1"),
    FEMALE("0"),
    MALE("1");

    private final String genderValue;
}

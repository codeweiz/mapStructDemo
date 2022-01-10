package cn.microboat.mapstructdemo.mapper.customMappers;

import cn.microboat.mapstructdemo.enums.GenderEnum;
import org.springframework.util.ObjectUtils;

/**
 * @author zhouwei
 */
public class GenderTransForm {
    /**
     * @auther zhouwei
     * @param genderEnum
     * @return Optional
     * */
    public static String genderEnum2Gender(GenderEnum genderEnum) {
        if (ObjectUtils.isEmpty(genderEnum)) {
            return "";
        }
        return genderEnum.getGenderValue();
    }

    /**
     * @auther zhouwei
     * @param gender
     * @return Optional
     * */
    public static GenderEnum gender2GenderEnum(String gender) {
        if (ObjectUtils.isEmpty(gender)) {
            return GenderEnum.UNKNOWN;
        }
        for (GenderEnum genderEnum : GenderEnum.values()) {
            if (genderEnum.getGenderValue().equals(gender)) {
                return genderEnum;
            }
        }
        return GenderEnum.UNKNOWN;
    }
}

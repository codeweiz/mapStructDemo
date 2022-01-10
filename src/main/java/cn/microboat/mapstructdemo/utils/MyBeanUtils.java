package cn.microboat.mapstructdemo.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MyBeanUtils extends BeanUtils {

    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }

    public static <S, T, R> List<T> copyListProperties(List<S> sources, Supplier<T> target, MyBeanUtilsCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            list.add(t);

            // 接收一个 Function 接口作为回调，在这用来处理不能被 BeanUtils 自动转换的属性，可以手动 get/set
            if (!ObjectUtils.isEmpty(callBack)) {
                callBack.callBack(source, t);
            }
        }
        return list;
    }
}

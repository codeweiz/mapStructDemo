package cn.microboat.mapstructdemo.utils;

@FunctionalInterface
public interface MyBeanUtilsCallBack<S,T> {

    void callBack(S s, T t);
}

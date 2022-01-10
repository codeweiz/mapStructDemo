package cn.microboat.mapstructdemo.mapper;

import java.util.List;

/*
* 这个接口约束了 传入参数 和 默认抽象方法
* 通用基础接口
* */
public interface IBaseMapper<M, E> {
    E model2Entity(M model);

    M entity2Model(E entity);

    List<E> models2Entities(List<M> models);

    List<M> entities2Models(List<E> entities);
}

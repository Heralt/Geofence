package com.denisroyz.geofence.di;

/**
 * Created by Heralt on 06.09.2017.
 */

public interface BeanDefinition<T> {

    T createBean(DIContext diContext);

    String getTag();
}

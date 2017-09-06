package com.denisroyz.geofence.di;

import com.denisroyz.geofence.service.ObjectSerializer;

/**
 * Created by Heralt on 06.09.2017.
 */

public class BaseApplicationScopeBeanDefinitions {

    public void inject(DIContext diContext){
        diContext.addBean(BeanTags.OBJECT_SERIALIZER, new ObjectSerializer());
    }
}

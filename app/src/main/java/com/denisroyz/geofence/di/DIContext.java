package com.denisroyz.geofence.di;

import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Heralt on 06.09.2017.
 */

public class DIContext {

    private static final String LOG_TAG = "DIContext";

    private Map<String, Object> beans;
    private Set<BeanDefinition> beanDefinitions;

    public DIContext(){
        beans = new HashMap<>();
        beanDefinitions = new HashSet<>();
    }

    public void addBean(String tag, Object bean){
        addBean(tag, bean, "public_setter");
    }
    private void addBean(String tag, Object bean, String creator){
        Log.i(LOG_TAG, String.format("created bean (tag:%s) of type %s. Created by (%s)",
                tag, bean.getClass().getCanonicalName(), creator));
        beans.put(tag, bean);
    }

    void addBeanDefinition(BeanDefinition beanDefinition){
        beanDefinitions.add(beanDefinition);
    }

    public Object getBean(String tag) throws CanNotSatisfyDependencyException {
        Object result = getObjectFromPool(tag);
        if (result!=null){
            Log.i(LOG_TAG, String.format("providing bean (tag:%s) of type %s ",
                    tag, result.getClass().getCanonicalName()));
            return result;
        }
        result = createObjectFromBeanDefinitions(tag);
        if (result!=null){
            Log.i(LOG_TAG, String.format("providing bean (tag:%s) of type %s ",
                    tag, result.getClass().getCanonicalName()));
            return result;
        }
        throw new CanNotSatisfyDependencyException(tag);
    }


    private Object createObjectFromBeanDefinitions(String tag){
        for (BeanDefinition beanDefinition: beanDefinitions){
            if (beanDefinition.getTag().equals(tag)){
                String beanDefinitionClassName = beanDefinition.getClass().getCanonicalName();
                Object bean = beanDefinition.createBean(this);
                addBean(tag, bean, beanDefinitionClassName );
                return bean;
            }
        }
        return null;
    }
    private Object getObjectFromPool(String tag){
        return beans.get(tag);
    }
}

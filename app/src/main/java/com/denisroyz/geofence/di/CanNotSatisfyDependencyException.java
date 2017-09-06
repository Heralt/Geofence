package com.denisroyz.geofence.di;

/**
 * Created by Heralt on 06.09.2017.
 */

public class CanNotSatisfyDependencyException extends RuntimeException{

    public CanNotSatisfyDependencyException(String tag){
        super(String.format("Can not found any bean or bean definition for tag %s", tag));
    }
}

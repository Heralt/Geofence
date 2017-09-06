package com.denisroyz.geofence.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Heralt on 06.09.2017.
 * In ordinary case i'd prefer to use Json serialization with com.google.code.gson library
 * but as we discussed with "Dane McLeod" i am not using any external non-android dependency.
 * So binary object serialization was chosen as simplest way to serialize object to string.
 */
public class ObjectSerializer {

    public Object readFromString(String serializedObject ) throws IOException,
            ClassNotFoundException {
        byte b[] = serializedObject.getBytes();
        ByteArrayInputStream bi = new ByteArrayInputStream(b);
        ObjectInputStream si = new ObjectInputStream(bi);
        return si.readObject();
    }

    public String writeToString(Serializable o ) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream so = new ObjectOutputStream(bo);
        so.writeObject(o);
        so.flush();
        return bo.toString();
    }

}

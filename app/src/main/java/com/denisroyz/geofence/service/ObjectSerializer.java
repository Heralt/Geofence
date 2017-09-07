package com.denisroyz.geofence.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
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

    public Object readFromString(String str) throws IOException,
            ClassNotFoundException {

        byte b[] = str.getBytes("ISO-8859-1");
        ByteArrayInputStream bi = new ByteArrayInputStream(b);
        ObjectInputStream si = new ObjectInputStream(bi);
        return si.readObject();
    }

    public String writeToString(Object obj)throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream so = new ObjectOutputStream(bo);
        so.writeObject(obj);
        so.flush();
        // This encoding induces a bijection between byte[] and String (unlike UTF-8)
        return bo.toString("ISO-8859-1");
    }

}

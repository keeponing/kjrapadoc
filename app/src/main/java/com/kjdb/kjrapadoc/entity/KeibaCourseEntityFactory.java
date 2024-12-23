package com.kjdb.kjrapadoc.entity;

import com.kjdb.kjrapadoc.utility.JraUtility;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by kiich on 2017/03/15.
 */

public class KeibaCourseEntityFactory extends Hashtable<String, KeibaCourseEntity> {
    private static KeibaCourseEntityFactory _instance = null;

    private KeibaCourseEntityFactory()
    {

    }

    public static KeibaCourseEntityFactory getInstance()
    {
        if(null == _instance)
        {
            _instance = new KeibaCourseEntityFactory();
        }
        return _instance;
    }

    public static KeibaCourseEntity getKeibaCourseEntity(String key)
    {
        KeibaCourseEntityFactory instance = getInstance();
        KeibaCourseEntity entity = instance.get(key);
        if(null == entity)
        {
            entity = new KeibaCourseEntity();
            String path = JraUtility.getCPathRace(key);
            entity.load(path);
            if(entity._loaded) {
                instance.put(key, entity);
            }
            else
            {
                entity = null;
            }
        }

        return entity;
    }

    public static void loadAll() {
        try {
            KeibaCourseEntityFactory instance = getInstance();
            String pathParent = JraUtility.getCPath();
            ArrayList<String> files = JraUtility.getFiles(pathParent);
            for (String fileName : files) {

                File file = new File(fileName);
                String key = file.getName();
                key = key.replace(".txt", "");
                String name = JraUtility.getCPathRace(key);
                KeibaCourseEntity entity = new KeibaCourseEntity();

                entity.load(name);
                instance.put(key, entity);
            }
        } catch (Exception e) {

        }
    }
}


package com.kjdb.kjrapadoc.entity;

import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by d635257 on 2017/04/13.
 */

public class DiagnosisEntity  implements Serializable {
    public String _title = "";
    public Map<String, Integer> _report = new Hashtable<String, Integer>();

    public List<Pair> getList()
    {
        List<Pair> list = new ArrayList<Pair>();

        list.add(new Pair("■"+_title, 0));

        for(String key: _report.keySet()) {
            String temp = String.format("\t%s", key);
            list.add(new Pair(temp, _report.get(key)));
        }

        return list;
    }
}

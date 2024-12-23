package com.kjdb.kjrapadoc.entity;

import java.io.BufferedReader;
import java.io.Serializable;

/**
 * Created by d635257 on 2017/03/10.
 */

public class BaseEntity implements Serializable {


    protected String nextToken(String[] items, int index)
    {
        try
        {
            String ret = items[index];
            ret.trim();
            return ret;
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return "";
    }

    protected void skipBufferedReader(int count, BufferedReader br)
    {
        try {

            for(int i = 0; i < count; i++)
            {
                br.readLine();
            }
        }
        catch (Exception e)
        {

        }
    }

}

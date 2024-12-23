package com.kjdb.kjrapadoc.entity;

import com.kjdb.kjrapadoc.utility.JraUtility;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class CodeTable extends BaseEntity  {
    private static CodeTable _instance = null;
    private Map<Integer,ArrayList<String[]>> _map = new Hashtable<Integer, ArrayList<String[]>>();
    private  CodeTable()
    {

    }

    public  static  CodeTable getInstance()
    {
        if(null == _instance)
        {
            _instance = new CodeTable();
            _instance.load();

        }
        return _instance;
    }

    public  static  String solveVal1(Integer type, String key)
    {
        String ret ="";
        try {

           CodeTable ct = getInstance();
           ArrayList<String[]> list = ct._map.get(type);
           for (String[] data : list) {
               if(3<  data.length) {
                   if (data[2].equals(key) ) {
                       ret = data[3];
                       break;
                   }
               }
           }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return ret;
    }

    public  static  String solveVal2(Integer type, String key)
    {
        String ret ="";
        try {

            CodeTable ct = getInstance();
            ArrayList<String[]> list = ct._map.get(type);
            for (String[] data : list) {
                if(3<=  data.length) {
                    if (data[2].equals(key) ) {
                        ret = data[4];
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return ret;
    }
    public  static  String solveVal3(Integer type, String key)
    {
        String ret ="";
        try {

            CodeTable ct = getInstance();
            ArrayList<String[]> list = ct._map.get(type);
            for (String[] data : list) {
                if(5<=  data.length) {
                    if (data[2].equals(key)) {
                        ret = data[5];
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return ret;
    }

    public  static  String solveVal3ToCode(Integer type, String code)
    {
        String ret ="";
        try {

            CodeTable ct = getInstance();
            ArrayList<String[]> list = ct._map.get(type);
            for (String[] data : list) {
                if(5<=  data.length) {
                    if (data[5].equals(code)) {
                        ret = data[2];
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return ret;
    }

    private  void load()
    {
        String file =JraUtility.getDPathCodeTable();


        try {
            //読み込みファイルのインスタンス生成
            //ファイル名を指定する
            FileInputStream fi = new FileInputStream(file);
            InputStreamReader is = new InputStreamReader(fi);
            BufferedReader br = new BufferedReader(is);


            //読み込み行数の管理
            int i = 0;

            //列名を管理する為の配列

            //1行ずつ読み込みを行う
            String line="";

            while (( line = br.readLine()) != null) {

                //先頭行は列名
                if (i != 0) {

                    //カンマで分割した内容を配列に格納する
                    String[] data = line.split(",");
                    Integer key = Integer.parseInt(data[1]);
                    if(_map.containsKey(key))
                    {
                       ArrayList list = _map.get(key);
                       list.add(data);
                    }
                    else
                    {
                        ArrayList list = new ArrayList();
                        list.add(data);
                        _map.put(key, list);
                    }


                }
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}

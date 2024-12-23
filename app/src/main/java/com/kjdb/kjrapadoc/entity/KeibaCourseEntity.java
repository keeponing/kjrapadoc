package com.kjdb.kjrapadoc.entity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

/**
 * Created by kiich on 2017/03/03.
 */

public class KeibaCourseEntity implements Serializable {

    public String _00rough; // 順当か？
    public String _01characteristic;   // コース特徴
    public String _02waku;//内枠(特に2枠）
    public String _03father[];//ディープインパクト、キングカメハメハ、ゼンノロブロイ、ハービンジャー
    public String _04jockey[];//川田将雅、Mデムーロ、ルメール
    public boolean _loaded = false;
    private DiagnosisEntity _diagnosis = null;
    public Integer forecast(HorseEntity he)
    {
        Integer ret = 0;
        try
        {
            _diagnosis = new DiagnosisEntity();
            _diagnosis._title = "コース特性";


            Integer temp  = (true == isMatchJockey(he.rr_r_jockey) ? 1 : 0);
            if(1 == temp)
            {
                he._styles.select("cs_jockey",true);
                _diagnosis._report.put(he.rr_r_jockey, 1);
                ret +=1;
            }
            else
            {
                he._styles.select("cs_jockey",false);
            }
//            temp  = (true == isMatchFather(he._father) ? 1 : 0);
//            if(1 == temp)
//            {
//                he._compatible.setFather();
//                _diagnosis._report.put(he._father, 1);
//                ret +=1;
//            }
//            temp  = (true == isPassOrder(he._prev.feetString()) ? 1 : 0);
//            if(1 == temp)
//            {
//                he._compatible.setPassOrder();
//                _diagnosis._report.put(_01characteristic, 1);
//                ret +=1;
//            }

        }
        catch (Exception e){}
        return ret;
    }

    public  DiagnosisEntity getDiagnosis()
    {
        return _diagnosis;
    }
//
//    public String getFatherString()
//    {
//        String ret ="";
//        if(null != _03father)
//        {
//            for(String str : _03father)
//            {
//                ret+=str+",";
//            }
//        }
//        return ret;
//    }

    public String getJockeyString()
    {
        String ret ="";
        if(null != _04jockey)
        {
            for(String str : _04jockey)
            {
                ret+=str+",";
            }
        }
        return ret;
    }

    public  void load(String path) {
        // AssetManagerの呼び出し
        FileInputStream fi = null;
        InputStreamReader is = null;
        BufferedReader br = null;
        try {

            // CSVファイルの読み込み
            fi = new FileInputStream(path);
            is = new InputStreamReader(fi);
            br = new BufferedReader(is);

            //どちらかと言えば堅く決まりやすいコース
            //逃げ・先行がやや有利なコース。
            //内枠(特に2枠）
            //ディープインパクト、キングカメハメハ、ゼンノロブロイ、ハービンジャー
            //川田将雅、Mデムーロ、ルメール

            //どちらかと言えば堅く決まりやすいコース
            _00rough = br.readLine();

            //逃げ・先行がやや有利なコース。
            _01characteristic = br.readLine();

            //内枠(特に2枠）
            _02waku = br.readLine();

            //ディープインパクト、キングカメハメハ、ゼンノロブロイ、ハービンジャー
            String temp = br.readLine();
            _03father = temp.split("、", 0);

            //川田将雅、Mデムーロ、ルメール
            temp = br.readLine();
            _04jockey = temp.split("、", 0);

            br.close();
            is.close();
            fi.close();
            _loaded = true;
        } catch (FileNotFoundException e2) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public  boolean isMatchFather(String  input) {
        boolean matched = false;
        try
        {
            if(null != _03father) {
                String str = _03father[0];
                String[] stringList = str.split(",");
                for (String temp : stringList) {
                    if (-1 != temp.indexOf("Empire Maker")) {
                        temp = "エンパイアメーカー";
                    }
                    String input2 = input.replace(" ", "");
                    if (-1 != temp.indexOf(input2)) {
                        matched = true;
                        break;
                    }
                }
//                for(int i = 0; i < _03father.length; i++) {
//                    String temp = _03father[i];
//
//                    if (-1 != temp.indexOf("Empire Maker")) {
//                        temp = "エンパイアメーカー";
//                    }
//                    String input2 = input.replace(" ", "");
//                    if (-1 != temp.indexOf(input2)) {
//                        matched = true;
//                    }
//                }
            }
        }
        catch (Exception e)
        {

        }
        return matched;
    }
    public  boolean isMatchJockey(String  input) {
        boolean matched = false;
        try {
            if (null != _04jockey) {
                String str = _04jockey[0];
                String[] stringList = str.split(",");
                for (String temp : stringList) {
                    if (-1 != temp.indexOf("Mデムーロ")) {
                        temp = "Mデムーロ";
                    }
                    String input2 = input.replace(" ", "");
                    if (-1 != temp.indexOf(input2)) {
                        matched = true;
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {

        }
        return matched;
    }



    public  boolean isPassOrder(String  input) {
        boolean matched = false;
        try
        {
            if(false == input.isEmpty()) {
                if (true == _01characteristic.contains(input)) {
                    matched = true;
                }
            }
        }
        catch (Exception e)
        {

        }
        return matched;
    }
}


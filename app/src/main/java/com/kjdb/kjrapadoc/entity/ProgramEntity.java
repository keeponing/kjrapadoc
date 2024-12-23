package com.kjdb.kjrapadoc.entity;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjdb.kjrapadoc.utility.JraUtility;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by d635257 on 2017/03/10.
 */

public class ProgramEntity extends BaseEntity implements Serializable {
    public String ph_id = "";             // ID
    public String ph_year = "";           //  年
    public String ph_month = "";           //  月
    public String ph_day = "";              //  日
    public String ph_count =  "";          //  開催回
    public String ph_place = "";          //  開催場所
    public String ph_place_count = "";          //  開催日
    private  boolean _loaded = false;
    //public String _slash_title = "";     // yyyy/mm/dd
    // public String _time = "";           //  何日目
    // public String _trim_title = "";     // yyyymmdd


    public Map<String, RaceEntity> _races = null;

    public ProgramEntity()
    {
        _races = new Hashtable<String, RaceEntity>();
    }


    public boolean isMatched(String id)
    {
        boolean ret = false;
        if(ph_id == id)
        {
            ret = true;
        }
        return ret;
    }

    public void load(Context context, String id, String title) {

        try {


            String path = JraUtility.getAPathRace(title);
            File file = new File(path);
            if(true == file.exists()) {
                // JSONファイルの読み込み
                ObjectMapper mapper = new ObjectMapper();
                FileInputStream fis = new FileInputStream(file);
                Reader reader = new InputStreamReader(fis, "Shift_JIS");
                JsonNode rootNode = mapper.readTree(reader) ;
                JsonNode phNode = rootNode.get("ph");
                if(_loaded == false) {
                    String monthDay = phNode.get("ph_monthday").asText();
                    ph_id =  phNode.get("ph_id").asText();
                    ph_year =  phNode.get("ph_year").asText();
                    ph_month = monthDay.substring(0,2);
                    ph_day = monthDay.substring(2,4);
                    ph_count = phNode.get("ph_count").asText();
                    ph_place = phNode.get("ph_place").asText();
                    ph_place_count = phNode.get("ph_place_count").asText();
                    _loaded =true;
                }

                RaceEntity re = RaceEntity.load(context, this,rootNode);
                _races.put(re.rh_race,re);
                }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    public String getTitle()
    {
        String place =CodeTable.solveVal3(2001,ph_place);
        String ret =String.format("%s %s年%s月%s日",place, ph_year,ph_month,ph_day);
        return ret;
    }


    public RaceEntity getRaceByString(Integer raceNo)
    {
        try
        {
            String temp = String.format("%02d", raceNo);
            return _races.get(temp);
        }
        catch (Exception e){
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }

        return null;
    }

//    public RaceEntity getRaceAt(Integer raceNo)
//    {
//        RaceEntity entity = null;
//        try
//        {
//            entity =  getRaceByString(raceNo);
//        }
//        catch (Exception e){
//            Log.d(this.getClass().getName(), e.getLocalizedMessage());
//        }
//        return entity;
//    }
    public RaceEntity getRaceAt(int raceNo)
    {
        RaceEntity entity = null;
        try
        {
            ArrayList<Integer> raceList = JraUtility.getRaces();
            if(raceList != null) {
                Integer race = raceList.get(raceNo - 1);
                entity = getRaceByString(race);
            }
        }
        catch (Exception e){
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
        return entity;
    }


    public HorseEntity getHorseEntityByName(String input) {
        HorseEntity ret = null;
        try
        {
            for(RaceEntity entity : _races.values())
            {
                ret = entity.getHorseSummaryEntityByPartOfName(input);
                if(null != ret) {
                    break;
                }
            }
        }
        catch (Exception e){}
        return  ret;
    }
    public RaceEntity getPrevRaceAt(RaceEntity input)
    {
        ArrayList<Integer> list = JraUtility.getRaces();
        RaceEntity target = null;
        try {
            RaceEntity prev = null;
            for(Integer key : list)
            {
                RaceEntity entity = getRaceByString(key);

                if(entity == input)
                {
                    target = prev;
                    break;
                }

                prev = entity;

            }
        }
        catch (Exception e){}
        return target;
    }


    public void updateSelected(String programId,String raceNo)
    {

        try{
            if(0 != programId.length())
            {
                RaceEntity re = _races.get(raceNo);
                re.updateSelected();
            }

        } catch (Exception e) {

        }

    }
}


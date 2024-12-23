package com.kjdb.kjrapadoc.entity;

import android.content.Context;

import com.kjdb.kjrapadoc.utility.JraUtility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by kiich on 2017/03/17.
 */

public class ForecastContainer extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private  Context _context =null;
    public Map<String, ForecastEntity> _forecasts  = null;

    public ForecastContainer(Context context)
    {
        _forecasts = new Hashtable<String, ForecastEntity>();
    }

    public  ArrayList<String> getProgramTitles()
    {
        ArrayList<String> ret = new ArrayList<String>();
        for( ForecastEntity fe : _forecasts.values())
        {
            ret.add(fe._program.getTitle());
        }
        return ret;
    }

    public ProgramEntity getProgramAt(String input)
    {
        try{
            ForecastEntity entity = _forecasts.get(input);
            return entity._program;
        }
        catch(Exception e){}
        return null;
    }

    public RaceEntity getRaceAt(String id, String raceNo)
    {
        try {
            ForecastEntity entity = _forecasts.get(id);
            if (null != entity) {
                return entity.getRaceAt(Integer.parseInt(raceNo));
            }
        }
        catch(Exception e){}
        return null;
    }


    public HorseEntity getHorseAt(String horseName)
    {
        HorseEntity ret = null;
        try {
            for(ForecastEntity fe : _forecasts.values())
            {
                ret = fe._program.getHorseEntityByName(horseName);
                if(ret != null) {
                    break;
                }
            }

        }
        catch(Exception e){}
        return ret;
    }

    public void load()
    {
        try {
            _forecasts = new Hashtable<String, ForecastEntity>();
            String folder = JraUtility.getAPath();
            ArrayList<String> files = JraUtility.getFiles(folder);
            //Date date1 = new Date();  //(1)Dateオブジェクトを生成
            Calendar cal = Calendar.getInstance();

            //cal.add(Calendar.DAY_OF_MONTH,1);
            Date date1 = cal.getTime();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            String prefixDate = sdf1.format(date1);
            for (String title : files) {
                if((-1 != title.indexOf(".txt")) && (-1 != title.indexOf(prefixDate))) {
                    String id = createId(title);
                    ForecastEntity entity = _forecasts.get(id);
                    if (null == entity) {
                        entity = new ForecastEntity();
                        _forecasts.put(id, entity);

                    }
                    entity.load(_context, id, title);
                }
            }
            if(0==_forecasts.size())
            {
                for (String title : files) {
                    if (-1 != title.indexOf(".txt")) {
                        String id = createId(title);
                        ForecastEntity entity = _forecasts.get(id);
                        if (null == entity) {
                            entity = new ForecastEntity();
                            _forecasts.put(id, entity);

                        }
                        entity.load(_context, id, title);
                    }
                }
            }
        }
        catch (Exception e)
        {

        }
    }


    public void deleteAllFile()
    {
        // シリアライズ
        try{
            deleteFiles(JraUtility.getKPath());
            JraUtility.createFolder(JraUtility.getKPath());
//            deleteFiles(JraUtility.getAPath());
//            deleteFiles(JraUtility.getBPath());
//            deleteFiles(JraUtility.getEPath());
//            deleteFiles(JraUtility.getGPath());
//            deleteFiles(JraUtility.getFPath());
//            deleteFiles(JraUtility.getIPath());
//            deleteFiles(JraUtility.getJPath());
//
//            JraUtility.createFolder(JraUtility.getAPath());
//            JraUtility.createFolder(JraUtility.getBPath());
//            JraUtility.createFolder(JraUtility.getEPath());
//            JraUtility.createFolder(JraUtility.getGPath());
//            JraUtility.createFolder(JraUtility.getFPath());
//            JraUtility.createFolder(JraUtility.getIPath());
//            JraUtility.createFolder(JraUtility.getJPath());
  //          deleteFiles(JraUtility.getFPath());
          //  deleteFiles(JraUtility.getWPath());

          //  deleteFiles(JraUtility.getMPath());


           // deleteFiles(JraUtility.getIPath());
           // deleteFiles(JraUtility.getLPath());
           // deleteFiles(JraUtility.getJraPath());
        } catch (Exception e) {

        }
    }


    public void serializable()
    {
        // シリアライズ
        try{
           for(ForecastEntity entity : _forecasts.values())
           {
               entity.serializable();
           }
        } catch (Exception e) {

        }
    }
//
//    public void deserializable()
//    {
//        // デシリアライズ
//        try {
//            _forecasts.clear();
//            String folder = JraUtility.getFPath();
//            ArrayList<String> files = JraUtility.getFiles(folder);
//
//            for (String title : files) {
//                Integer id = createId(title);
//                _forecasts.put(id, ForecastEntity.deserializable(title));
//
//            }
//        } catch (Exception e) {
//
//        }
//    }

    public void updatePredict(boolean isPrecisionMode)
    {
        FileInputStream fi = null;
        InputStreamReader is = null;
        BufferedReader br = null;

        try {
            String path = new String();
            if(true==isPrecisionMode) {
                path = JraUtility.getH1Path();
            }
            else {
                path = JraUtility.getHEPath();
            }

            File dir = new File(path);

            //listFilesメソッドを使用して一覧を取得する
            File[] list = dir.listFiles();
            if(null != list)
            {
                for(File file : list) {
                    if (true == file.exists()) {
                        String placeCode = file.getName().substring(8, 10);
                        if (false == placeCode.isEmpty()) {
                            List<String> predictList = new ArrayList<String>();
                            // ファイルの読み込み
                            fi = new FileInputStream(file);
                            is = new InputStreamReader(fi, "Shift-JIS");
                            br = new BufferedReader(is);
                            String line = "";
                            while ((line = br.readLine()) != null) {
                                predictList.add(line);
                            }
                            br.close();
                            is.close();
                            fi.close();
                            for (ForecastEntity fe : _forecasts.values()) {
                                if (fe._program.ph_place.equals(placeCode)) {
                                    fe.updatePredict(predictList);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            try {
                br.close();
                is.close();
                fi.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    private String createId(String input)
    {

        try {
            //  2019121506_01.txt
            String[] emelents = input.split("_");
            String tempId = emelents[0];
            return tempId;
        }
        catch (Exception e)
        {

        }

        return "";
    }


    public ProgramEntity getProgramByPlace(String input)
    {
        ProgramEntity ret = null;

        try {
            for (String key : _forecasts.keySet()) {
                ForecastEntity fe = _forecasts.get(key);
                if(true == fe._program.ph_place.equals(input))
                {
                    ret = fe._program;
                }
            }
        }
        catch (Exception e)
        {

        }
        return ret;
    }

    public ArrayList<String> getSortPlace()
    {
        ArrayList<String> ret = new  ArrayList<String>();
        try{
            String[] placeOrder ={"05", "06", "08","09","03","04","07","10","01","02"};
            String[] placeValues =new String[10];
            for (String key : _forecasts.keySet()) {

                for (int i=0; i<placeOrder.length; i++) {
                    if(key.substring(8,10).equals(placeOrder[i]))
                    {
                        placeValues[i]=key;
                    }
                }
            }
            for (String place : placeValues) {
                if(null != place) {
                    if (place.length() != 0) {
                        ret.add(place);
                    }
                }
            }
        } catch (Exception e) {

        }
        return ret;
    }
    private void deleteFiles(String folder )
    {
        // シリアライズ
        try{
//            ArrayList<String> files = JraUtility.getFiles(folder);
//            for (String title : files) {
//
//                File file = new File( JraUtility.getFFile(title));
//                file.delete();
//            }
            JraUtility.deleteDirectory(folder);
        } catch (Exception e) {

        }
    }
    public String getPlaceName(String placeCode)
    {
        return JraUtility.getPlaceName(placeCode);
    }

    public void updateSelected(String programId,String raceNo)
    {

        try{
            if(0 != programId.length())
            {
                ProgramEntity pe = getProgramAt(programId);

                RaceEntity re = pe._races.get(raceNo);
                re.updateSelected();
            }

        } catch (Exception e) {

        }
    }

    public void loadSelected()
    {

        try{
            for(ForecastEntity fe : _forecasts.values())
            {
                Map<String, RaceEntity> races = fe._program._races;
                for(RaceEntity race : races.values())
                {
                    race.loadSelected();
                }
            }

        } catch (Exception e) {

        }
    }

    public ArrayList<String> checkFiles()
    {
        ArrayList<String> ret = new ArrayList<String>();
        try {
            for(ForecastEntity fe : _forecasts.values())
            {
                Map<String, RaceEntity> races = fe._program._races;
                for(RaceEntity race : races.values())
                {
                    for(HorseEntity horse:race._horses)
                    {
                        String name =  horse.rr_r_horse_name;
                        boolean retB= JraUtility.existsBPath(name);
                        boolean retE= JraUtility.existsEPath(name);
                        boolean retF= JraUtility.existsFPath(name);
                        boolean retG= JraUtility.existsGPath(name);
                        boolean retI= JraUtility.existsIPath(name);
                        if(
                            (false == retB)
                            ||
                            (false == retE)
                            ||
                            (false == retF)
                            ||
                            (false == retG)
                            ||
                            (false == retI)
                        )
                        {
                            String result = String.format("%s %s%s%s%s%s",
                                    name,
                                    (retB==true)?"*":"B",
                                    (retE==true)?"*":"E",
                                    (retF==true)?"*":"F",
                                    (retG==true)?"*":"G",
                                    (retI==true)?"*":"I"
                            );
                            ret.add(result);
                        }
                    }
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return ret;

    }
}

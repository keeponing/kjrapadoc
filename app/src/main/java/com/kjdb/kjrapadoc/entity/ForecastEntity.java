package com.kjdb.kjrapadoc.entity;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by d635257 on 2017/03/16.
 */

public class ForecastEntity extends BaseEntity implements Serializable {
    public ProgramEntity _program = null;

    public ForecastEntity() {
        _program = new ProgramEntity();
    }

    public void load(Context context, String id, String title) {

        try {
            _program.load(context, id, title);
        } catch (Exception e) {
        }
    }


    public RaceEntity getRaceAt(Integer id) {
        return _program.getRaceAt(id);
    }

    public RaceEntity getRaceAt(int id) {
        return _program.getRaceAt(id);
    }

    public HorseEntity getHorseSummaryEntityByPartOfName(String input) {
        HorseEntity ret = null;

        try {
            ret = _program.getHorseEntityByName(input);
        } catch (Exception e) {

        }
        return ret;
    }

    public void serializable() {

        try {
            if (null != _program) {
               // String path = JraUtility.getFFileAppendExt(_program._title);
                String path ="";
                File file = new File(path);
                file.getParentFile().mkdir();
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(this);
            }
        } catch (IOException e) {

        }
    }

//    public static ForecastEntity deserializable(String title) {
//
//        ForecastEntity entity = null;
//        try {
//            String path = JraUtility.getFFileAppendExt(title);
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
//            entity = (ForecastEntity) ois.readObject();
//
//        } catch (IOException | ClassNotFoundException e) {
//
//        }
//        return entity;
//    }

    public void updatePredict(List<String> predictList) {

        try {
            Map<RaceEntity,List<String>> predictRaceMap = new Hashtable<RaceEntity,List<String>>();
            RaceEntity re = null;
            for (String line : predictList) {
                if(-1 != line.indexOf(".txt"))
                {

                    //06_ダート・右_1200_2019122205_1R_3_A_txt
                    String temp = line.replace("_", ",");
                    temp = temp.replace("R","");
                            temp = temp.replace(".", ",");
                    String[] splits = temp.split(",");
                    String id =splits[0] ;
                    int race =Integer.parseInt(splits[1]) ;
                    if(id.equals(_program.ph_id)) {
                        re = getRaceAt(race);
                        if(re != null)
                        {
                            predictRaceMap.put(re, new ArrayList<String>());
                        }

                    }
                }
                else if(-1 != line.indexOf("horse_no"))
                {
                    // Nothing to do...
                }
                else
                {
                    if(null!=re) {
                        List<String> list = predictRaceMap.get(re);
                        if (null != list) {
                            list.add(line);
                        }
                    }
                }
            }

            for(RaceEntity re2 : _program._races.values())
            {
                List<String> list = predictRaceMap.get(re2);
                if(null != list)
                {
                    re2.updatePredict(list);
                    re2.calcProbTop3();
                    //re2.calcExpectTop3();
                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}


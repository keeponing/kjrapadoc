package com.kjdb.kjrapadoc.entity;

import android.content.Context;

import com.fasterxml.jackson.databind.JsonNode;
import com.kjdb.kjrapadoc.utility.JraUtility;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by d635257 on 2017/03/10.
 */

public class RaceEntity extends BaseEntity implements Serializable {
    public String rh_id ="";//ID
    public String rh_race ="";//レース
    public String rh_race_h ="";;
    public String rh_race_m ="";;
    public String rh_weather ="";//天気
    public String rh_track = "";
    public String rh_turf_condition = "";
    public String rh_dirt_condition = "";
    public String rh_race_name = "";
    public String rh_category = "";
    public String rh_rule = "";
    public String rh_weight = "";
    public String rh_distance =  "";
    public String rh_corner = "";
    public String rh_horse_count = "";
    public String rh_grade = "";
    public Integer rh_ready = 0;
    public String rh_class2 = "";
    public String rh_class3 = "";
    public String rh_class4 = "";
    public String rh_class5over = "";
    public String rh_classYoung = "";

    public ProgramEntity _parent = null;
    public  List<HorseEntity> _horses = null;
    public double _std =.0;
    public double _mean =.0;
    public double _stdOdds =.0;
    public double _meanOdds =.0;
    public String _mark = "";

    public RaceEntity()
    {
    }
    public RaceEntity(ProgramEntity parent)
    {
        _parent = parent;
        _horses = new ArrayList<HorseEntity>();
    }


    public String getTitle()
    {
        return String.format("%d %d %s", rh_race ,rh_distance ,rh_track) ;
    }

    public String getTitle3()
    {
        try {
            String tm = String.format("%s時%s分",rh_race_h, rh_race_m );
            String track = CodeTable.solveVal3(2009, rh_track);
            String place = CodeTable.solveVal2(2001, _parent.ph_place);
            return String.format("%s %sR %s %sm %s",place ,rh_race , tm, rh_distance , track  );
        }
        catch (Exception e)
        {

        }
        return "";
    }
    public String getTitle4()
    {
        String tm = String.format("%s時%s分",rh_race_h, rh_race_m );
        String track = CodeTable.solveVal2(2009, rh_track);

        return String.format(" %dR %sm  %s %s %s",
                Integer.parseInt(rh_race) ,
                rh_distance ,
                track,
                tm,
                _mark);
//        String tm = String.format("%s時%s分",rh_race_h, rh_race_m );
//        String track = CodeTable.solveVal2(2009, rh_track);
//        String weather = CodeTable.solveVal1(2011,rh_weather);
//        String tc = CodeTable.solveVal1(2010,rh_turf_condition);
//        String dc = CodeTable.solveVal1(2010,rh_dirt_condition);
//        String condition = (true == isShinba())?tc:dc;
//        String cls = getRaceClass();
//        return String.format(" %dR %sm %s %s %s %s (%s) %s",
//                Integer.parseInt(rh_race) ,
//                rh_distance ,
//                track ,
//                tm,
//                weather,
//                condition,
//                cls,
//                _mark);
    }

    public String getConditions()
    {
        String cls = getRaceClass();
        String weather = CodeTable.solveVal1(2011,rh_weather);
        String tc = CodeTable.solveVal1(2010,rh_turf_condition);
        String dc = CodeTable.solveVal1(2010,rh_dirt_condition);
        String condition = (true == isShinba())?tc:dc;
//        double stdCov = 0.0;
//        double oddsCov =0.0;
//        if(0.0 !=_mean)
//        {
//            stdCov = (_std/_mean)*100;
//        }
//
//        if(0.0 !=_meanOdds)
//        {
//            oddsCov = (_stdOdds/_meanOdds)*100;
//        }
//        return String.format("[%s]  天候:%s  馬場:%s    MO:%d   SO:%d   MP:%d   SP:%d"
        return String.format("[%s]  天候:%s  馬場:%s" ,
                cls,
                weather ,
                condition);
    }
    public List<HorseEntity> getHorsesList()
    {
        return _horses;
    }

    public static RaceEntity load(Context context, ProgramEntity parent, JsonNode rootNode) {
        RaceEntity ret = null;
        try
        {
            JsonNode rhNode = rootNode.get("rh");
            ret = new RaceEntity(parent);
            ret.rh_id  =rhNode.get("rh_id").asText();//ID
            ret.rh_race =rhNode.get("rh_race").asText();//レース
            String hassoTime = rhNode.get("rh_race_hm").asText();
            ret.rh_race_h =hassoTime.substring(0,2);
            ret.rh_race_m =hassoTime.substring(2,4);
            ret.rh_weather = rhNode.get("rh_weather").asText("");
            ret.rh_track = rhNode.get("rh_track").asText("");
            ret.rh_turf_condition =rhNode.get("rh_turf_condition").asText("");
            ret.rh_dirt_condition =rhNode.get("rh_dirt_condition").asText("");
            ret.rh_race_name = rhNode.get("rh_race_name").asText("");
            ret.rh_category =rhNode.get("rh_category").asText("");
            ret.rh_rule = rhNode.get("rh_rule").asText("");
            ret.rh_weight = rhNode.get("rh_weight").asText("");
            ret.rh_distance = rhNode.get("rh_distance").asText("");
            ret.rh_corner = rhNode.get("rh_corner").asText("");
            ret.rh_horse_count = rhNode.get("rh_horse_count").asText("");
            ret.rh_grade = rhNode.get("rh_grade").asText("");
            ret.rh_ready = rhNode.get("rh_ready").asInt(0);
            ret.rh_class2 = rhNode.get("rh_class2").asText("");
            ret.rh_class3 = rhNode.get("rh_class3").asText("");
            ret.rh_class4 = rhNode.get("rh_class4").asText("");
            ret.rh_class5over = rhNode.get("rh_class5over").asText("");
            ret.rh_classYoung = rhNode.get("rh_classYoung").asText("");

            ret._horses = HorseEntity.load(context, parent, ret, rootNode);
            ret.calcProbTop3();
//            ret.calcExpectTop3();
            ret.calcDeviationTop3();
            ret.calc3fDeviationTop3();
            ret.calcJockeyTop3();
            ret.calcSireTop3();
            ret.calcOddsTop3();
//            ret.calcMmTimeTop3();
            ret.calcMmScore3();
            for (HorseEntity he : ret._horses) {
                he.calcAdvantage();
            }
        }
        catch (Exception e)
        {
            int a=0;
        }
        return ret;
    }



    public HorseEntity getHorseSummaryEntityByPartOfName(String input) {
        HorseEntity ret = null;
        try {
            for (HorseEntity entity : _horses) {
                if(-1 != input.indexOf(entity.rr_r_horse_name))
                {
                    ret = entity;
                    break;
                }
            }
        }
        catch (Exception e){}
        return  ret;
    }

    public void calcDeviationTop3()
    {
        try {
            ArrayList<HorseEntity> sorting = new ArrayList<>();
            for(HorseEntity entity: _horses)
            {
                sorting.add(entity);
            }

            //  偏差値上位3頭
            Collections.sort(sorting, new Comparator<HorseEntity>() {
                @Override
                public int compare(HorseEntity a, HorseEntity b) {
                    return a.getAverageDeviation() - b.getAverageDeviation();
                }
            });
            Collections.reverse(sorting);
            for (int i = 0; (i < sorting.size()) && (i < 3); i++) {
                HorseEntity he = sorting.get(i);
                int temp = he.getAverageDeviation();
                he._styles.select("cs_deviation", false);

                if ((0 != temp) && (false== isShinba())) {
                    he._styles.select("cs_deviation", true);
                    he.setcAdvantage(true);
                }else{
                    he.setcAdvantage(false);
                }
            }
        }catch (Exception e)
        {

        }
    }


    public void calc3fDeviationTop3()
    {
        try {
            ArrayList<HorseEntity> sorting = new ArrayList<>();
            for(HorseEntity entity: _horses)
            {
                sorting.add(entity);
            }

            //  偏差値上位3頭
            Collections.sort(sorting, new Comparator<HorseEntity>() {
                @Override
                public int compare(HorseEntity a, HorseEntity b) {
                    return (int)(a.getLatest3f()*100.0)  - (int)(b.getLatest3f()*100.0);
                }
            });
            //Collections.reverse(sorting);
            for (int i = 0; (i < sorting.size()) && (i < 3); i++) {
                HorseEntity he = sorting.get(i);
                double temp = he.getLatest3f();
                he._styles.select("cs_deviation3f", false);
                if ((0 != temp) && (false== isShinba())) {
                    he._styles.select("cs_deviation3f", true);
                }
            }
        }catch (Exception e)
        {

        }
    }


    public void calcProbTop3()
    {
        try {
            ArrayList<HorseEntity> sorting = new ArrayList<>();
            for(HorseEntity entity: _horses)
            {
                sorting.add(entity);
            }

            //  偏差値上位3頭
            Collections.sort(sorting, new Comparator<HorseEntity>() {
                @Override
                public int compare(HorseEntity a, HorseEntity b) {
//                double ad = (4-a._exp)+a._prob;
//                double bd = (4-b._exp)+b._prob;
                double ad = a._prob;
                double bd = b._prob;
                return (int)(ad*10000) - (int)(bd*10000);
                }
            });
            Collections.reverse(sorting);
            for (int i = 0; (i < sorting.size()) && (i < 3); i++) {
                HorseEntity he = sorting.get(i);
                double temp = he._prob;
                he._styles.select("cs_pred_val", false);
                if (.0 != temp) {
                    he._styles.select("cs_pred_val", true);
                }
            }
        }catch (Exception e)
        {

        }
    }

//
//    public void calcExpectTop3()
//    {
//        try {
//            ArrayList<HorseEntity> sorting = new ArrayList<>();
//            for(HorseEntity entity: _horses)
//            {
//                sorting.add(entity);
//            }
//
//            //  偏差値上位3頭
//            Collections.sort(sorting, new Comparator<HorseEntity>() {
//                @Override
//                public int compare(HorseEntity a, HorseEntity b) {
//                    Integer ad = a._exp;
//                    Integer bd = b._exp;
//                    return ad - bd;
//                }
//            });
//            Collections.reverse(sorting);
//            for (int i = 0; (i < sorting.size()) && (i < 3); i++) {
//                HorseEntity he = sorting.get(i);
//                Integer temp = he._exp;
//                he._styles.select("cs_prob_val", false);
//                if (0 != temp) {
//                    he._styles.select("cs_prob_val", true);
//                }
//            }
//        }catch (Exception e)
//        {
//
//        }
//    }


    public void calcOddsTop3()
    {
        try {
            ArrayList<HorseEntity> sorting = new ArrayList<>();
            for(HorseEntity entity: _horses)
            {
                sorting.add(entity);
            }

            //  偏差値上位3頭
            Collections.sort(sorting, new Comparator<HorseEntity>() {
                @Override
                public int compare(HorseEntity a, HorseEntity b) {
                    Double ad = Double.parseDouble(a.rr_r_odds);
                    Double bd = Double.parseDouble(b.rr_r_odds);
                    return (int)(ad*100) - (int)(bd*100);
                }
            });
           // Collections.reverse(sorting);
            for (int i = 0; (i < sorting.size()) && (i < 3); i++) {
                HorseEntity he = sorting.get(i);
                Double temp = Double.parseDouble(he.rr_r_odds);
                he._styles.select("cs_odds_val", false);
                if (.0 != temp) {
                    he._styles.select("cs_odds_val", true);
                }
            }
        }catch (Exception e)
        {

        }
    }
//
//
//    public void calcMmTimeTop3()
//    {
//        try {
//            ArrayList<HorseEntity> sorting = new ArrayList<>();
//            for(HorseEntity entity: _horses)
//            {
//                sorting.add(entity);
//            }
//
//            //  偏差値上位3頭
//            Collections.sort(sorting, new Comparator<HorseEntity>() {
//                @Override
//                public int compare(HorseEntity a, HorseEntity b) {
//                    double ad = a.getMnTimeValue();
//                    double bd = b.getMnTimeValue();
//                    return (int)(bd*100 - ad*100);
//                }
//            });
//            Collections.reverse(sorting);
//            for (int i = 0; (i < sorting.size()) && (i < 3); i++) {
//                HorseEntity he = sorting.get(i);
//                double temp = he.getMnTimeValue();
//                he._styles.select("cs_mn_time", false);
//                if (.0 != temp) {
//                    he._styles.select("cs_mn_time", true);
//                }
//            }
//        }catch (Exception e)
//        {
//
//        }
//    }


    public void calcMmScore3()
    {
        try {
            ArrayList<HorseEntity> sorting = new ArrayList<>();
            for(HorseEntity entity: _horses)
            {
                sorting.add(entity);
            }

            //  偏差値上位3頭
            Collections.sort(sorting, new Comparator<HorseEntity>() {
                @Override
                public int compare(HorseEntity a, HorseEntity b) {
                    int ad = a.getMnScore();
                    int bd = b.getMnScore();
                    return  ad - bd;
                }
            });
            Collections.reverse(sorting);
            for (int i = 0; (i < sorting.size()) && (i < 3); i++) {
                HorseEntity he = sorting.get(i);
                double temp = he.getMnScore();
                he._styles.select("cs_mn_score", false);
                if (.0 != temp) {
                    he._styles.select("cs_mn_score", true);
                }
            }
        }catch (Exception e)
        {

        }
    }

    public void calcJockeyTop3()
    {
        try {
//            ArrayList<HorseEntity> sorting = new ArrayList<>();
//            for(HorseEntity entity: _horses)
//            {
//                sorting.add(entity);
//            }
//
//            //  偏差値上位3頭
//            Collections.sort(sorting, new Comparator<HorseEntity>() {
//                @Override
//                public int compare(HorseEntity a, HorseEntity b) {
//                    return (int)(a.rr_jk_score*100) - (int)(b.rr_jk_score*100);
//                }
//            });
//            Collections.reverse(sorting);
//            for (int i = 0; i < sorting.size() ; i++) {
//                HorseEntity he = sorting.get(i);
//                he._styles.select("cs_jcokey", false);
//            }
//            for (int i = 0; (i < sorting.size()) && (i < 3); i++) {
//                HorseEntity he = sorting.get(i);
//                Double  temp = he.rr_jk_score;
//                if (0 != temp) {
//                    he._styles.select("cs_jcokey", true);
//                }
//                else
//                {
//                    he._styles.select("cs_jcokey", false);
//                }
//            }
        }catch (Exception e)
        {

        }
    }


    public void calcSireTop3()
    {
        try {
//            ArrayList<HorseEntity> sorting = new ArrayList<>();
//            for(HorseEntity entity: _horses)
//            {
//                sorting.add(entity);
//            }
//
//            //  偏差値上位3頭
//            Collections.sort(sorting, new Comparator<HorseEntity>() {
//                @Override
//                public int compare(HorseEntity a, HorseEntity b) {
//                    return (int)(a.bs_mul_ratio*100) - (int)(b.bs_mul_ratio*100);
//                }
//            });
//            Collections.reverse(sorting);
//            for (int i = 0; i < sorting.size() ; i++) {
//                HorseEntity he = sorting.get(i);
//                he._styles.select("cs_sire", false);
//            }
//            for (int i = 0; (i < sorting.size()) && (i < 3); i++) {
//                HorseEntity he = sorting.get(i);
//                Double  temp = he.bs_mul_ratio;
//                if (0 != temp) {
//                    he._styles.select("cs_sire", true);
//                }
//                else
//                {
//                    he._styles.select("cs_jcokey", false);
//                }
//            }
        }catch (Exception e)
        {

        }
    }
    public KeibaCourseEntity loadKeibaCourse()
    {
        KeibaCourseEntity entity = null;
        try {

            String key = JraUtility.createKey(_parent.ph_place, rh_track, rh_distance);
            entity = KeibaCourseEntityFactory.getKeibaCourseEntity(key);
        }
        catch (Exception e)
        {

        }
        return  entity;
    }

    public HorseEntity getNextHorseAt(HorseEntity input)
    {

        HorseEntity target = null;
        try {
            boolean next = false;
            for(HorseEntity entity : _horses)
            {
                if(true == next)
                {
                    target = entity;
                    break;
                }
                if(entity == input)
                {
                    next = true;
                }
            }

        }
        catch (Exception e){}
        return target;
    }

    public HorseEntity getPrevHorseAt(HorseEntity input)
    {

        HorseEntity target = null;
        try {
            HorseEntity prev = null;
            for(HorseEntity entity : _horses)
            {

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

    public boolean updateSelected(RaceEntity input)
    {
        boolean ret = false;
        try {

            for(HorseEntity entity : input._horses)
            {
                HorseEntity entity2 =getHorseSummaryEntityByPartOfName(entity.rr_r_horse_name);
                if(entity2 != null)
                {
                    if(entity2._selected != entity._selected) {
                        entity2._selected = entity._selected;
                        ret = true;
                    }
                }
            }
        }
        catch (Exception e){}
        return ret;
    }

    public void updatePredict(List<String> predictList)
    {
        try
        {
            _mark ="";
            for(String line: predictList)
            {
                if(-1!=line.indexOf("*")) {
                    _mark ="*";
                }
                else if(line.equals("-"))
                {
                    _mark ="-";
                }
                else {
                    String splits[] = line.split(",");
                    String horseNo = String.format("%02d",Integer.parseInt(splits[1].trim()));
                    Double prob = Double.parseDouble(splits[4].trim());
                    Integer exp = Integer.parseInt(splits[2].trim());
                    HorseEntity entity = getHorseSummaryAt(horseNo);

                    if (null != entity) {
                        entity._exp = exp;

                        entity._prob = prob;
                    } else {
                        int aa = 0;
                    }
                }

            }
            calcMean();
            calcStd();
            calcMeanOdds();
            calcStdOdds();
        }
        catch (Exception e){}
    }

    private HorseEntity getHorseSummaryAt(String horseNo)
    {
        HorseEntity ret = null;
        try
        {
            for(HorseEntity entity: _horses)
            {
                if(entity.rr_r_horse_no.equals(horseNo))
                {
                    ret = entity;
                    break;
                }
            }
        }
        catch (Exception e){}
        return ret;
    }

    public void updateSelected()
    {

        try{

            JSONObject obj = new JSONObject();
            String raceNo = rh_race;
            String programId = rh_id;
            for(HorseEntity he: _horses)
            {
                obj.put(he.rr_r_horse_id, he._selected);
            }
            String path = JraUtility.getJPathSelected(programId, raceNo);
            try ( BufferedWriter bw = new BufferedWriter(new FileWriter(path)) ) {
                String str = obj.toString();
                bw.write(str);
            } catch ( Exception e ) {

            }

        } catch (Exception e) {

        }

    }


    public void loadSelected()
    {

        try{
            for(HorseEntity he: _horses)
            {
               he.loadSelected();
            }

        } catch (Exception e) {

        }

    }

    public Boolean isShinba()
    {
        Boolean ret = false;
        try{
            if( (rh_class2.equals("701"))
                ||
                (rh_class3.equals("701"))
                ||
                (rh_class4.equals("701"))
                ||
                (rh_class5over.equals("701"))
                ||
                (rh_classYoung.equals("701"))
            )
            {
                ret =true;
            }

        } catch (Exception e) {

        }
        return ret;
    }

    public String getRaceClass()
    {
        String ret = "";
        try{
            if(!rh_class2.isEmpty()) {
                ret = CodeTable.solveVal1(2007,rh_class2);
            }
            if(ret.isEmpty() && !rh_class3.isEmpty()) {
                ret = CodeTable.solveVal1(2007,rh_class3);
            }
            if(ret.isEmpty() && !rh_class4.isEmpty()) {
                ret = CodeTable.solveVal1(2007,rh_class4);
            }
            if(ret.isEmpty() && !rh_class5over.isEmpty()) {
                ret = CodeTable.solveVal1(2007,rh_class5over);
            }
            if(ret.isEmpty() && !rh_classYoung.isEmpty()) {
                ret = CodeTable.solveVal1(2007,rh_classYoung);
            }
            if(!ret.isEmpty())
            {
                ret = JraUtility.toHalfWidth(ret);
            }
        } catch (Exception e) {

        }
        return ret;
    }

    public List<HorseEntity> sortPredictHorses()
    {
        List<HorseEntity> ret = new ArrayList<HorseEntity>();
        try{
            ArrayList<HorseEntity> sorting = new ArrayList<>();
            for(HorseEntity entity: _horses)
            {
                sorting.add(entity);
            }

            //  偏差値上位3頭
            Collections.sort(sorting, new Comparator<HorseEntity>() {
                @Override
                public int compare(HorseEntity a, HorseEntity b) {
                    int ret = (int)(a._prob*10000) - (int)(b._prob*10000);

                    return ret;
                    //return (int)(a._prob*10000) - (int)(b._prob*10000);
                }
            });
            Collections.reverse(sorting);

            ret = sorting;
        } catch (Exception e) {

        }
        return ret;
    }

    private void calcMean()
    {
        try{
            if(0!=_horses.size())
            {
                _mean = 0.0;
                for (HorseEntity horse : _horses) {
                    _mean += horse._prob;
                }
                _mean /= _horses.size();
            }
        } catch (Exception e) {

        }
    }


    private void calcStd()
    {
        try {
            if (0 != _horses.size())
            {
                _std= 0.0;
                for (HorseEntity horse : _horses) {
                    _std += Math.pow((horse._prob - _mean), 2.0);
                }
                _std = Math.sqrt(_std / _horses.size());
            }

        } catch (Exception e) {

        }
    }

    private void calcMeanOdds()
    {
        try{
            if(0!=_horses.size())
            {
                _meanOdds = 0.0;
                for (HorseEntity horse : _horses) {
                    try{
                        _meanOdds += Double.parseDouble(horse.rr_r_odds);
                    } catch (Exception e) {

                    }
                }
                _meanOdds /= _horses.size();
            }
        } catch (Exception e) {

        }
    }


    private void calcStdOdds()
    {
        try {
            if (0 != _horses.size())
            {
                _stdOdds= 0.0;
                for (HorseEntity horse : _horses) {
                    try{
                        _stdOdds += Math.pow((Double.parseDouble(horse.rr_r_odds) - _meanOdds), 2.0);
                    } catch (Exception e) {

                    }
                }
                _stdOdds = Math.sqrt(_stdOdds / _horses.size());
            }

        } catch (Exception e) {

        }
    }




}



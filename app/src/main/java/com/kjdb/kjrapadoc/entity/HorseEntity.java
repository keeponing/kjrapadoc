package com.kjdb.kjrapadoc.entity;

import android.content.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjdb.kjrapadoc.utility.JraUtility;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by d635257 on 2017/03/10.
 */

public class HorseEntity extends BaseEntity implements Serializable {

    public String ph_id ="";
    public String ph_year="";
    public String ph_month="";
    public String ph_day="";
    public String ph_count="";
    public String ph_place="";
    public String ph_place_count="";
    public String rh_id="";
    public String rh_race="";
    public String rh_race_h="";
    public String rh_race_m="";
    public String rh_weather="";
    public String rh_turf_condition="";
    public String rh_dirt_condition="";
    public String rh_race_name="";
    public String rh_rule="";
    public String rh_weight="";
    public String rh_distance="";
    public String rh_corner="";
    public String rh_horse_count ="";
    public String rh_grade ="";
    public String rh_category = "";
    public String rr_r_id="";
    public String rr_r_race = "";
    public String rr_r_horse_id ="";
    public String rr_r_horse_no ="";
    public String rr_r_horse_name="";
    public String rr_r_rank = "";
    public String rr_r_waku ="";
    public String rr_pw ="";
    public String rr_r_blinker ="";
    public String rr_r_age ="";
    public String rr_r_gender="";
    public String rr_r_burden ="";
    public String rr_r_jockey="";
    public String rr_r_j_id="";
    public String rr_r_j_mark="";
    public String rr_r_time="";
    public String rr_r_f3_time ="";
    public String rr_r_weight ="";
    public String rr_r_gal_val="";
    public String rr_r_gal_sign="";
    public String rr_r_trainer="";
    public String rr_r_t_id="";
    public String rr_r_vote="";
    public String rr_r_corner1="";
    public String rr_r_corner2="";
    public String rr_r_corner3="";
    public String rr_r_corner4="";

    public String rr_comment="";
    public String  rr_m_feet1="";
    public String  rr_m_feet2="";
    public String  rr_m_feet3="";
    public String  rr_m_feet4="";
    public String  rr_r_father="";
    public String  rr_r_mother="";
    public String  rr_r_breeder_name="";
    public String  mn_time="";
    public String  mn_gosa="";
    public String  mn_score="";

    public Integer rr_r_breeder_tp =0;

    public String rr_r_odds ="0";


    public String  _sexStr="";
    public String _deviationStrList = "";
    public String _star ="";
    public RaceEntity _parent = null;
    public ProgramEntity _grand = null;
    public  HorseHistoryEntity[] _histories = new HorseHistoryEntity[4];

    public  PadoceEntity _padoc = new PadoceEntity();
    public ColorStyleEntity _styles = new ColorStyleEntity();
    public Boolean _selected =false;

    public double _prob = .0;
    public Integer _exp = 0;

    public Integer _advantage=0;

    public  List<HaronEntity> _haronList = new ArrayList<HaronEntity>();
    public HorseEntity()
    {
        for(int i =0; i < _histories.length; i++)
        {
            _histories[i] = new HorseHistoryEntity();
        }
    }
    public HorseEntity(ProgramEntity grand, RaceEntity parent)
    {
        _parent = parent;
        _grand = grand;
        for(int i =0; i < _histories.length; i++)
        {
            _histories[i] = new HorseHistoryEntity();
        }
    }

    public static List<HorseEntity> load(Context context, ProgramEntity grand, RaceEntity parent, JsonNode rootNode) {
        List<HorseEntity> ret = new ArrayList<HorseEntity>();
        Integer i = 0;
        try
        {
            JsonNode rrNodes = rootNode.get("rr");
            //int nodeSize = rrNodes.size();
            for( i = 0; i <18; i++) {
                String  no = String.format("%02d", i+1);
                JsonNode rrNode = rrNodes.get(no);
                if(null != rrNode) {
                    HorseEntity he = new HorseEntity(grand, parent);
                    he.ph_id = grand.ph_id;
                    he.ph_year = grand.ph_year;
                    he.ph_month = grand.ph_month;
                    he.ph_day = grand.ph_day;
                    he.ph_count = grand.ph_count;
                    he.ph_place = grand.ph_place;
                    he.ph_place_count = grand.ph_place_count;
                    he.rh_id = parent.rh_id;
                    he.rh_race = parent.rh_race;
                    he.rh_race_h = parent.rh_race_h;
                    he.rh_race_m = parent.rh_race_m;
                    he.rh_weather = parent.rh_weather;
                    he.rh_turf_condition = parent.rh_turf_condition;
                    he.rh_dirt_condition = parent.rh_dirt_condition;
                    he.rh_race_name = parent.rh_race_name;
                    he.rh_category = parent.rh_category;
                    he.rh_rule = parent.rh_rule;
                    he.rh_weight = parent.rh_weight;
                    he.rh_distance = parent.rh_distance;
                    he.rh_corner = parent.rh_corner;
                    he.rh_horse_count = parent.rh_horse_count;
                    he.rh_grade = parent.rh_grade;

                    he.rr_r_id = rrNode.get("rr_r_id").asText();
                    he.rr_r_race = rrNode.get("rr_r_race").asText();
                    he.rr_r_horse_id = rrNode.get("rr_r_horse_id").asText();
                    he.rr_r_horse_no = rrNode.get("rr_r_horse_no").asText();
                    he.rr_r_horse_name = rrNode.get("rr_r_horse_name").asText("");
                    he.rr_r_rank = rrNode.get("rr_r_rank").asText();
                    he.rr_r_waku = rrNode.get("rr_r_waku").asText();
                    he.rr_pw = rrNode.get("rr_pw").asText("");
                    he.rr_r_blinker = rrNode.get("rr_r_blinker").asText("");
                    he.rr_r_age = rrNode.get("rr_r_age").asText();
                    he.rr_r_gender = rrNode.get("rr_r_gender").asText("");
                    he.rr_r_burden = rrNode.get("rr_r_burden").asText();
                    he.rr_r_jockey = rrNode.get("rr_r_jockey").asText("");
                    he.rr_r_j_id = rrNode.get("rr_r_j_id").asText("");
                    he.rr_r_j_mark = rrNode.get("rr_r_j_mark").asText("");
                    he.rr_r_time = rrNode.get("rr_r_time").asText("");
                    he.rr_r_f3_time = rrNode.get("rr_r_f3_time").asText();
                    he.rr_r_weight = rrNode.get("rr_r_weight").asText();
                    he.rr_r_gal_val = rrNode.get("rr_r_gal_val").asText("");
                    he.rr_r_gal_sign = rrNode.get("rr_r_gal_sign").asText("");
                    he.rr_r_trainer = rrNode.get("rr_r_trainer").asText("");
                    he.rr_r_t_id = rrNode.get("rr_r_t_id").asText("");
                    he.rr_r_vote = rrNode.get("rr_r_vote").asText();
                    he.rr_r_corner1 = rrNode.get("rr_r_corner1").asText("");
                    he.rr_r_corner2 = rrNode.get("rr_r_corner2").asText("");
                    he.rr_r_corner3 = rrNode.get("rr_r_corner3").asText("");
                    he.rr_r_corner4 = rrNode.get("rr_r_corner4").asText("");
                    he.rr_m_feet1 = rrNode.get("rr_m_feet1").asText("");
                    he.rr_m_feet2 = rrNode.get("rr_m_feet2").asText("");
                    he.rr_m_feet3 = rrNode.get("rr_m_feet3").asText("");
                    he.rr_m_feet4 = rrNode.get("rr_m_feet4").asText("");
                    he.rr_r_father = rrNode.get("rr_r_father").asText("");
                    he.rr_r_mother = rrNode.get("rr_r_mother").asText("");
                    he.rr_r_breeder_name = rrNode.get("rr_r_breeder_name").asText("");
                    he.rr_r_breeder_tp = rrNode.get("rr_r_breeder_tp").asInt(0);
                    he.mn_time = rrNode.get("mn_time").asText("");
                    he.mn_gosa = rrNode.get("mn_gosa").asText("");
                    he.mn_score = rrNode.get("mn_score").asText("");

                    he.rr_r_odds = rrNode.get("rr_r_odds").asText();

                    he._sexStr = he.convertSex();
                    loadHistory(he);
                    he._haronList = HaronEntity.load( he.rr_r_horse_name);
                    he._deviationStrList = he.convertDeviationString();
                    he._padoc = PadoceEntity.load(he.rr_r_id,  he.rr_r_horse_id);
                    if(he._padoc == null) {
                        he._padoc = new PadoceEntity();
                        he._padoc.rr_r_id = he.rr_r_id;
                        he._padoc.rr_r_horse_id = he.rr_r_horse_id;
                        he._padoc.rr_r_horse_name = he.rr_r_horse_name;
                    }
                    ret.add(he);
                }
            }


        }
        catch (Exception e)
        {
            int aa=0;
        }
        return ret;
    }

    public String feetString()
    {
        String ret ="追込";
        try {

            try {
                int max = 0;
                int temp=  Integer.parseInt(rr_m_feet1);
                if(max <= temp)
                {
                    ret = "逃げ";
                    max =temp;
                }
                temp=  Integer.parseInt(rr_m_feet2);
                if(max <= temp)
                {
                    ret = "先行";
                    max =temp;
                }
                temp=  Integer.parseInt(rr_m_feet3);
                if(max <= temp)
                {
                    ret = "差し";
                    max =temp;
                }
                temp=  Integer.parseInt(rr_m_feet4);
                if(max <= temp)
                {
                    ret = "追込";
                }
            }
            catch (Exception e){
                int aaa=0;
            }

        }
        catch (Exception e){}
        return ret;
    }

    public void setcAdvantage(Boolean isDeviation)
    {
        try {
            _advantage=0;
            if (isDeviation) {
                _advantage=1;
            }
        }
        catch (Exception e){}
    }

    public void calcAdvantage()
    {
        try {
            RaceEntity re = _parent;
            KeibaCourseEntity kc = re.loadKeibaCourse();
            if (true == kc.isMatchFather(rr_r_father)) {
                _advantage++;
            }
            if (true == kc.isMatchJockey(rr_r_jockey)) {
                _advantage++;
            }
        } catch (Exception e) {

        }
    }
    public Double getLatest3f() {
        Double ret = 99.0;
        try {
            if(0 < _haronList.size() )
            {
                for(Object obj : _haronList)
                {
                    HaronEntity hhe =(HaronEntity)obj;

                    try {
                        ret = Double.parseDouble(hhe._hn_haron_time3)/10.0;
                    }
                    catch (Exception e){}
                    break;

                }
            }
//        if(0 < _histories.length )
//        {
//            List temp = Arrays.asList(_histories);
//            for(Object obj : temp)
//            {
//                HorseHistoryEntity hhe =(HorseHistoryEntity)obj;
//                if(hhe._loaded) {
//                    try {
//                        ret = Double.parseDouble(hhe.rr_r_f3_time)/10.0;
//                    }
//                    catch (Exception e){}
//                    break;
//                }
//            }
//        }
        }
        catch (Exception e){}

        return ret;
    }

    public Integer getAverageDeviation()
    {
        double average = .0;
        try {
            int total = 0;
            int demo = 0;
            for (HorseHistoryEntity entity : _histories) {
                Integer val = (int)(entity.rr_a_deviation*100);
                total += val;
                demo++;
            }
            if (0 != _histories.length) {
                average = (total / demo) + 0.5;
            }
        }
        catch (Exception e){}

        return (int)average;
    }
    public Double getAverage3fDeviation()
    {
        Double ret = .0;
        try {

            if(0 < _haronList.size() )
            {
                HaronEntity hhe = _haronList.get(0);
                try {
                    ret = Double.parseDouble(hhe._hn_haron_time3) / 10.0;

                } catch (Exception e) {
                }
            }

        }
        catch (Exception e){}

        return ret;
    }
    public String convertDeviationString() {
        String str = "";
        int max=_histories.length;
        if(false== _parent.isShinba()){
            for (int i =0; i<max;i++) {
                HorseHistoryEntity hhe = _histories[max-1-i];
                int val = (int)(hhe.rr_a_deviation*100);
                if(99 < val)
                {
                    val =99;
                }
                if (false == str.isEmpty()) {
                    str += "-";
                }

                str += String.format("%02d", val);

            }
        }
        else
        {
            str ="(新馬)";
        }

        return str;
    }



    public static void loadHistory(HorseEntity he)
    {
        try {
            String horseName = he.rr_r_horse_name;
            String path = JraUtility.getBPath(horseName);
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(path);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(path);
                Reader reader = new InputStreamReader(fis, "Shift_JIS");
                JsonNode rootRoot = mapper.readTree(reader);

                he._histories[0].load(horseName, rootRoot.get("his0"));
                he._histories[1].load(horseName, rootRoot.get("his1"));
                he._histories[2].load(horseName, rootRoot.get("his2"));
                he._histories[3].load(horseName, rootRoot.get("his3"));
            }


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public Double getExpectValue()
//    {
//        Double ret = .0;
//        try {
//            ret= _prob;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ret;
//    }
//    public Double getMnTimeValue()
//    {
//        Double ret = .0;
//        try {
//            Float tm = Float.parseFloat(mn_time);
//            ret = tm/100.0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ret;
//    }
    public Integer getMnScore()
    {
        Integer ret = 0;
        try {
            Integer tm = Integer.parseInt(mn_score);
            ret = tm;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void loadSelected()
    {

        try{
            String raceNo = rh_race;
            String programId = rh_id;
            String path = JraUtility.getJPathSelected(programId, raceNo);
            File file = new File( path );
            try ( BufferedReader br = new BufferedReader ( new FileReader( file ) ) ) {
                String text;
                while ( ( text = br.readLine() ) != null ) {
                    JSONObject obj = new JSONObject( text );
                    Boolean selected = (Boolean) obj.get(rr_r_horse_id);
                    _selected = selected;
                }
            }


        } catch (Exception e) {

        }

    }


    public int isCompapreBurden()
    {
        int ret = 0;
        try {

            Double burdenCurrent = JraUtility.toDoubleD10(rr_r_burden);
            HorseHistoryEntity pr = _histories[0];
            Double burdenPrev = JraUtility.toDoubleD10(pr.rr_r_burden);
            if(burdenCurrent< burdenPrev)
            {
                ret = -1;
            }
            if(burdenPrev< burdenCurrent)
            {
                ret = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public int getRaceSpan()
    {
        int ret = 0;
        try {
            if(false == ph_id.isEmpty()) {
                String yc = ph_year;
                String mc = ph_month;
                String dc = ph_day;
                String dateStrC = String.format("%s/%s/%s", yc, mc, dc);
                HorseHistoryEntity history = _histories[0];
                if(history != null)
                {
                    String y1  = history.ph_year;
                    String m1 = history.ph_monthDay.substring(0,2);
                    String d1 = history.ph_monthDay.substring(2,4);
                    String dateStr1 = String.format("%s/%s/%s", y1, m1, d1);

                    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
                    Date datec = sdFormat.parse(dateStrC);
                    Date date1 = sdFormat.parse(dateStr1);
                    long diff = datec.getTime()- date1.getTime();
                    long oneDayTime = 1000 * 60 * 60 * 24;
                    long diffDays = (diff) / oneDayTime;
                    long diffMonthes = diffDays/30;

                    ret = (int)diffMonthes;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    private String convertSex()
    {
        String ret = "";
        try{
            String temp = CodeTable.solveVal2(2202, rr_r_gender);
            String temp2= "";
            if(temp.equals("F"))
            {
                temp2 ="牝";
            }
            if(temp.equals("C"))
            {
                temp2 ="牡";
            }
            if(temp.equals("G"))
            {
                temp2 ="セ";
            }
            ret = temp2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}

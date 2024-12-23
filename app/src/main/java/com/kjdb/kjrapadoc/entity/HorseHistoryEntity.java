package com.kjdb.kjrapadoc.entity;


import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.Serializable;

/**
 * Created by kiich on 2017/03/15.
 */

public class HorseHistoryEntity  implements Serializable {

    public String ph_id ="";
    public String ph_year="";
    public String ph_monthDay="";
    public String ph_count="";
    public String ph_place="";
    public String ph_place_count="";

    public String rh_id="";
    public String rh_race="";
    public String rh_race_hm="";

    public String rh_weather="";
    public String rh_track="";
    public String rh_turf_condition="";
    public String rh_dirt_condition="";
    public String rh_race_name="";
    public String rh_category="";
    public String rh_rule="";
    public String rh_weight="";
    public String rh_distance="";
    public String rh_corner="";
    public String rh_horse_count ="";
    public String rh_grade ="";

    public String rh_class2="";
    public String rh_class3 ="";
    public String rh_class4 ="";
    public String rh_class5Over ="";
    public String rh_classY ="";


    public String rr_r_id="";
    public String rr_r_race = "";
    public String rr_r_horse_id ="";
    public String rr_r_horse_no ="";
    public String rr_r_horse_name="";
    public String rr_r_rank = "";
    public String rr_r_waku ="";
    public String rr_r_blinker ="";
    public String rr_r_age ="";
    public String rr_r_gender="";
    public String rr_r_burden ="";
    public String rr_r_jockey="";
    public String rr_r_j_id="";
    public String rr_r_j_mark="";
    public String rr_r_time="";
    public String rr_r_diff1="";
    public String rr_r_diff2="";
    public String rr_r_diff3="";
    public String rr_r_f3_time ="";
    public String rr_r_weight ="";
    public String rr_r_gal_val="";
    public String rr_r_gal_sign="";
    public String rr_r_trainer="";
    public String rr_r_t_id="";
    public Integer rr_r_vote=0;
    public String rr_r_corner1="";
    public String rr_r_corner2="";
    public String rr_r_corner3="";
    public String rr_r_corner4="";
    public String rr_c_comment="";
    public Integer upd=0;
    public Double rr_a_deviation =0.5;
    public Double rr_a_deviation3f =0.5;
    public Integer rr_h_prev_id1=0;
    public Integer rr_h_prev_id2=0;
    public Integer rr_h_prev_id3=0;
    public Integer rr_h_prev_id4=0;
    public Integer rr_a_race_count=0;
    public Double rr_a_win_ratio =0.0;
    public Double rr_a_mul_ratio =0.0;
    public Double rr_a_avg_distance =0.0;
    public Double rr_a_distance_diff =0.0;
    public Double rr_a_arg_dev =0.0;
    public Double rr_a_arg_3fd =0.0;
    public Double rr_a_tr_score =0.0;
    public Double rr_a_jk_score =0.0;
    public Double rr_a_sire_win_score =0.0;
    public Double rr_a_sire_mul_score =0.0;
    public Double rr_a_straight_dev =0.5;
    public Double rr_a_l_corner_dev =0.5;
    public Double rr_a_corner_dev =0.5;
    public Double rr_a_turf_dev =0.5;
    public Double rr_a_dirt_dev =0.5;
    public Double rr_r_odds =0.0;
    public Double rr_a_time_diff =0.0;
    public String rr_m_feet1="";
    public String rr_m_feet2="";
    public String rr_m_feet3="";
    public String rr_m_feet4="";
    public long _span=0;
    public boolean _loaded = false;

    public void load(String name, JsonNode rrNode)
    {
        try {
            _loaded = false;
            if(null!= rrNode) {
                ph_id = rrNode.get("ph_id").asText("");
                ph_year = rrNode.get("ph_year").asText("");
                ph_monthDay = rrNode.get("ph_monthday").asText("");

                ph_count = rrNode.get("ph_count").asText("");
                ph_place = rrNode.get("ph_place").asText("");

                ph_place_count = rrNode.get("ph_place_count").asText("");
                rh_id = rrNode.get("rh_id").asText("");
                rh_race = rrNode.get("rh_race").asText("");
                rh_race_hm = rrNode.get("rh_race_hm").asText("");
                rh_weather = rrNode.get("rh_weather").asText("");
                rh_track = rrNode.get("rh_track").asText("");
                rh_turf_condition = rrNode.get("rh_turf_condition").asText("");
                rh_dirt_condition = rrNode.get("rh_dirt_condition").asText("");
                rh_race_name = rrNode.get("rh_race_name").asText("");
                rh_category = rrNode.get("rh_category").asText("");

                rh_rule = rrNode.get("rh_rule").asText("");
                rh_weight = rrNode.get("rh_weight").asText("");
                rh_distance = rrNode.get("rh_distance").asText("");
                rh_corner = rrNode.get("rh_corner").asText("");
                rh_horse_count = rrNode.get("rh_horse_count").asText("");
                rh_grade = rrNode.get("rh_grade").asText("");
                rh_class2= rrNode.get("rh_class2").asText("");
                rh_class3 = rrNode.get("rh_class3").asText("");
                rh_class4 = rrNode.get("rh_class4").asText("");
                rh_class5Over = rrNode.get("rh_class5over").asText("");
                rh_classY = rrNode.get("rh_classYoung").asText("");

                rr_r_id = rrNode.get("rr_r_id").asText("");
                rr_r_race = rrNode.get("rr_r_race").asText("");
                rr_r_horse_id = rrNode.get("rr_r_horse_id").asText("");
                rr_r_horse_no = rrNode.get("rr_r_horse_no").asText("");
                rr_r_horse_name = rrNode.get("rr_r_horse_name").asText("");
                rr_r_rank = rrNode.get("rr_r_rank").asText("");
                rr_r_waku = rrNode.get("rr_r_waku").asText("");
                rr_r_blinker = rrNode.get("rr_r_blinker").asText("");
                rr_r_age = rrNode.get("rr_r_age").asText("");
                rr_r_gender = rrNode.get("rr_r_gender").asText("");
                rr_r_burden = rrNode.get("rr_r_burden").asText("");
                rr_r_jockey = rrNode.get("rr_r_jockey").asText("");
                rr_r_j_id = rrNode.get("rr_r_j_id").asText("");
                rr_r_j_mark = rrNode.get("rr_r_j_mark").asText("");
                rr_r_time = rrNode.get("rr_r_time").asText("");
                rr_r_diff1 = rrNode.get("rr_r_diff1").asText("");
                rr_r_diff2 = rrNode.get("rr_r_diff2").asText("");
                rr_r_diff3 = rrNode.get("rr_r_diff3").asText("");
                rr_r_f3_time = rrNode.get("rr_r_f3_time").asText("");
                rr_r_weight = rrNode.get("rr_r_weight").asText("");
                rr_r_gal_val = rrNode.get("rr_r_gal_val").asText("");
                rr_r_gal_sign = rrNode.get("rr_r_gal_sign").asText("");
                rr_r_trainer = rrNode.get("rr_r_trainer").asText("");
                rr_r_t_id = rrNode.get("rr_r_t_id").asText("");
                rr_r_vote = rrNode.get("rr_r_vote").asInt(0);
                rr_r_corner1 = rrNode.get("rr_r_corner1").asText("");
                rr_r_corner2 = rrNode.get("rr_r_corner2").asText("");
                rr_r_corner3 = rrNode.get("rr_r_corner3").asText("");
                rr_r_corner4 = rrNode.get("rr_r_corner4").asText("");
                rr_c_comment = rrNode.get("rr_c_comment").asText("");
                rr_a_deviation = rrNode.get("rr_a_deviation").asDouble(.0);
                rr_a_deviation3f = rrNode.get("rr_a_deviation3f").asDouble(.0);
                rr_h_prev_id1 = rrNode.get("rr_h_prev_id1").asInt(0);
                rr_h_prev_id2 = rrNode.get("rr_h_prev_id2").asInt(0);
                rr_h_prev_id3 = rrNode.get("rr_h_prev_id3").asInt(0);
                rr_h_prev_id4 = rrNode.get("rr_h_prev_id4").asInt(0);
                rr_a_race_count = rrNode.get("rr_a_race_count").asInt(0);
                rr_a_win_ratio = rrNode.get("rr_a_win_ratio").asDouble(.0);
                rr_a_mul_ratio = rrNode.get("rr_a_mul_ratio").asDouble(.0);
                rr_a_avg_distance = rrNode.get("rr_a_avg_distance").asDouble(.0);
                rr_a_distance_diff = rrNode.get("rr_a_distance_diff").asDouble(.0);
                rr_a_arg_dev = rrNode.get("rr_a_arg_dev").asDouble(.0);
                rr_a_arg_3fd = rrNode.get("rr_a_arg_3fd").asDouble(.0);
                rr_a_tr_score = rrNode.get("rr_a_tr_score").asDouble(.0);
                rr_a_jk_score = rrNode.get("rr_a_jk_score").asDouble(.0);
                rr_a_sire_win_score = rrNode.get("rr_a_sire_win_score").asDouble(.0);
                rr_a_sire_mul_score = rrNode.get("rr_a_sire_mul_score").asDouble(.0);
                rr_a_straight_dev = rrNode.get("rr_a_straight_dev").asDouble(.0);
                rr_a_l_corner_dev = rrNode.get("rr_a_l_corner_dev").asDouble(.0);
                rr_a_corner_dev = rrNode.get("rr_a_r_corner_dev").asDouble(.0);
                rr_a_turf_dev = rrNode.get("rr_a_turf_dev").asDouble(.0);
                rr_a_dirt_dev = rrNode.get("rr_a_dirt_dev").asDouble(.0);
                rr_r_odds = rrNode.get("rr_r_odds").asDouble(.0);
                rr_a_time_diff = rrNode.get("rr_r_time_diff").asDouble(.0);
                rr_m_feet1 = rrNode.get("rr_m_feet1").asText();
                rr_m_feet2 = rrNode.get("rr_m_feet2").asText();
                rr_m_feet3 = rrNode.get("rr_m_feet3").asText();
                rr_m_feet4 = rrNode.get("rr_m_feet4").asText();
                _loaded = true;
            }
        }
        catch (Exception e)
        {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }


    public int checkFit(HorseEntity entity)
    {
        int ret = 0;
        try
        {
            // 競馬場が一緒
            if((ph_id.substring(8))==(entity.ph_id.substring(8)))
            {
                ret++;
            }
            //距離が一緒

            if(rh_distance.equals(entity._parent.rh_distance))
            {
                ret++;
            }
            //  馬場が一緒
            if(rh_track.equals(entity._parent.rh_track))
            {
                ret++;
            }
            // JOCKEYが一緒
            if(rr_r_jockey.equals(entity.rr_r_jockey))
            {
                ret++;
            }
        }
        catch (Exception e)
        {
            int aaa = 0;
        }
        return ret;
    }

    public String getDateDecolate()
    {
        String ret = "";
        try
        {
            String y = ph_year.substring(2,4);
            String m = ph_monthDay.substring(0,2);
            String d = ph_monthDay.substring(2,4);
            ret = String.format("%s.%s.%s",
                    y,
                    m,
                    d
            );

        }
        catch (Exception e)
        {
            int aaa = 0;
        }
        return ret;
    }
}

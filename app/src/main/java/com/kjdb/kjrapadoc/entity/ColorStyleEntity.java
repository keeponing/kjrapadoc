package com.kjdb.kjrapadoc.entity;

import java.io.Serializable;
import java.util.HashMap;

public class ColorStyleEntity  implements Serializable {

    private HashMap<String, String> _styles = new HashMap<String, String>();


    public static String getBaseColor()
    {
        return  "#404040";
    }
    public static String getBlueColor()
    {
        return  "#0000FF";
    }
    public static String getRedColor()
    {
        return  "#B72121";
    }
    public static String getYellowColor()
    {
        return  "#E7E721";
    }
    public ColorStyleEntity() {
        _styles.put("cs_deviation", getBaseColor());
        _styles.put("cs_deviation3f", getBaseColor());
        _styles.put("cs_jockey", getBaseColor());
        _styles.put("cs_trainer", getBaseColor());
        _styles.put("cs_sire", getBaseColor());
        _styles.put("cs_feet", getBaseColor());
        _styles.put("cs_old", getBaseColor());
        _styles.put("cs_pred_val", getBaseColor());
        _styles.put("cs_prob_val", getBaseColor());
        _styles.put("cs_odds_val", getBaseColor());
        _styles.put("cs_mn_time", getBaseColor());
        _styles.put("cs_mn_score", getBaseColor());
        _styles.put("cs_breeder_pt", getBaseColor());
    }

    public String get(String key)
    {
        return  _styles.get(key);
    }
    public void select(String key, boolean selected)
    {
        String val = getBaseColor();
        if(true == selected)
        {
            val = getRedColor();
        }
        _styles.put(key, val);
    }
    public void hilight(String key, boolean selected)
    {
        String val =getBaseColor();
        if(true == selected)
        {
            val = getRedColor();
        }
        _styles.put(key, val);
    }
}

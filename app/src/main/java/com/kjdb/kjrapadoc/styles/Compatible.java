package com.kjdb.kjrapadoc.styles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by d635257 on 2017/03/27.
 */

public class Compatible implements Serializable {
    final public static int COMPATIBLE_WAKU                 = 1;    //  枠
    final public static int COMPATIBLE_UMABAN               = 2;   //  馬番

    final public static int COMPATIBLE_GENDER               = 3;   //  性別
    final public static int COMPATIBLE_OLD                  = 4;    //  馬齢

    final public static int COMPATIBLE_WEIGHT               = 5;   //  馬体重

    final public static int COMPATIBLE_BURDEN               = 6;    //  重量・加減

    final public static int COMPATIBLE_PASSORDER            = 7;    //  脚質

    final public static int COMPATIBLE_JOCKEY               = 8;    //  騎手

    final public static int COMPATIBLE_TRAINER              = 9;   //  調教師

    final public static int COMPATIBLE_FATHER               = 10;    //  父

    final public static int COMPATIBLE_CLASSRANK            = 11;    //  クラス

    final public static int COMPATIBLE_DEVIATION            = 12;    //  偏差値

    final public static int COMPATIBLE_LAST3F               = 13;   //  ラスト3F

    final public static int COMPATIBLE_4CORNERPOS           = 14;   //  4コーナー位置

    final public static int COMPATIBLE_VOTE                 = 15;   //  人気順

    final public static int COMPATIBLE_GOAL                 = 16;   //  ゴール

    final public static int COMPATIBLE_DISTANCE             = 17;   //  距離

    final public static int COMPATIBLE_COURSE               = 18;   //  コース

    final public static int COMPATIBLE_RACECOUNT            = 19;   //  出走数

    final public static int COMPATIBLE_PLACE                = 20;   //  競馬場

    final public static int COMPATIBLE_RACESPAN             = 21;   //  レース間隔

    final public static int COMPATIBLE_TURNCOURSE           = 22;    //  左回り・右周り

    final public static int COMPATIBLE_HORSECOUNT           = 23;   //  出走頭数

    final public static int COMPATIBLE_ULTRA                = 24;   //  ウルトラ

    final public static int COMPATIBLE_PEDIGREE              = 25;   //  血統

    final public static int COMPATIBLE_KC                   = 26;   //  コース特性


    private List<Integer> _list = new ArrayList<Integer>();

    public Compatible()
    {
        for(int i=0; i < 100; i++) {
            _list.add(0);
        }
    }

    public Integer get(Integer input)
    {
        Integer ret = 0;
        try {
            ret = _list.get(input);
        }
        catch (Exception e)
        {
            ret = 0;
        }
        return ret;
    }

    public void setJockey() {_list.set(COMPATIBLE_JOCKEY, 1);}
    public void setFather() {_list.set(COMPATIBLE_FATHER, 1);}
    public void setWaku() {_list.set(COMPATIBLE_WAKU, 1);}
    public void setPassOrder() {_list.set(COMPATIBLE_PASSORDER, 1);}
    public void setDeviation() {_list.set(COMPATIBLE_DEVIATION, 1);}
    public void setTurnCourse() {_list.set(COMPATIBLE_TURNCOURSE, 1);}
    public void setClassRank() {_list.set(COMPATIBLE_CLASSRANK, 1);}
    public void setOld() {_list.set(COMPATIBLE_OLD, 1);}
    public void setBurden() {_list.set(COMPATIBLE_BURDEN, 1);}
    public void setHorseCount() {_list.set(COMPATIBLE_HORSECOUNT, 1);}
    public void setTrainer() {_list.set(COMPATIBLE_TRAINER, 1);}
    public void setGender() {_list.set(COMPATIBLE_GENDER, 1);}
    public void setDistance() {_list.set(COMPATIBLE_DISTANCE, 1);}
    public void setLast3f() {_list.set(COMPATIBLE_LAST3F, 1);}
    public void setCornerPos() {_list.set(COMPATIBLE_4CORNERPOS, 1);}
    public void setWeight() {_list.set(COMPATIBLE_WEIGHT, 1);}
    public void setVote() {_list.set(COMPATIBLE_VOTE, 1);}
    public void setGoal() {_list.set(COMPATIBLE_GOAL, 1);}
    public void setRaceCount() {_list.set(COMPATIBLE_RACECOUNT, 1);}
    public void setCourse() {_list.set(COMPATIBLE_COURSE, 1);}
    public void setPlace() {_list.set(COMPATIBLE_PLACE, 1);}
    public void setRaceSpan() {_list.set(COMPATIBLE_RACESPAN, 1);}
    public void setUmaban() {_list.set(COMPATIBLE_UMABAN, 1);}
    public void setUltra() {_list.set(COMPATIBLE_ULTRA, 1);}
    public void setPedigree() {_list.set(COMPATIBLE_PEDIGREE, 1);}
    public void setKc() {_list.set(COMPATIBLE_KC, 1);}

    //public Integer getJockey() {return _list.get(COMPATIBLE_JOCKEY);}
    //public Integer getFather() {return _list.get(COMPATIBLE_FATHER);}
    public Integer getWaku() {return _list.get(COMPATIBLE_WAKU);}
    public Integer getPassOrder() {return _list.get(COMPATIBLE_PASSORDER);}
    public Integer getDeviation() {return _list.get(COMPATIBLE_DEVIATION);}
    public Integer getTurnCourse() {return _list.get(COMPATIBLE_TURNCOURSE);}
    public Integer getClassRank() {return _list.get(COMPATIBLE_CLASSRANK);}
    //public Integer getOld() {return _list.get(COMPATIBLE_OLD);}
    //public Integer getBurden() {return _list.get(COMPATIBLE_BURDEN);}
    public Integer getHorseCount() {return _list.get(COMPATIBLE_HORSECOUNT);}
    public Integer getTrainer() {return _list.get(COMPATIBLE_TRAINER);}
    public Integer getGender() {return _list.get(COMPATIBLE_GENDER);}
    //public Integer getDistance() {return _list.get(COMPATIBLE_DISTANCE);}
    public Integer getLast3f() {return _list.get(COMPATIBLE_LAST3F);}
    public Integer getCornerPos() {return _list.get(COMPATIBLE_4CORNERPOS);}
    //public Integer getWeight() {return _list.get(COMPATIBLE_WEIGHT);}
    //public Integer getVote() {return _list.get(COMPATIBLE_VOTE);}
    public Integer getGoal() {return _list.get(COMPATIBLE_GOAL);}
    public Integer getRaceCount() {return _list.get(COMPATIBLE_RACECOUNT);}
    public Integer getCourse() {return _list.get(COMPATIBLE_COURSE);}
    //public Integer getPlace() {return _list.get(COMPATIBLE_PLACE);}
    public Integer getRaceSpan() {return _list.get(COMPATIBLE_RACESPAN);}
    //public Integer getUmaban() {return _list.get(COMPATIBLE_UMABAN);}
    public Integer getUltra() {return _list.get(COMPATIBLE_ULTRA);}
    public Integer getPedigree() {return _list.get(COMPATIBLE_PEDIGREE);}
    public Integer getKc() {return _list.get(COMPATIBLE_KC);}

    public String getJockeyColor() {return getColor(_list.get(COMPATIBLE_JOCKEY));}
    public String getJockeyColor2() {return getColor2(_list.get(COMPATIBLE_JOCKEY));}
    public String getFatherColor() {return getColor(_list.get(COMPATIBLE_FATHER));}
    public String getFatherColor2() {return getColor2(_list.get(COMPATIBLE_FATHER));}
    public String getWakuColor() {return getColor(_list.get(COMPATIBLE_WAKU));}
    public String getPassOrderColor() {return getColor(_list.get(COMPATIBLE_PASSORDER));}
    public String getPassOrderColor2() {return getColor2(_list.get(COMPATIBLE_PASSORDER));}
    public String getDeviationColor() {return getColor(_list.get(COMPATIBLE_DEVIATION));}
    public String getDeviationColor2() {return getColor2(_list.get(COMPATIBLE_DEVIATION));}
    public String getTurnCourseColor() {return getColor(_list.get(COMPATIBLE_TURNCOURSE));}
    public String getClassRankColor() {return getColor(_list.get(COMPATIBLE_CLASSRANK));}
    public String getOldColor() {return getColor(_list.get(COMPATIBLE_OLD));}
    public String getOldColor2() {return getColor2(_list.get(COMPATIBLE_OLD));}
    public String getBurdenColor() {return getColor(_list.get(COMPATIBLE_BURDEN));}
    public String getHorseCountColor() {return getColor(_list.get(COMPATIBLE_HORSECOUNT));}
    public String getTrainerColor() {return getColor(_list.get(COMPATIBLE_TRAINER));}
    public String getGenderColor() {return getColor(_list.get(COMPATIBLE_GENDER));}
    public String getDistanceColor() {return getColor(_list.get(COMPATIBLE_DISTANCE));}
    public String getLast3fColor() {return getColor(_list.get(COMPATIBLE_LAST3F));}
    public String getLast3fColor2() {return getColor2(_list.get(COMPATIBLE_LAST3F));}
    public String getCornerPosColor() {return getColor(_list.get(COMPATIBLE_4CORNERPOS));}
    public String getWeightColor() {return getColor(_list.get(COMPATIBLE_WEIGHT));}
    public String getVoteColor() {return getColor(_list.get(COMPATIBLE_VOTE));}
    public String getGoalColor() {return getColor(_list.get(COMPATIBLE_GOAL));}
    public String getRaceCountColor() {return getColor(_list.get(COMPATIBLE_RACECOUNT));}
    public String getCourseColor() {return getColor(_list.get(COMPATIBLE_COURSE));}
    public String getPlaceColor() {return getColor(_list.get(COMPATIBLE_PLACE));}
    public String getRaceSpanColor() {return getColor(_list.get(COMPATIBLE_RACESPAN));}
    public String getUmabanColor() {return getColor(_list.get(COMPATIBLE_UMABAN));}
    public String getUltraColor() {return getColor(_list.get(COMPATIBLE_ULTRA));}
    public String getPedigreeColor() {return getColor(_list.get(COMPATIBLE_PEDIGREE));}
    public String getKcColor() {return getColor(_list.get(COMPATIBLE_KC));}
    public void clear()
    {
        for(int i=0; i < _list.size(); i++) {
            _list.set(i, 0);
        }
    }

    private String getColor(int input)
    {
        return (1 == input) ? "#B72121" : "#555555";
    }

    public String getCompatibleColor(Integer input)
    {
        return (1 == input) ? "#B72121" : "#555555";
    }

    public static String getBaseColor()
    {
        return  "#222222";
    }

    private String getColor2(int input)
    {
        return (1 == input) ? "#B72121" : "#404040";
    }

    public String getCompatibleColor2(Integer input)
    {
        return (1 == input) ? "#B72121" : "#404040";
    }

    public static String getBaseColor2()
    {
        return  "#404040";
    }
}










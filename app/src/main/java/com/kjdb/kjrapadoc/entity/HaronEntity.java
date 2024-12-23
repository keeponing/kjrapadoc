package com.kjdb.kjrapadoc.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjdb.kjrapadoc.utility.JraUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by d635257 on 2017/03/10.
 */

public class HaronEntity extends BaseEntity implements Serializable {

    public String _hn_chokyo_time ="";
    public String _hn_code ="";
    public String _hn_chokyo_date ="";
    public String _hn_haron_time4 ="";
    public String _hn_lap_time4 ="";
    public String _hn_haron_time3 ="";
    public String _hn_lap_time3 ="";
    public String _hn_haron_time2 ="";
    public String _hn_lap_time2 ="";
    public String _hn_lap_time1 ="";


    public HaronEntity()
    {

    }

    public static List<HaronEntity> load(String horseName) {
        List<HaronEntity> ret = new ArrayList<HaronEntity>();
        Integer i = 0;
        try
        {
            String path = JraUtility.getIPathFile(horseName);
            File file = new File(path);
            if(true == file.exists()) {
                // JSONファイルの読み込み
                ObjectMapper mapper = new ObjectMapper();
                FileInputStream fis = new FileInputStream(file);
                Reader reader = new InputStreamReader(fis, "Shift_JIS");
                JsonNode rootNode = mapper.readTree(reader);
                int nodeSize = rootNode.size();
                for (i = 0; i < nodeSize; i++) {
                    String no = String.format("hanro_%01d", i);
                    JsonNode rrNode = rootNode.get(no);
                    if (null != rrNode) {
                        HaronEntity he = new HaronEntity();
                        he._hn_chokyo_time = rrNode.get("hn_chokyo_time").asText();
                        he._hn_code = rrNode.get("hn_code").asText();
                        he._hn_chokyo_date = rrNode.get("hn_chokyo_date").asText();
                        he._hn_haron_time4 = rrNode.get("hn_haron_time4").asText();
                        he._hn_lap_time4 = rrNode.get("hn_lap_time4").asText("");
                        he._hn_haron_time3 = rrNode.get("hn_haron_time3").asText();
                        he._hn_lap_time3 = rrNode.get("hn_lap_time3").asText();
                        he._hn_haron_time2 = rrNode.get("hn_haron_time2").asText("");
                        he._hn_lap_time2 = rrNode.get("hn_lap_time2").asText("");
                        he._hn_lap_time1 = rrNode.get("hn_lap_time1").asText("");
                        ret.add(he);
                    }
                }
            }
        }
        catch (Exception e)
        {
            int aa=0;
        }
        return ret;
    }


}

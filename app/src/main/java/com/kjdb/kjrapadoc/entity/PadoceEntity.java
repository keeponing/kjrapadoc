package com.kjdb.kjrapadoc.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjdb.kjrapadoc.utility.JraUtility;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by d635257 on 2017/03/10.
 */

public class PadoceEntity extends BaseEntity implements Serializable {
    public String rr_r_id ="";//ID
    public String rr_r_horse_id ="";

    public String rr_r_horse_name ="";
    public Integer chk_indication=0;
    public Integer chk_taken=0;
    public Integer chk_ayumi=0;
    public Integer chk_twoperson=0;
    public Integer chk_glossy=0;
    public Integer chk_excitement=0;
    public Integer chk_thicker=0;
    public Integer chk_sweating=0;

    public PadoceEntity()
    {
    }

    public static PadoceEntity load(String programId, String horse_id) {
        PadoceEntity ret = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String fileName = JraUtility.getKPath(programId, horse_id);
            File file = new File(fileName);
            if (file.exists()) {

                // JSONファイルを読み込み
                JsonNode nodes = mapper.readTree(file);

                ret = new PadoceEntity();
                ret.rr_r_id = nodes.get("rr_r_id").asText();
                ret.rr_r_horse_id = nodes.get("rr_r_horse_id").asText();
                ret.rr_r_horse_name= nodes.get("rr_r_horse_name").asText();
                ret.chk_indication = nodes.get("chk_indication").asInt();
                ret.chk_taken = nodes.get("chk_taken").asInt();
                ret.chk_ayumi = nodes.get("chk_ayumi").asInt();
                ret.chk_twoperson = nodes.get("chk_twoperson").asInt();
                ret.chk_glossy = nodes.get("chk_glossy").asInt();
                ret.chk_excitement = nodes.get("chk_excitement").asInt();
                ret.chk_thicker = nodes.get("chk_thicker").asInt();
                ret.chk_sweating = nodes.get("chk_sweating").asInt();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public static void save(String programId, String horse_id, PadoceEntity entity ) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String fileName = JraUtility.getKPath(programId, horse_id);
            File file = new File(fileName);
            // 親ディレクトリが存在しない場合は作成
            file.getParentFile().mkdirs();

            // PadoceEntityオブジェクトをJSONファイルにシリアライズして保存
            mapper.writeValue(file, entity);
            //System.out.println("JSONファイルに保存しました: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}



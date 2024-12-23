package com.kjdb.kjrapadoc.utility;

import android.os.Environment;

import com.kjdb.kjrapadoc.AppDomain;
import com.kjdb.kjrapadoc.R;
import com.kjdb.kjrapadoc.entity.CodeTable;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by kiich on 2017/02/26.
 */

public class JraUtility {

    public static final int SWIPE_MIN_DISTANCE = 220;
    public static final int SWIPE_THRESHOLD_VELOCITY = 300;
    public static final String BASE_PATH = "_KjraDir";
    public static final String SUB_HOT_PATH="hot";
    public static final String SUB_COLD_PATH="cld";
    public static final String SUB_STATIC_PATH="stc";

    public static void saveStockFile(AppDomain domain)
    {
        try
        {
            domain._forecasts.serializable();
        }
        catch (Exception e) {

        }

    }
    public static double sigmoid(double x) {
            return 1.0/(1.0+Math.exp(-(-1.5*x)));
    }

    public static String getAPath() {
        String ret = String.format("%s/%s/%s/A",
                Environment.getExternalStorageDirectory().getPath(),
                BASE_PATH,
                SUB_HOT_PATH
                );
        return ret;
    }
    public static String getAPathRace(String title) {
        String root = getAPath();
        return String.format("%s/%s", root, title);
    }

    public static String getBPath() {
        String ret = String.format("%s/%s/%s/B",
                Environment.getExternalStorageDirectory().getPath(),
                BASE_PATH,
                SUB_COLD_PATH
        );
        return ret;
        //return Environment.getExternalStorageDirectory().getPath() + "/_KjraDir/B";
    }
    public static String getBPath(String name) {
        String root = getBPath();
        String fileName = name + ".txt";
        return root + "/" + fileName;
    }

    public static boolean existsBPath(String name)
    {
        String path = getBPath(name);
        return exists(path);
    }

    public static String getCPath() {
        String ret = String.format("%s/%s/%s/C",
                Environment.getExternalStorageDirectory().getPath(),
                BASE_PATH,
                SUB_STATIC_PATH
        );
        return ret;
        //return Environment.getExternalStorageDirectory().getPath() + "/_KjraDir/C";
    }
    public static String getCPathRace(String title) {
        String root = getCPath();
        return String.format("%s/%s.txt", root, title);
    }
    public static String getDPath() {
        String ret = String.format("%s/%s/%s/D",
                Environment.getExternalStorageDirectory().getPath(),
                BASE_PATH,
                SUB_STATIC_PATH
        );
        return ret;
        //return Environment.getExternalStorageDirectory().getPath() + "/_KjraDir/D";
    }
    public static String getDPathCodeTable() {
        String root = getDPath();
        return String.format("%s/%s", root, "CodeTable.csv");
    }
    public static String getEPath() {
        String ret = String.format("%s/%s/%s/E",
                Environment.getExternalStorageDirectory().getPath(),
                BASE_PATH,
                SUB_COLD_PATH
        );
        return ret;
        //return Environment.getExternalStorageDirectory().getPath() + "/_KjraDir/E";
    }

    public static String getEPathFile(String name) {
        String root = getEPath();
        return String.format("%s/%s.png", root, name);
    }
    public static boolean existsEPath(String name)
    {
        String path = getEPathFile(name);
        return exists(path);
    }

    public static String getGPath() {
        String ret = String.format("%s/%s/%s/G",
                Environment.getExternalStorageDirectory().getPath(),
                BASE_PATH,
                SUB_COLD_PATH
        );
        return ret;
        //return Environment.getExternalStorageDirectory().getPath() + "/_KjraDir/G";
    }
    public static String getGPathFile(String title) {
        String root = getGPath();
        String fileName = title + ".png";
        return root + "/" + fileName;
    }
    public static boolean existsGPath(String name)
    {
        String path = getGPathFile(name);
        return exists(path);
    }

    public static String getFPath() {
        String ret = String.format("%s/%s/%s/F",
                Environment.getExternalStorageDirectory().getPath(),
                BASE_PATH,
                SUB_COLD_PATH
        );
        return ret;
       // return Environment.getExternalStorageDirectory().getPath() + "/_KjraDir/F";
    }

    public static String getFPathFile(String key) {
        String root = getFPath();
        return String.format("%s/%s.png", root, key);
    }
    public static boolean existsFPath(String name)
    {
        String path = getFPathFile(name);
        return exists(path);
    }
    public static String getH1Path() {
        String ret = String.format("%s/%s/%s/H1",
                Environment.getExternalStorageDirectory().getPath(),
                BASE_PATH,
                SUB_HOT_PATH
        );
        return ret;
        //return Environment.getExternalStorageDirectory().getPath() + "/_KjraDir/H";
    }

    public static String getHEPath() {
        String ret = String.format("%s/%s/%s/HE",
                Environment.getExternalStorageDirectory().getPath(),
                BASE_PATH,
                SUB_HOT_PATH
        );
        return ret;
        //return Environment.getExternalStorageDirectory().getPath() + "/_KjraDir/H";
    }
    public static String getHPathFile(String key) {
        String root = getH1Path();
        return String.format("%s/%s.txt", root, key);
    }

    public static String getIPath() {
        String ret = String.format("%s/%s/%s/I",
                Environment.getExternalStorageDirectory().getPath(),
                BASE_PATH,
                SUB_COLD_PATH
        );
        return ret;
        //return Environment.getExternalStorageDirectory().getPath() + "/_KjraDir/I";
    }
    public static String getIPathFile(String name) {
        String root = getIPath();
        return String.format("%s/%s.txt", root, name);
    }


    public static String getKPath() {
        String ret = String.format("%s/%s/%s/K",
                Environment.getExternalStorageDirectory().getPath(),
                BASE_PATH,
                SUB_HOT_PATH
        );
        return ret;
        //return Environment.getExternalStorageDirectory().getPath() + "/_KjraDir/B";
    }
    public static String getKPath(String programId, String horseId) {
        String root = getKPath();
        String fileName = programId+ "_"+horseId + ".txt";
        return root + "/" + fileName;
    }

    public static boolean existsIPath(String name)
    {
        String path = getIPathFile(name);
        return exists(path);
    }
    public static String getJPath() {
        String ret = String.format("%s/%s/%s/J",
                Environment.getExternalStorageDirectory().getPath(),
                BASE_PATH,
                SUB_HOT_PATH
        );
        return ret;
    }
    public static String getJPathSelected(String programId, String raceNo) {
        String root = getJPath();
        return String.format("%s/%s_%s.txt", root, programId, raceNo);
    }
    public static String createKey(String place, String trufOrDirt, String distance) {
        String turf = "";
        String temp1 = CodeTable.solveVal3(2001, place);
        String temp2 = CodeTable.solveVal1(2009, trufOrDirt);
        if (-1 != temp2.indexOf("芝")) {
            turf = "芝";
        }
        if (-1 != temp2.indexOf("ダート")) {
            turf = "ダート";
        }
        return String.format("%s_%s_%sm",  temp1, turf, distance);
    }

    // ファイル一覧取得
    public static ArrayList<String> getFiles(String path) {
        ArrayList<String> filesList = new ArrayList<String>();
        try {

            File file = new File(path);
            File list[] = file.listFiles();
            if (null != list) {
                for (int i = 0; i < list.length; i++) {
                    filesList.add(list[i].getName());
                }
            }
        } catch (Exception e) {
        }

        return filesList;
    }

    public static String getColorAt(int id) {
        String ret ="";
        if(id <0)
        {
            ret = "#FFFFFF";
        }else {
            String[] color = {"#A0A0A0", "Black", "#b72222", "Blue", "#FFFF00", "#22B14C", "#ffc800", "#ffaec8"};
            id = id % 8;
            ret = color[id];
        }
        return ret;
    }

    public static String getFrameTextColorAt(int id) {
        String ret ="";
        if(id <0)
        {
            ret = "#FFFFFF";
        }else {
            String[] color = {"White", "White", "White", "White", "Black", "Black", "Black", "Black"};
            id = id % 8;
            ret = color[id];
        }
        return ret;
    }
    public static String convertUmaban(String input) {
        String ret = "";
        Integer temp = Integer.parseInt(input);
        switch (temp) {
            case 1:
                ret = "❶";
                break;
            case 2:
                ret = "❷";
                break;
            case 3:
                ret = "❸";
                break;
            case 4:
                ret = "❹";
                break;
            case 5:
                ret = "❺";
                break;
            case 6:
                ret = "❻";
                break;
            case 7:
                ret = "❼";
                break;
            case 8:
                ret = "❽";
                break;
            case 9:
                ret = "❾";
                break;
            case 10:
                ret = "❿";
                break;
            case 11:
                ret = "⓫";
                break;
            case 12:
                ret = "⓬";
                break;
            case 13:
                ret = "⓭";
                break;
            case 14:
                ret = "⓮";
                break;
            case 15:
                ret = "⓯";
                break;
            case 16:
                ret = "⓰";
                break;
            case 17:
                ret = "⓱";
                break;
            case 18:
                ret = "⓲";
                break;
        }
        return ret;
    }
    public static String convertUmaban2(String input) {
        String ret = "";
        Integer temp = Integer.parseInt(input);
        switch (temp) {
            case 1:
                ret = "①";
                break;
            case 2:
                ret = "②";
                break;
            case 3:
                ret = "③";
                break;
            case 4:
                ret = "④";
                break;
            case 5:
                ret = "⑤";
                break;
            case 6:
                ret = "⑥";
                break;
            case 7:
                ret = "⑦";
                break;
            case 8:
                ret = "⑧";
                break;
            case 9:
                ret = "⑨";
                break;
            case 10:
                ret = "⑩";
                break;
            case 11:
                ret = "⑪";
                break;
            case 12:
                ret = "⑫";
                break;
            case 13:
                ret = "⑬";
                break;
            case 14:
                ret = "⑭";
                break;
            case 15:
                ret = "⑮";
                break;
            case 16:
                ret = "⑯";
                break;
            case 17:
                ret = "⑰";
                break;
            case 18:
                ret = "⑱";
                break;
        }
        return ret;
    }
    public static String appendSpace(String input, int max) {
        if (max < input.length()) {
            input = input.substring(0, max);
        }
        int len = input.length();
        for (int i = max; len <= i; i--) {
            input += "　";
        }
        return input;
    }


    public static ArrayList<Integer> getRaces() {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        ret.add(1);
        ret.add(2);
        ret.add(3);
        ret.add(4);
        ret.add(5);
        ret.add(6);
        ret.add(7);
        ret.add(8);
        ret.add(9);
        ret.add(10);
        ret.add(11);
        ret.add(12);
        return ret;
    }


    public static String getPlaceName(String placeCode) {
        //***	開催場からコードを算出
        String place = "";
        if (-1 != placeCode.indexOf("01")) place = "札幌";
        else if (-1 != placeCode.indexOf("02")) place = "函館";
        else if (-1 != placeCode.indexOf("03")) place = "福島";
        else if (-1 != placeCode.indexOf("04")) place = "新潟";
        else if (-1 != placeCode.indexOf("05")) place = "東京";
        else if (-1 != placeCode.indexOf("06")) place = "中山";
        else if (-1 != placeCode.indexOf("07")) place = "中京";
        else if (-1 != placeCode.indexOf("08")) place = "京都";
        else if (-1 != placeCode.indexOf("09")) place = "阪神";
        else if (-1 != placeCode.indexOf("10")) place = "小倉";

        return place;
    }

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
    public static String getTrimJockeyName(String input) {
        String temp = input.replace("．", "");
        temp = temp.replace(" ", "");
        return temp;
    }

    public static String getRankString(String input)
    {
        String ret ="";
        try {
            if(-1 != input.indexOf("新馬"))
            {
                ret = "新馬";
            }
            if(-1 != input.indexOf("未勝利"))
            {
                ret = "未勝利";
            }
            if(-1 != input.indexOf("500万"))
            {
                ret = "500万";
            }
            if(-1 != input.indexOf("1000万"))
            {
                ret = "1000万";
            }
            if(-1 != input.indexOf("1600万"))
            {
                ret = "1600万";
            }
            if(-1 != input.indexOf("オープン"))
            {
                ret = "オープン";
            }
        }
        catch(Exception e)
        {

        }

        return  ret;
    }

    public static String getIdToDateString2(String input)
    {
        String ret ="";
        try {
            if( input.length() == 10) {
                String y = input.substring(2, 4);
                String m = input.substring(4, 6);
                String d = input.substring(6, 8);
                ret = y+"."+m+"."+d;
            }
        }
        catch(Exception e)
        {

        }

        return  ret;
    }

    public static  String formatTurfOrDirt(String input)
    {
        String ret = "";
        try {
            if(-1 != input.indexOf("Turf")) {
                ret = "芝";
            }
            else if(-1 != input.indexOf("Dirt")) {
                ret = "ダ";
            }
            else if(-1 != input.indexOf("Sand")) {
                ret = "サ";
            }

        }
        catch(Exception e)
        {

        }

        return  ret;
    }

    public static  String trimString(String input, int size)
    {
        try {
            input = input.substring(0,size);
        }
        catch(Exception e)
        {
        }

        return  input;
    }


    public static int [] getUmabasiraId()
    {
        int resId[] = {
                R.id.lbl_umabasira_1,
                R.id.lbl_umabasira_2,
                R.id.lbl_umabasira_3,
                R.id.lbl_umabasira_4,
                R.id.lbl_umabasira_5,
                R.id.lbl_umabasira_6,
                R.id.lbl_umabasira_7,
                R.id.lbl_umabasira_8,
                R.id.lbl_umabasira_9,
                R.id.lbl_umabasira_10,
                R.id.lbl_umabasira_11,
                R.id.lbl_umabasira_12,
                R.id.lbl_umabasira_13,
                R.id.lbl_umabasira_14,
                R.id.lbl_umabasira_15,
                R.id.lbl_umabasira_16,
                R.id.lbl_umabasira_17,
                R.id.lbl_umabasira_18,
        };
        return resId;
    }


    public static int [] getChkPadocId()
    {
        int resId[] = {
                R.id.chk_indication,
                R.id.chk_taken,
                R.id.chk_ayumi,
                R.id.chk_twoperson,
                R.id.chk_glossy,
                R.id.chk_excitement,
                R.id.chk_thicker,
                R.id.chk_sweating
        };
        return resId;
    }
    public static Double  toDoubleD10(String input)
    {
        Double ret=.0;
        try {
            ret = Double.parseDouble(input);
            ret =ret/10.0;
        }
        catch(Exception e)
        {
            return null;
        }
        return ret;
    }


    public static String [] getRaceTitle()
    {
        String ret[] = {
                "12R",
                "11R",
                "10R",
                "9R",
                "8R",
                "7R",
                "6R",
                "5R",
                "4R",
                "3R",
                "2R",
                "1R",
        };
        return ret;
    }

    /**
     * 対象パスのディレクトリの削除を行う.<BR>
     * ディレクトリ配下のファイル等が存在する場合は<BR>
     * 配下のファイルをすべて削除します.
     *
     * @param dirPath 削除対象ディレクトリパス
     * @throws Exception
     */
    public static void deleteDirectory(final String dirPath) throws Exception {
        File file = new File(dirPath);
        recursiveDeleteFile(file);
    }

    /**
     * 対象のファイルオブジェクトの削除を行う.<BR>
     * ディレクトリの場合は再帰処理を行い、削除する。
     *
     * @param file ファイルオブジェクト
     * @throws Exception
     */
    private static void recursiveDeleteFile(final File file) throws Exception {
        // 存在しない場合は処理終了
        if (!file.exists()) {
            return;
        }
        // 対象がディレクトリの場合は再帰処理
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                recursiveDeleteFile(child);
            }
        }
        // 対象がファイルもしくは配下が空のディレクトリの場合は削除する
        file.delete();
    }


    public static String  formatDate(String input)
    {
        String ret="00/00";
        try {
            ret = String.format("%s/%s",
                    input.substring(4,6),
                    input.substring(6,8)
            );
        }
        catch(Exception e)
        {
            return null;
        }
        return ret;
    }

    public static String  formatMilsec(String input)
    {
        String ret="00.0";
        try {
            Double temp = Double.parseDouble(input)/10.0;
            ret = String.format("%3.1f",
                    temp
            );
        }
        catch(Exception e)
        {
            return null;
        }
        return ret;
    }

    public static String  formatMilsecUpto99(String input)
    {
        String ret="00.0";
        try {
            Double temp = Double.parseDouble(input)/10.0;
            if(100.0 <= temp)
            {
                temp =99.9;
            }
            ret = String.format("%3.1f",
                    temp
            );
        }
        catch(Exception e)
        {
            return null;
        }
        return ret;
    }

    public static String  formatTime9999ToMsms(String input)
    {
        String ret="0.00.00";
        try {
            if(4 == input.length())
            {
                String m = input.substring(0,1);
                String s = input.substring(1,3);
                String ms = input.substring(3,4);
                ret = String.format("%1s.%2s.%1sms",
                        m,
                        s,
                        ms
                );
            }

        }
        catch(Exception e)
        {
            return null;
        }
        return ret;
    }


    public  static String getClassColor(int input)
    {
        String ret="#AAAAAA";
        switch(input) {
            case 5:
                ret = "#C8FFC8";  // 新馬
                break;
            case 4:
                ret = "#90FF90";  //未勝利
                break;
            case 3:
                ret = "#FFF090"; // 500万円以下
                break;
            case 2:
                ret = "#FFC820";  // 1000万円以下
                break;
            case 1:
                ret = "#FFA020";  // 1600万円以下
                break;
            case 0:
                ret = "#A8A8FF";  // オープン
                break;
        }

        return ret;

    }

    public static void createFolder(String path)
    {
        try {

            File file = new File(path);
            file.mkdir();

        } catch (Exception e) {

        }
    }

    public static boolean exists(String path)
    {
        boolean ret = false;
        try {

            File file = new File(path);
            ret = file.exists();

        } catch (Exception e) {

        }
        return ret;
    }

    /**
     * 「全角数字」を「半角数字」へ変換処理を実施する。
     *
     * @param s 対象文字列
     * @return 変換結果
     */
    public static String toHalfWidth(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (0xFF10 <= c && c <= 0xFF19) {
                sb.setCharAt(i, (char) (c - 0xFEE0));
            }
        }
        return sb.toString();
    }
}
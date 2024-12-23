package com.kjdb.kjrapadoc.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.kjdb.kjrapadoc.AppDomain;
import com.kjdb.kjrapadoc.R;
import com.kjdb.kjrapadoc.entity.ColorStyleEntity;
import com.kjdb.kjrapadoc.entity.HorseEntity;
import com.kjdb.kjrapadoc.entity.KeibaCourseEntity;
import com.kjdb.kjrapadoc.entity.PadoceEntity;
import com.kjdb.kjrapadoc.entity.ProgramEntity;
import com.kjdb.kjrapadoc.entity.RaceEntity;
import com.kjdb.kjrapadoc.utility.JraUtility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by d635257 on 2017/04/11.
 */

public class PadocAdapter extends BaseAdapter {

    private Context _context;
    private List<Object> _items;
    private List<Object> _viewTypes;
    public  boolean _modeLarge =false;
    public  boolean _modeB =false;

    private LayoutInflater _inflater;

    public PadocAdapter(Context context) {
        this._context = context;
        this._items = new ArrayList<Object>();
        this._viewTypes = new ArrayList<Object>();
        this._viewTypes.add(String.class);
        this._viewTypes.add(HorseEntity.class);
        this._inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public synchronized  void setProgram(ProgramEntity pe)
    {
        try {
            if( false == setProgramInner(pe, false))
            {
                setProgramInner(pe, true);
            }

        }
        catch (Exception e){
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }


    public Boolean setProgramInner(ProgramEntity pe, Boolean strictShowMode)
    {
        Boolean ret =false;
        try {
            AppDomain domain = (AppDomain)_context.getApplicationContext();
            Date date1 = new Date();  //(1)Dateオブジェクトを生成
            SimpleDateFormat sdf1 = new SimpleDateFormat("Hm");
            String temp3 = sdf1.format(date1);
            if(null != pe) {
                _items.clear();
                ArrayList<Integer> list = JraUtility.getRaces();
                for (Integer raceNo : list) {
                    try {
                        RaceEntity re = pe.getRaceByString(raceNo);
                        if(strictShowMode== false) {
                            if (true == domain._dev_show_mode) {
                                int h = Integer.parseInt(re.rh_race_h);
                                int m = Integer.parseInt(re.rh_race_m);
                                int temp2 = h * 100 + m;
                                if (temp2 < Integer.valueOf(temp3) - 20) {
                                    continue;
                                }
                            }
                        }

                        _items.add(re.getTitle4());
                        if(false == re.rh_race_name.isEmpty()) {
                            _items.add(re.rh_race_name);
                        }
                        _items.add(re.getConditions());

                        KeibaCourseEntity kc = re.loadKeibaCourse();
                        if(null != kc) {
                            _items.add(kc._00rough);
                            _items.add(String.format("%s/%s",kc._01characteristic,kc._02waku));
                            String temp = kc.getJockeyString();
                            _items.add(temp.isEmpty()?"-":temp);

                        }

                        List<HorseEntity> horses = re._horses;
                        if(true == domain._predictSortMode)
                        {
                            //domain._forecasts.updatePredict(domain._predictPrecisionMode);
                            horses = re.sortPredictHorses();
                        }
                        for (HorseEntity he : horses) {
                            _items.add(he);

                        }
                        _items.add("");
                        ret =true;
                    } catch(Exception e1){}

                }
                _items.add("");
                _items.add("");
                _items.add("");
            }
        }
        catch (Exception e){
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
        return ret;
    }
    @Override
    public int getCount() {
        return _items.size();
    }

    @Override
    public Object getItem(int position) {
        try {
            return _items.get(position);
        }
        catch (Exception e){
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View customItemView = convertView;
        try {
            Object baseItem = _items.get(position);
            if (baseItem instanceof String) {
                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    customItemView = inflater.inflate(R.layout.row_top_summary, parent, false);

                } else {
                    customItemView = convertView;
                }

                setString(customItemView, baseItem);

            } else if (baseItem instanceof HorseEntity) { // HorseSummaryEntity
                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    customItemView = inflater.inflate(R.layout.row_top_horse, parent, false);
                    //   Log.d(this.getClass().getName(), "custom 2 created");
                } else {
                    customItemView = convertView;
                    //   Log.d(this.getClass().getName(), "custom 2 recycled");
                }


                HorseEntity he = (HorseEntity) baseItem;

                Resources res = _context.getResources();
                customItemView.setBackgroundColor(getBackColor(he));
                int colSelected = res.getColor(R.color.colorSelected);
                int colScore = res.getColor(R.color.colorScore);
                if ((he.rr_r_rank.equals("01")) || (he.rr_r_rank.equals("02")) || (he.rr_r_rank.equals("03"))) {
                    customItemView.setBackgroundColor(colScore);
                }
                else if (true == he._selected) {
                    customItemView.setBackgroundColor(colSelected);
                }
                String horse_id = he.rr_r_horse_id;

                int[] padocId = JraUtility.getChkPadocId();
                if(he.rr_r_horse_name.equals("フジルバーブ"))
                {
                    int t =0;
                    t++;
                }
                if(he._padoc != null)
                {
                    setIndication(customItemView, he._padoc.chk_indication );
                    setTaken(customItemView, he._padoc.chk_taken);
                    setAyumi(customItemView, he._padoc.chk_ayumi);
                    setTwoperson(customItemView, he._padoc.chk_twoperson);
                    setGlossy(customItemView, he._padoc.chk_glossy);
                    setExcitement(customItemView, he._padoc.chk_excitement);
                    setThicker(customItemView,  he._padoc.chk_thicker);
                    setSweating(customItemView, he._padoc.chk_sweating);
                }
                for (int p : padocId) {
                    CheckBox cb = customItemView.findViewById(p);
                    cb.setTag(he);
                    cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            try {
                                HorseEntity he = (HorseEntity)buttonView.getTag();
                                if(null != he) {
                                    String programId = he.rr_r_id;
                                    String horse_id = he.rr_r_horse_id;
                                    Integer resId = buttonView.getId();
                                    Integer val = (true == isChecked) ? 1 : 0;
                                    switch (resId) {
                                        case R.id.chk_indication:
                                            he._padoc.chk_indication = val;
                                            break;
                                        case R.id.chk_taken:
                                            he._padoc.chk_taken = val;
                                            break;
                                        case R.id.chk_ayumi:
                                            he._padoc.chk_ayumi = val;
                                            break;
                                        case R.id.chk_twoperson:
                                            he._padoc.chk_twoperson = val;
                                            break;
                                        case R.id.chk_glossy:
                                            he._padoc.chk_glossy = val;
                                            break;
                                        case R.id.chk_excitement:
                                            he._padoc.chk_excitement = val;
                                            break;
                                        case R.id.chk_thicker:
                                            he._padoc.chk_thicker = val;
                                            break;
                                        case R.id.chk_sweating:
                                            he._padoc.chk_sweating = val;
                                            break;
                                    }
                                    PadoceEntity.save(programId, horse_id, he._padoc);
//                                ViewGroup parentView = (ViewGroup) buttonView.getParent();
//                                parentView.invalidate();
                                }
                            }
                            catch (Exception e)
                            {
                                Log.d(this.getClass().getName(), e.getLocalizedMessage());
                            }
                        }
                    });

                }
                setRank(customItemView, he);
                setHorseNo(customItemView, he);
                setHorseName(customItemView, he);

            }
            else
            {
                setString(customItemView, baseItem);
            }
            if (customItemView == null) {
                LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                customItemView = inflater.inflate(R.layout.row_top_horse, parent, false);
                //   Log.d(this.getClass().getName(), "custom 2 created");
            }
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
        return customItemView;
    }

    @Override
    public int getViewTypeCount() {
        return _viewTypes.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object item = _items.get(position);
        if(null != item) {
            return _viewTypes.indexOf(item.getClass());
        }
        return 0;
    }

    private void setString(View view, Object baseItem)
    {
        try {
            String baseColor = ColorStyleEntity.getBaseColor();
            String item = (String) baseItem;
            TextView tv = (TextView) view.findViewById(R.id.title);
            tv.setText(item);
            Resources res = _context.getResources();
            int colNonSelected = res.getColor(R.color.colorSummary);
            if (true == item.isEmpty()) {
                view.setBackgroundColor(Color.parseColor("#444444"));
            } else {
                view.setBackgroundColor(colNonSelected);//"#357020"));
            }
            tv.setTextColor(Color.parseColor(baseColor));
        }
        catch (Exception e)
        {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    private void setRank(View view, HorseEntity he)
    {
        try {
            String temp = "";
            TextView tv = (TextView) view.findViewById(R.id.rank);


            if(false == he.rr_r_rank.isEmpty()) {
                temp="";
                if(he.rr_r_rank.equals("01"))
                {
                    temp="①";
                }
                if(he.rr_r_rank.equals("02"))
                {
                    temp="②";
                }
                if(he.rr_r_rank.equals("03"))
                {
                    temp="③";
                }
                temp = String.format("%s", temp);
            }
//            }else
//        }
            tv.setText(temp);
            tv.setTextColor(Color.parseColor("black"));
        }
        catch (Exception e)
        {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    private void setHorseNo(View view, HorseEntity he)
    {
        try
        {
            TextView tv = (TextView) view.findViewById(R.id.umaban);
            Integer frame = Integer.parseInt(he.rr_r_waku)- 1;
            String col1 = JraUtility.getColorAt(frame);
            String textcol1 = JraUtility.getFrameTextColorAt(frame);
            tv.setBackgroundColor(Color.parseColor(col1));
            tv.setTextColor(Color.parseColor(textcol1));
            tv.setText(JraUtility.convertUmaban2(he.rr_r_horse_no));
        }
        catch (Exception e)
        {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    private void setHorseName(View view, HorseEntity he)
    {
        try
        {
            String baseColor = ColorStyleEntity.getBaseColor();
            TextView tv = (TextView) view.findViewById(R.id.name);
            tv.setText(JraUtility.appendSpace(he.rr_r_horse_name, 9));
            if(true == _modeLarge)
            {
                tv.setText(JraUtility.appendSpace(he.rr_r_horse_name, 9)+"\n\n");
            }
            else
            {
                tv.setText(JraUtility.appendSpace(he.rr_r_horse_name, 9));
            }
            tv.setTextColor(Color.parseColor(baseColor));

        }
        catch (Exception e)
        {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }
    private void setIndication(View view, Integer val)
    {
        try {
            CheckBox cb = view.findViewById(R.id.chk_indication);
            cb.setTag(null);
            cb.setChecked((val==0)?false:true);
        }
        catch (Exception e)
        {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    //踏出
    private void setTaken(View view, Integer val)
    {
        try {
            CheckBox cb = view.findViewById(R.id.chk_taken);
            cb.setTag(null);
            cb.setChecked((val==0)?false:true);
        }
        catch (Exception e)
        {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    //歩様
    private void setAyumi(View view, Integer val)
    {
        try {
            CheckBox cb = view.findViewById(R.id.chk_ayumi);
            cb.setTag(null);
            cb.setChecked((val==0)?false:true);
        }
        catch (Exception e)
        {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    //二人引く
    private void setTwoperson(View view, Integer val)
    {
        try {
            CheckBox cb = view.findViewById(R.id.chk_twoperson);
            cb.setTag(null);
            cb.setChecked((val==0)?false:true);
        }
        catch (Exception e)
        {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    //毛艶
    private void setGlossy(View view, Integer val)
    {
        try {
            CheckBox cb = view.findViewById(R.id.chk_glossy);
            cb.setTag(null);
            cb.setChecked((val==0)?false:true);
        }
        catch (Exception e)
        {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    //入れ込み
    private void setExcitement(View view, Integer val)
    {
        try {
            CheckBox cb = view.findViewById(R.id.chk_excitement);
            cb.setTag(null);
            cb.setChecked((val==0)?false:true);
        }
        catch (Exception e)
        {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    //体重
    private void setThicker(View view, Integer val)
    {
        try {
            CheckBox cb = view.findViewById(R.id.chk_thicker);
            cb.setTag(null);
            cb.setChecked((val==0)?false:true);
        }
        catch (Exception e)
        {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }


    //発汗
    private void setSweating(View view, Integer val)
    {
        try {
            CheckBox cb = view.findViewById(R.id.chk_sweating);
            cb.setTag(null);
            cb.setChecked((val==0)?false:true);
        }
        catch (Exception e)
        {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    private int getBackColor(HorseEntity he)
    {
        int ret = 0;
        try
        {
            Resources res = _context.getResources();
            AppDomain domain = (AppDomain)_context.getApplicationContext();
            int colSelected = res.getColor(R.color.colorSelected);
            int colNonSelected = res.getColor(R.color.colorNonSelected);

            int colScore = res.getColor(R.color.colorScore);
            if ((he.rr_r_rank.equals("01")) || (he.rr_r_rank.equals("02")) || (he.rr_r_rank.equals("03"))) {
                ret = colScore;
            }
            else if (true == he._selected) {
                ret = colSelected;
            }
            else
            {
                if(false == domain._predictPrecisionMode)
                {
                    ret = Color.parseColor("#DFDFFF");
                }else
                {
                    ret = colNonSelected;
                }
            }
        }
        catch (Exception e)
        {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
        return ret;
    }
}

package com.kjdb.kjrapadoc;


import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.kjdb.kjrapadoc.entity.ForecastContainer;
import com.kjdb.kjrapadoc.entity.ProgramEntity;
import com.kjdb.kjrapadoc.entity.RaceEntity;

public class AppDomain extends Application {
    private static final String TAG = "AppDomain";
    public ForecastContainer _forecasts =null;
    public String _currentProgramId ="";
    public Integer _currentRace = 0;
    public Boolean _predictSortMode=false;
    public Boolean _predictPrecisionMode =true;
    public Boolean _dev_show_mode =true;
    /**
     * アプリケーションの起動時に呼び出される
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        _forecasts =  new ForecastContainer(this);
        _currentProgramId = "";
        _currentRace = 0;
    }

    /**
     * アプリケーション終了時に呼び出される
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTerminate");

    }

    public ProgramEntity getCurrentProgram()
    {
        return _forecasts.getProgramAt(_currentProgramId);
    }

    public RaceEntity getRaceAt(Intent intent)
    {
        Integer raceNo = (Integer) intent.getSerializableExtra("race");

        ProgramEntity pe = getCurrentProgram();
        return pe.getRaceAt(raceNo);
    }

    public String getCurrentProgramTitle()
    {
        String ret ="";
        ProgramEntity pe = _forecasts.getProgramAt(_currentProgramId);
        if(null != pe)
        {
            ret = pe.getTitle();
        }
        return ret;
    }
}

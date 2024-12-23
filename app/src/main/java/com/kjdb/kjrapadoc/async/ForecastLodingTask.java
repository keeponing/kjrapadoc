package com.kjdb.kjrapadoc.async;

import android.os.AsyncTask;

import com.kjdb.kjrapadoc.entity.ForecastContainer;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;
import static android.os.Process.setThreadPriority;

/**
 * Created by kiich on 2017/03/19.
 */

public class ForecastLodingTask extends AsyncTask<ForecastContainer, Void, String>{

    public ForecastLodingTask() {
        setThreadPriority(THREAD_PRIORITY_BACKGROUND);

    }

    @Override
    protected String doInBackground(ForecastContainer... params) {
        String ret = "";
        ForecastContainer enitiy = params[0];
        try {
            enitiy.load();

        } catch (Exception e) {
            e.printStackTrace();
            ret = null;
        }
        return ret;
    }

    // このメソッドは非同期処理の終わった後に呼び出されます
    @Override
    protected void onPostExecute(String result) {
        if(null!=result) {

        }
    }

}

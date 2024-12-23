package com.kjdb.kjrapadoc.async;

import android.os.AsyncTask;

import com.kjdb.kjrapadoc.entity.KeibaCourseEntityFactory;

/**
 * Created by kiich on 2017/03/19.
 */

public class KeibaCourseLodingTask  extends AsyncTask<Void, Void, String> {

    public KeibaCourseLodingTask() {
        //  setThreadPriority(THREAD_PRIORITY_BACKGROUND);

    }

    @Override
    protected String doInBackground(Void... params) {
        String ret = "";
        try {
            KeibaCourseEntityFactory.loadAll();
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

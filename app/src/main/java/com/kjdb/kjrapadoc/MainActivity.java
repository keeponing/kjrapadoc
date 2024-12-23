package com.kjdb.kjrapadoc;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.Thing;
import com.kjdb.kjrapadoc.adapter.PadocAdapter;
import com.kjdb.kjrapadoc.async.KeibaCourseLodingTask;
import com.kjdb.kjrapadoc.entity.CodeTable;
import com.kjdb.kjrapadoc.entity.ForecastContainer;
import com.kjdb.kjrapadoc.entity.ForecastEntity;
import com.kjdb.kjrapadoc.entity.HorseEntity;
import com.kjdb.kjrapadoc.entity.ProgramEntity;
import com.kjdb.kjrapadoc.entity.RaceEntity;
import com.kjdb.kjrapadoc.utility.JraUtility;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;



public class MainActivity extends AppCompatActivity
            implements
            NavigationView.OnNavigationItemSelectedListener,
            CompoundButton.OnCheckedChangeListener
{
    private static final int PICK_CONTACT_REQUEST = 1000;
    private Activity _activity = null;

    private Map<String,String> _pwList = new Hashtable<String,String>();
    private int _lastRaceNo =0;
    private GestureDetector _gestureDetector = null;
    private static Boolean _isLFileOutput =  false;


    private final Handler _handler = new Handler();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
   // private GoogleApiClient client;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createKFolder();
        //おまじない
        try {
            Class.forName("android.os.AsyncTask");
        } catch (ClassNotFoundException e) {
        }
        _activity = this;

        try {
            AppDomain domain = (AppDomain) getApplication();
            setupButtons();
            setupCheckbox();
            setupDrawerLayout();

            setupGestureDetector();
            setupListview();
            setupSwitch();
            setupPlaceButton();
            setupScrollBar();
            setupNavigationView();


//            AppDomain domain = (AppDomain)getApplication();
//            domain._forecasts.updatePredict(domain._predictPrecisionMode);
            // 重い処理はバックグラウンドで実施。
            try{

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        loadForecast();
                        loadCodeTable();
                        checkData();
                        loadKeibaCourse();
                        AppDomain domain = (AppDomain)getApplication();
                        domain._forecasts.updatePredict(domain._predictPrecisionMode);
//                        domain._forecasts.loadSelected();
//                        ListView lv = (ListView) findViewById(R.id.listView);
//                        TopAdapter adapter = (TopAdapter)lv.getAdapter();
//                        adapter.notifyDataSetChanged();
                    }
                }).start();

            }catch (Exception e)
            {
                Log.d(this.getClass().getName(), e.getLocalizedMessage());
            }
        }catch (Exception e)
        {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        try{
            // Check which request we're responding to
            if (requestCode == PICK_CONTACT_REQUEST) {
//                ProgramEntity pe = (ProgramEntity) intent.getSerializableExtra("program");
//                UpdateSelectHorsesRequest task = new UpdateSelectHorsesRequest(_activity);
//                task.execute(pe);

                updateUmabasira(_lastRaceNo);


            }
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
       try
       {
           updateTopActivity();
       }
       catch (Exception e) {
           Log.d(this.getClass().getName(), e.getLocalizedMessage());
       }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // GridView以外の領域でスワイプした場合
//        if(_gestureDetector.onTouchEvent(event)){
//            return true;
//        }

        return false;
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
     //   client.connect();
    //    AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
     //   AppIndex.AppIndexApi.end(client, getIndexApiAction());
     //   client.disconnect();
    }
    @Override
    public void onBackPressed() {
        try
        {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try{
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();


        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        try
        {
            // Handle navigation view item clicks here.
            int id = item.getItemId();
            boolean isClose = true;
            if (id == R.id.jra_delete_files) {

                new AlertDialog.Builder(_activity)
                        .setTitle("データ削除")
                        .setMessage("データを削除しますか？")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AppDomain domain = (AppDomain)getApplication();
                                domain._forecasts.deleteAllFile();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();

            } else if (id == R.id.jra_race_predict_sort) {
                sortPredict();

            } else if (id == R.id.jra_race_just_time) {
                AppDomain domain = (AppDomain)getApplication();
                boolean isChecked = !domain._dev_show_mode;
                changeCurrenttime( isChecked);

            } else if (id == R.id.jra_race_check) {
                checkData();
            }

            if (true == isClose) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
        return true;
    }
    private void checkData()
    {
        try{
            AppDomain domain = (AppDomain)getApplication();
            ArrayList<String> results = domain._forecasts.checkFiles();
            if(results.size()!=0)
            {
                String message = String.format("%d\r\n",results.size());
                int max = (results.size()<10)?results.size():10;
                for(int i=0; i<max;i++)
                {
                    message += results.get(i)+"\r\n";
                }

                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this, "Check OK.", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    private void updateTopActivity()
    {
        try{
            AppDomain domain = (AppDomain)getApplication();
            String title =domain.getCurrentProgramTitle();
            _activity.setTitle(title);

            ProgramEntity pe = domain.getCurrentProgram();

            ListView lv = (ListView) findViewById(R.id.listView);
            PadocAdapter adapter = (PadocAdapter)lv.getAdapter();

            adapter.setProgram(pe);

            adapter.notifyDataSetChanged();


            // ListView全体をリフレッシュ
            lv.invalidateViews();

        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    private void setupPlace(NavigationView navigationView) {
        try
        {
            int resId[] = getPlaceResId();
            AppDomain domain = (AppDomain)getApplication();
            ArrayList<String> list = domain._forecasts.getProgramTitles();

//            String path = JraUtility.getDPath();
//            ArrayList<String> list = JraUtility.getFiles(path);

            Menu menu = navigationView.getMenu();

            for (int i = 0; i < resId.length; i++) {
                MenuItem item = (MenuItem) menu.findItem(resId[i]);
                if (i < list.size()) {
                    item.setTitle(list.get(i));
                    item.setVisible(true);
                } else {
                    item.setVisible(false);
                }
            }
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    private int [] getPlaceResId() {
        int resId[] = {
                R.id.jra_place_1,
                R.id.jra_place_2,
                R.id.jra_place_3,
                R.id.jra_place_4,
                R.id.jra_place_5,
                R.id.jra_place_6
        };
        return resId;
    }


    private boolean showHorseHistoryActivity()
    {
        try
        {
            updateTopActivity();
            loadPlaceButton();
            Integer race  = getRaceDisplayRace();
            updateUmabasira(race);
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
        return true;
    }

    private int getRaceDisplayRace()
    {
        int ret = 0;
        try {
            ListView lv = (ListView) findViewById(R.id.listView);
            ArrayList<Integer> races =  JraUtility.getRaces();
            PadocAdapter adapter = (PadocAdapter) lv.getAdapter();
            for (int i = 0; i < lv.getChildCount(); i++) {
                int position = lv.getFirstVisiblePosition() + i;
                Object obj = adapter.getItem(position);
                if (obj instanceof String) {
                    String temp = (String) obj;
                    for (int j = races.size() - 1; 0 <= j; j--) {
                        String  race = String.format("%dR", races.get(j));
                        if (-1 != temp.indexOf(race)) {
                            ret = j + 1;
                            break;
                        }
                    }
                    if (0 !=  ret) {
                        break;
                    }
                }
            }
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }

        return ret;
    }

    private void setupNavigationView()
    {
        try
        {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            setupPlace(navigationView);
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    private void setupPlaceButton()
    {
        try
        {

            Button buttons [] = new Button[]{
                    (Button) findViewById(R.id.btn_place_1),
                    (Button) findViewById(R.id.btn_place_2),
                    (Button) findViewById(R.id.btn_place_3),
            };

            Button b1 = (Button) findViewById(R.id.btn_place_1);
            b1.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // ボタンがクリックされた時に呼び出されます
                            Button button = (Button) v;
                            AppDomain domain = (AppDomain)getApplication();
                            String val = (String)button.getText();
                            ProgramEntity pe = domain._forecasts.getProgramByPlace(CodeTable.solveVal3ToCode(2001,val));
                            if(pe != null) {
                                domain._currentProgramId = pe.ph_id;
                                showHorseHistoryActivity();
                            }
                        }
                    });
            Button b2 = (Button) findViewById(R.id.btn_place_2);
            b2.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // ボタンがクリックされた時に呼び出されます
                            Button button = (Button) v;
                            AppDomain domain = (AppDomain)getApplication();
                            String val = (String)button.getText();
                            ProgramEntity pe = domain._forecasts.getProgramByPlace(CodeTable.solveVal3ToCode(2001,val));
                            if(pe != null) {
                                domain._currentProgramId = pe.ph_id;
                                showHorseHistoryActivity();
                            }
                        }
                    });
            Button b3 = (Button) findViewById(R.id.btn_place_3);
            b3.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // ボタンがクリックされた時に呼び出されます
                            Button button = (Button) v;
                            AppDomain domain = (AppDomain)getApplication();
                            String val = (String)button.getText();
                            ProgramEntity pe = domain._forecasts.getProgramByPlace(CodeTable.solveVal3ToCode(2001,val));
                            if(pe != null) {
                                domain._currentProgramId = pe.ph_id;
                                showHorseHistoryActivity();
                            }
                        }
                    });
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }

    }

    private void setupScrollBar()
    {
        try
        {

            ListView lv = (ListView) findViewById(R.id.listView);
            lv.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView absListView, int i) {

                }

                @Override
                public void onScroll(AbsListView view, int totalItemCount, int firstVisibleItem, int visibleItemCount)
                {

                    try {
                        Integer race  = getRaceDisplayRace();
                        if(race != _lastRaceNo)
                        {
                            updateUmabasira(race);
                            _lastRaceNo = race;
                        }

                    }
                    catch (Exception e){
                        Log.d(this.getClass().getName(), e.getLocalizedMessage());
                    }
                }
            });
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }
    private void updateUmabasira(int race)
    {
        try {
            AppDomain domain = (AppDomain)getApplication();
            int[] hasira = JraUtility.getUmabasiraId();

            ProgramEntity pe = domain.getCurrentProgram();
            ArrayList<Integer> list =  JraUtility.getRaces();
            RaceEntity re = pe.getRaceAt(list.get(race));
            for(int u:hasira)
            {
                TextView tv = (TextView) findViewById(u);
                tv.setVisibility(View.INVISIBLE);
            }
            int i = 0;
            for(HorseEntity he: re._horses)
            {
                String col1 = JraUtility.getColorAt(Integer.parseInt(he.rr_r_waku) - 1);
                TextView tv = (TextView) findViewById(hasira[i++]);
                tv.setTextColor(Color.parseColor(col1));
                tv.setVisibility(View.VISIBLE);
                Resources res = getResources();
                int colSelected = res.getColor(R.color.colorSelected);
                if (true == he._selected) {
                    tv.setBackgroundColor(colSelected);
                }
                else
                {
                    tv.setBackgroundColor(Color.parseColor("#c8dda3"));
                }
            }

            ListView lv = (ListView) findViewById(R.id.listView);

            PadocAdapter adapter = (PadocAdapter)lv.getAdapter();
            adapter.notifyDataSetChanged();
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }
    private void loadPlaceButton()
    {
        Button buttons [] = new Button[]{
            (Button) findViewById(R.id.btn_place_1),
            (Button) findViewById(R.id.btn_place_2),
            (Button) findViewById(R.id.btn_place_3),
        };

        try {

            Integer count =0;
            AppDomain domain = (AppDomain)getApplication();
            ArrayList<String> lst = domain._forecasts. getSortPlace();
            //for (String key : domain._forecasts._forecasts.keySet()) {
            for(String key : lst){
                ForecastEntity fe = domain._forecasts._forecasts.get(key);
                if ((null != fe) && (count < buttons.length)) {
                    String place = CodeTable.solveVal3(2001,fe._program.ph_place);
                    buttons[count++].setText(place);
                }
            }
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }

    }
    private void setupSwitch() {

        try {

            Switch switch2 = (Switch) findViewById(R.id.switch2);
            switch2.setOnCheckedChangeListener(this);

        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupDrawerLayout()
    {
        try
        {
            Window window = getWindow();
            window.setStatusBarColor(Color.parseColor("#478740"));
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }
    private void setupListview()
    {
        try
        {

            PadocAdapter adapter = new PadocAdapter(this);
            ListView lv = (ListView) findViewById(R.id.listView);

            lv.setAdapter(adapter);
            lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

            OnTouchListenerImpl touchListener = new OnTouchListenerImpl();
            OnItemClickListenerImpl onClickListener = new OnItemClickListenerImpl();
          //  OnDoubleTapListenerImpl  onDoubleTapListener = new OnDoubleTapListenerImpl();
            // タッチリスナーを設定
            lv.setOnTouchListener(touchListener);
            lv.setOnItemClickListener(onClickListener);
            // ロングクリックリスナーを設定
            lv.setOnItemLongClickListener(onClickListener);


            //リスト項目が選択された時のイベントを追加
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView lv = (ListView)parent;
                try {
                    Object baseItem = lv.getItemAtPosition(position);
                    if(null != lv) {
                        if (baseItem instanceof String) {

//                            String race = findShownRace();
//                            transitionDetailActivity(race);
                        } else if (baseItem instanceof HorseEntity) { // HorseSummaryEntity
                            try {
                                PadocAdapter adapter = (PadocAdapter)lv.getAdapter();
                                if(true == adapter._modeLarge)  {
                                    if (baseItem instanceof HorseEntity) { // HorseSummaryEntity
                                        HorseEntity he = (HorseEntity)baseItem;
                                        if(null != he) {
                                            he._selected = (false == he._selected )?true:false;


                                            adapter.notifyDataSetChanged();
                                        }
                                    }
                                }
                            }
                            catch(Exception e){}
                        }
                    }
                }
                catch(Exception e){}
                }
            });
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }


    private void setupCheckbox()
    {
        try
        {
//            ListView lv = (ListView) findViewById(R.id.listView);
//            PadocAdapter adapter = new PadocAdapter(this);
//            lv.setAdapter(adapter);
//            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View customItemView = inflater.inflate(R.layout.row_top_horse, lv, false);
//            int[] padocId = JraUtility.getChkPadocId();
//
//            for(int p:padocId) {
//                CheckBox cb =  customItemView.findViewById(p);
//                cb.setChecked(true);
//            }

        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    private void loadForecast()
    {
        try
        {
            AppDomain domain = (AppDomain)getApplication();
            domain._forecasts = new ForecastContainer(this);
            domain._forecasts.load();
//            Toast.makeText(_activity, "ロード中...", Toast.LENGTH_LONG).show();
//            ForecastLodingTask task = new ForecastLodingTask();
//            task.execute(_forecasts);

            int i =0;
            while((domain._currentProgramId.equals("")) &&(i <6)) {
                Thread.sleep(100);
                i++;
            }
            showHorseHistoryActivity();
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    private void loadKeibaCourse()
    {
        try
        {

            KeibaCourseLodingTask task = new KeibaCourseLodingTask();
            task.execute();
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }


    private void setupGestureDetector()
    {
        MainActivity.OnGestureListenerImpl gestureListener = new MainActivity.OnGestureListenerImpl();
        _gestureDetector = new GestureDetector(this, gestureListener);
    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        try
        {
            //changeCurrenttime( isChecked);
            AppDomain domain = (AppDomain)getApplication();
            domain._predictPrecisionMode = isChecked;
            String message=new String();
            if( domain._predictPrecisionMode ==false)
            {
                message = String.format("Rough mode.");
            }
            else
            {
                message = String.format("Precision mode.");
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            domain._forecasts.updatePredict(domain._predictPrecisionMode);
            domain._forecasts.loadSelected();
            ListView lv = (ListView) findViewById(R.id.listView);
            PadocAdapter adapter = (PadocAdapter)lv.getAdapter();
            ProgramEntity pe =domain.getCurrentProgram();
            adapter.setProgram(pe);
            adapter.notifyDataSetChanged();
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }
    private void changeCurrenttime(boolean isChecked)
    {
        try
        {
            ListView lv = (ListView) findViewById(R.id.listView);
            PadocAdapter adapter = (PadocAdapter)lv.getAdapter();
            // adapter._modeLarge = isChecked;
            AppDomain domain = (AppDomain)getApplication();
            ProgramEntity pe =domain.getCurrentProgram();
            String[] races = JraUtility.getRaceTitle();
            domain._dev_show_mode = isChecked;
            adapter.setProgram(pe);
            adapter.notifyDataSetChanged();

        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    private void setupButtons()
    {
        try
        {
            ImageButton btn1 = (ImageButton)findViewById(R.id.bt1);

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        ListView lv = (ListView) findViewById(R.id.listView);
                        PadocAdapter adapter = (PadocAdapter)lv.getAdapter();
                        adapter._modeB = (true == adapter._modeB)?false:true;
                        AppDomain domain = (AppDomain)getApplication();
                        ProgramEntity pe = domain.getCurrentProgram();
                        adapter.setProgram(pe);
                        adapter.notifyDataSetChanged();

                    }
                    catch(Exception e){}
                }
            });

            ImageButton btn2 = (ImageButton)findViewById(R.id.bt2);
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                    }
                    catch(Exception e){}
                }
            });
            ImageButton btn3 = (ImageButton)findViewById(R.id.bt3);
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {

                    }
                    catch(Exception e){}
                }
            });

            ImageButton btn5 = (ImageButton)findViewById(R.id.bt5);
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {

                    }
                    catch(Exception e){}
                }
            });
            ImageButton btn6 = (ImageButton)findViewById(R.id.bt6);
            btn6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Snackbar.make(view, "結果更新中...", Snackbar.LENGTH_LONG).show();
                        AppDomain domain = (AppDomain) getApplication();
                        loadForecast();
                        domain._forecasts.updatePredict(domain._predictPrecisionMode);
                        domain._forecasts.loadSelected();
                        ProgramEntity pe =domain.getCurrentProgram();
                        ListView lv = (ListView) findViewById(R.id.listView);
                        PadocAdapter adapter = (PadocAdapter)lv.getAdapter();
                        adapter.setProgram(pe);
                        adapter.notifyDataSetChanged();
                    } catch (Exception e) {
                    }
                }
            });
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }

    private void loadCodeTable()
    {
        try
        {
            //  初期化
            CodeTable.getInstance();

        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }
    }
    private void sortPredict()
    {
        try
        {
            AppDomain domain = (AppDomain)getApplication();
            domain._predictSortMode= !domain._predictSortMode;
            ListView lv = (ListView) findViewById(R.id.listView);
            PadocAdapter adapter = (PadocAdapter)lv.getAdapter();
            ProgramEntity pe =domain.getCurrentProgram();

            adapter.setProgram(pe);
            adapter.notifyDataSetChanged();

        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }

    }

    private void createKFolder()
    {
        try
        {
            JraUtility.createFolder(JraUtility.getKPath());
        }
        catch (Exception e) {
            Log.d(this.getClass().getName(), e.getLocalizedMessage());
        }

    }


    private class OnItemClickListenerImpl implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // 任意の処理
            try {
                ListView lv = (ListView)parent;
                Object baseItem = lv.getItemAtPosition(position);

            }
            catch (Exception e) {
                Log.d(this.getClass().getName(), e.getLocalizedMessage());
            }
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            // 任意の処理
            ListView lv = (ListView)parent;
            try {
                Object baseItem = lv.getItemAtPosition(position);
                if(null != lv) {
                    PadocAdapter adapter = (PadocAdapter)lv.getAdapter();

                    if (baseItem instanceof String) {
                        String temp = (String)baseItem;
                        String[] races = JraUtility.getRaceTitle();
                        AppDomain domain = (AppDomain)getApplication();
                        ProgramEntity program = domain.getCurrentProgram();
                        if(null != program) {

                            for(String race : races) {
                                if(-1 != temp.indexOf(race)) {
                                    RaceEntity re = program.getRaceAt(Integer.parseInt(race));
                                    domain._currentProgramId = re.rh_id;
                                    domain._dev_show_mode = (domain._dev_show_mode==true)?false:true;
                                    adapter.setProgram(program);
                                    break;
                                }
                            }
                        }
                    } else if (baseItem instanceof HorseEntity) { // HorseSummaryEntity
                        if (baseItem instanceof HorseEntity) { // HorseSummaryEntity
                            HorseEntity he = (HorseEntity)baseItem;
                            if(null != he) {
                                String raceNo = he.rh_race;
                                he._selected = (false == he._selected )?true:false;
                                AppDomain domain = (AppDomain)getApplication();
                                String program_id = domain._currentProgramId;
                                domain._forecasts.updateSelected(program_id, raceNo);
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
            catch (Exception e) {
                Log.d(this.getClass().getName(), e.getLocalizedMessage());
            }
            return false;
        }

    }

    private class OnTouchListenerImpl implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if (_gestureDetector.onTouchEvent(event)) {
                // ACTION_CANCELイベントでViewのタッチイベントをキャンセル(問題点1の回避)
                MotionEvent cancelEvent = MotionEvent.obtain(event);
                cancelEvent.setAction(MotionEvent.ACTION_CANCEL);
                view.onTouchEvent(cancelEvent);

                return true;
            }

            return false;
        }

    }

    public class UpdateSelectHorsesRequest extends AsyncTask<ProgramEntity, Void, String> {
        private Activity _activity;

        public UpdateSelectHorsesRequest(Activity activity) {

            // 呼び出し元のアクティビティ
            this._activity = activity;
        }

        @Override
        protected String doInBackground(ProgramEntity... params) {
            String ret ="";

            try {
                ProgramEntity pe = params[0];
                for (RaceEntity re: pe._races.values()) {
                    AppDomain domain = (AppDomain)getApplication();
                    RaceEntity race2 = domain._forecasts.getRaceAt(pe.ph_id, re.rh_race);
                    if(null != race2)
                    {
                        if(true == race2.updateSelected(re)) {
                            Integer raceNo  = getRaceDisplayRace();
                            updateUmabasira(raceNo);

                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.d(this.getClass().getName(), e.getLocalizedMessage());
                ret = null;
            }
            return ret;
        }

        // このメソッドは非同期処理の終わった後に呼び出されます
        @Override
        protected void onPostExecute(String in) {
            try {
                ListView lv = (ListView) findViewById(R.id.listView);
                PadocAdapter adapter = (PadocAdapter)lv.getAdapter();
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                Log.d(this.getClass().getName(), e.getLocalizedMessage());
            }

        }
    }
    private class OnGestureListenerImpl implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {

            return false;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            // ロングクリックのフラグが立っていたらフリック処理をキャンセル(問題点2の回避)



            try {

                if (event1.getX() - event2.getX() > JraUtility.SWIPE_MIN_DISTANCE && Math.abs(velocityX) > JraUtility.SWIPE_THRESHOLD_VELOCITY) {
                    // 開始位置から終了位置の移動距離が指定値より大きい
                    // X軸の移動速度が指定値より大きい
                    //   Toast.makeText(_activity, "右から左", Toast.LENGTH_SHORT).show();

                } else if (event2.getX() - event1.getX() > JraUtility.SWIPE_MIN_DISTANCE && Math.abs(velocityX) > JraUtility.SWIPE_THRESHOLD_VELOCITY) {
                    // 終了位置から開始位置の移動距離が指定値より大きい
                    // X軸の移動速度が指定値より大きい
                    //    Toast.makeText(_activity, "左から右", Toast.LENGTH_SHORT).show();

                }


            } catch (Exception e) {
                // nothing
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent event) {
        }

        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent event) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return false;
        }
    }


}


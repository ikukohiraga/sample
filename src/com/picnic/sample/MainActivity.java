package com.picnic.sample;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnApiListener{

    ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // デフォルトのリストに表示する人を追加
        String[] members = { "mhidaka", "rongon_xp", "kacchi0516", "kobashinG",
                "seit", "kei_i_t", "furusin_oriver" };
         
        mLv = (ListView) findViewById(R.id.nearFriend);
 
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, members);
 
        mLv.setAdapter(adapter);
        
   	 
        String uid = "ikuko";
        double lat = 37.42;
   	 	double lon = 138.62;
   	 	int status = 1;
   	 	String message = "hello";
   	 
   	 
        String searchUrl = "http://picnic.mydns.jp:3000/match/search?uid=" + uid + "&lat=" + lat + "&lon=" + lon;
        String cofeingUrl = "http://picnic.mydns.jp:3000/match/cofeing?lon=" + lon + "&lat=" + lat;
        String statusUrl = "http://picnic.mydns.jp:3000/match/status?uid=" + uid + "&status=" + status;
        String sendingUrl = "http://picnic.mydns.jp:3000/match/sending?uid=" + uid + "&message=" + message;
        String receiveUrl = "http://picnic.mydns.jp:3000/match/receive?uid=" + uid; 
        
        searchAsyncTask task = new searchAsyncTask(this,searchUrl);
        task.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /* (non-Javadoc)
     * @see com.picnic.sample.OnApiListener#onSuccess(java.lang.String)
     */
    @Override
    public void onSuccess(String result) {

        
        // http://techbooster.jpn.org/andriod/application/1645/
        
        List<String> list = new ArrayList<String>();

        try {
            JSONArray rootArray;
            rootArray = new JSONArray(result);

            for (int i = 0; i < rootArray.length(); i++) {
                JSONObject jsonObject = rootArray.getJSONObject(i);
                Log.d("JSONampleActivity", jsonObject.getString("uid"));
                list.add(jsonObject.getString("uid"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, list);
        mLv.setAdapter(adapter);

    }

}

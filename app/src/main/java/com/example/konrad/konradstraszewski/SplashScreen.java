package com.example.konrad.konradstraszewski;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SplashScreen extends ActionBarActivity {

    final String BASE_SERVER_URL = "http://192.168.0.7:8080";

    static public String jsonString;
    static public String [] UrlList = new String[30];
    static public String [] TitleList = new String[30];
    static public String [] DescList = new String[30];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        JSON json = new JSON();
        json.execute(BASE_SERVER_URL);
        new JSON() {
            @Override
            protected void onPostExecute(String result) {
                jsonString = result;
                Log.e("daw", jsonString);
                JSONArray array;
                try {
                    array = new JSONArray(jsonString);

                    for(int Position = 0; Position< array.length(); Position++) {
                        JSONObject titleJSONObject = array.getJSONObject(Position);

                        UrlList[Position] = titleJSONObject.getString("url");

                        TitleList[Position] = titleJSONObject.getString("title");

                        DescList[Position] = titleJSONObject.getString("desc");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }.execute(BASE_SERVER_URL);
        Thread waitingThread = new Thread() {
            public void run() {                            // Wątek czekania
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent mainActivity = new Intent("android.intent.action.MAIN2");          //Tworzenie nowego Activity
                    startActivity(mainActivity);
                }

            }
        };
        waitingThread.start();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

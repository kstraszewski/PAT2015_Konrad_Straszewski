package com.example.konrad.konradstraszewski;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * http://192.168.0.7:8080/page_0.json
 * Created by Konrad on 2015-01-06.
 */

public class JSON extends AsyncTask<String, String, String> {

    JSONArray jsonArray;
    String[] title,desc,url;

    @Override
    protected String doInBackground(String... params) {
        title = new String[10];
        desc = new String[10];
        url = new String[10];
        JSONObject jsonObject;
        String JSON_URL, result = "", json;
        int i = 0;

        while(i<3) {


            JSON_URL = params[0] + "/page_" + i + ".json";

            DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());

            HttpGet httpGet = new HttpGet(JSON_URL);
            httpGet.setHeader("Content-Type","application/json");

            InputStream inputStream = null;
            try {

                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity entity = response.getEntity();
                inputStream = entity.getContent();


                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);


                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }{
                    json = stringBuilder.toString();
                }

                jsonObject = new JSONObject(json);
                jsonArray = jsonObject.getJSONArray("array");

                result+= jsonArray.toString().substring(1,jsonArray.toString().length()-1);

                result+=",";


                fillTableWithJSON(jsonArray);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }

        result=result.substring(0,result.length()-1);
        result= "[" + result + "]";
        Log.e("daw", result);
        return result;
    }



    String fillTableWithJSON(JSONArray array) throws JSONException {
        for(int Position = 0; Position< array.length(); Position++) {
            JSONObject titleJSONObject = array.getJSONObject(Position);

            title[Position] = titleJSONObject.getString("title");

            desc[Position] = titleJSONObject.getString("desc");

            url[Position] = titleJSONObject.getString("url");
        }
        return null;
   }
}

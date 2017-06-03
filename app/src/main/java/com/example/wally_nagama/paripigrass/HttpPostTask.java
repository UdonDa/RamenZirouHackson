package com.example.wally_nagama.paripigrass;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by horitadaichi on 2017/06/03.
 */

public class HttpPostTask extends AsyncTask<URL, Void, Void> {
    Context context;
    static public String name_get;
    //static public int score_get;


    final String json = "{\"events\":{\"name\":\"" + name_get + "\",\"score\": "+ MainActivity.score +"}}";

    JSONObject jsonObject;
    //コンストラクタ
    HttpPostTask(){}


    @Override
    protected Void doInBackground(URL... urls) {
        try {
            String buffer = "";
            HttpURLConnection con = null;
            URL url = new URL("http://invita.tech:26000/callback");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setInstanceFollowRedirects(false);
            con.setRequestProperty("Accept-Language", "jp");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            OutputStream os = con.getOutputStream();
            PrintStream ps = new PrintStream(os);
            //書き込み
            ps.print(json);
            ps.close();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            buffer = reader.readLine();

            JSONObject item = new JSONObject(buffer);
            /*for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                Log.d("HTTP REQ", jsonObject.getString("name"));
            }*/

            MainActivity.st = item.getString("events");

            MainActivity.name = item.getJSONObject("events").getString("name");
            MainActivity.ranking = item.getJSONObject("events").getString("ranking");
            MainActivity.score_return = item.getJSONObject("events").getString("score");


            Log.d("帰って着たの",MainActivity.st);



            //終了
            con.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
package com.example.wally_nagama.paripigrass;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by horitadaichi on 2017/06/03.
 */

public class HttpPostTask extends AsyncTask<URL, Void, Void> {
    @Override
    protected Void doInBackground(URL... urls) {

        final URL url = urls[0];
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setChunkedStreamingMode(0);
            con.connect();

            // POSTデータ送信処理
            OutputStream out = null;
            try {
                out = con.getOutputStream();
                //送る
                out.write("POST DATA".getBytes("UTF-8"));
                out.flush();
            } catch (IOException e) {
                // POST送信エラー
                e.printStackTrace();
            } finally {
                if (out != null) {
                    out.close();
                }
            }

            final int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                // 正常
                // レスポンス取得処理を実行
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return null;
    }

}

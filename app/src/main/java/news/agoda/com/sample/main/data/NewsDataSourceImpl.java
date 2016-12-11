package news.agoda.com.sample.main.data;

import android.os.Handler;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import news.agoda.com.sample.entity.NewsEntity;

/**
 * Created by dvaletin on 10.12.16.
 * Copyright (c) 2016 android_challenge. All rights reserved.
 */

/**
 * Concrete class to load news entries
 */
public class NewsDataSourceImpl implements DataSource<NewsEntity> {

    private static final String BACKEND_URL = "http://www.mocky.io/v2/573c89f31100004a1daa8adb";

    @NonNull
    private final Handler handler;

    /**
     *
     * @param handler - handler to use to post results to.
     *                For instance you can use it to post results in UI thread.
     */
    public NewsDataSourceImpl(@NonNull final Handler handler) {
        this.handler = handler;
    }

    @Override
    public void loadDataAsync(final DataCallback<NewsEntity> callback) {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(BACKEND_URL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    String readStream = readStream(con.getInputStream());

                    JSONObject jsonObject;
                    final List<NewsEntity> newsItemList = new ArrayList<>();
                    jsonObject = new JSONObject(readStream);
                    JSONArray resultArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject newsObject = resultArray.getJSONObject(i);
                        NewsEntity newsEntity = new NewsEntity(newsObject);
                        newsItemList.add(newsEntity);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onDataLoaded(newsItemList);
                        }
                    });
                } catch (final Exception e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onError(e);
                        }
                    });
                }
            }
        }.start();

    }

    /**
     * Used to read all data from input stream and return as a String. Can potentially produce OOM, but in our case should not be an issue.
     */
    private String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

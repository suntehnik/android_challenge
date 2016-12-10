package news.agoda.com.sample.main;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import news.agoda.com.sample.detail.DetailViewActivity;
import news.agoda.com.sample.main.adapters.NewsListAdapter;
import news.agoda.com.sample.R;
import news.agoda.com.sample.entity.NewsEntity;

public class MainActivity
        extends ListActivity
        implements Callback {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        loadResource(this);
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

    private void loadResource(final Callback callback) {
        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    URL url = new URL("http://www.mocky.io/v2/573c89f31100004a1daa8adb");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    String readStream = readStream(con.getInputStream());
                    callback.onResult(readStream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

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

    @Override public void onResult(final String data) {
        handler.postDelayed(new Runnable() {
            @Override public void run() {
                JSONObject jsonObject;
                List<NewsEntity> newsItemList = new ArrayList<>();


                try {
                    jsonObject = new JSONObject(data);
                    JSONArray resultArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject newsObject = resultArray.getJSONObject(i);
                        NewsEntity newsEntity = new NewsEntity(newsObject);
                        newsItemList.add(newsEntity);
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "fail to parse json string");
                }

                NewsListAdapter adapter = new NewsListAdapter(MainActivity.this, R.layout.list_item_news, newsItemList);
                setListAdapter(adapter);

                ListView listView = getListView();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        NewsEntity newsEntity = ((NewsListAdapter) parent.getAdapter()).getItem(position);
                        if (newsEntity == null) {
                            return;
                        }
                        String title = newsEntity.getTitle();
                        Intent intent = new Intent(MainActivity.this, DetailViewActivity.class);
                        intent.putExtra("storyURL", newsEntity.getArticleUrl());
                        intent.putExtra("summary", newsEntity.getSummary());
                        if (newsEntity.getMediaEntity().size() > 0) {
                            String imageUrl = newsEntity.getMediaEntity().get(0).getUrl();
                            if (!TextUtils.isEmpty(imageUrl)) {
                                intent.putExtra("imageURL", imageUrl);
                            }
                        }
                        intent.putExtra("title", title);
                        startActivity(intent);
                    }
                });
            }
        }, 0);
    }

}

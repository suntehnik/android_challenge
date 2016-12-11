package news.agoda.com.sample.main;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import news.agoda.com.sample.R;
import news.agoda.com.sample.entity.NewsEntity;
import news.agoda.com.sample.main.adapters.NewsListAdapter;
import news.agoda.com.sample.main.data.DataSource;
import news.agoda.com.sample.main.data.NewsDataSourceImpl;
import news.agoda.com.sample.main.presenter.MainPresenterImpl;

public class MainActivity
        extends ListActivity
        implements MainView {

    @Nullable
    private MainPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataSource<NewsEntity> dataSource = new NewsDataSourceImpl(new Handler(Looper.getMainLooper()));
        presenter = new MainPresenterImpl(dataSource, this);
        presenter.loadResource();
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

    @Override
    public void populateAdapterData(@NonNull final List<NewsEntity> newsItemList) {
        NewsListAdapter adapter = new NewsListAdapter(MainActivity.this, R.layout.list_item_news, newsItemList);
        setListAdapter(adapter);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onListItemClick(final ListView l, final View v, final int position, final long id) {
        NewsEntity newsEntity = ((NewsListAdapter) l.getAdapter()).getItem(position);
        if (newsEntity == null) {
            return;
        }
        if (presenter != null) {
            presenter.onListItemClick(newsEntity);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
            presenter = null;
        }
    }
}

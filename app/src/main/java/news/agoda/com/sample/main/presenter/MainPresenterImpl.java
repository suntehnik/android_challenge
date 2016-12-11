package news.agoda.com.sample.main.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import news.agoda.com.sample.entity.NewsEntity;
import news.agoda.com.sample.main.MainView;
import news.agoda.com.sample.main.data.DataCallback;
import news.agoda.com.sample.main.data.DataSource;
import news.agoda.com.sample.main.iteractor.MainRouter;

/**
 * Created by dvaletin on 11.12.16.
 * Copyright (c) 2016 android_challenge. All rights reserved.
 */

public class MainPresenterImpl implements DataCallback<NewsEntity> {
    @NonNull
    private DataSource<NewsEntity> dataSource;

    @NonNull
    private MainRouter router;
    @Nullable
    private MainView   mainView;

    public MainPresenterImpl(@NonNull DataSource<NewsEntity> dataSource, @NonNull MainRouter router, @NonNull final MainView mainView) {
        this.dataSource = dataSource;
        this.router = router;
        this.mainView = mainView;
    }

    public void loadResource() {
        new Thread(new Runnable() {
            @Override public void run() {
                dataSource.loadDataAsync(MainPresenterImpl.this);
            }
        }).start();
    }

    @Override
    public void onDataLoaded(@NonNull final List<NewsEntity> data) {
        if (mainView != null) {
            mainView.populateAdapterData(data);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onError(final Exception error) {
        if (error instanceof IOException && mainView != null) {
            mainView.populateAdapterData(Collections.EMPTY_LIST);
        }
    }

    public void onDestroy() {
        // Release any resources here to prevent memory leaks and circular dependencies
        mainView = null;
    }

    public void onListItemClick(final NewsEntity newsEntity) {
        if (mainView != null) {
            router.onNavigateToDetails(mainView.getActivity(), newsEntity);
        }
    }
}

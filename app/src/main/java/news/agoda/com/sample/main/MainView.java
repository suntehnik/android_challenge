package news.agoda.com.sample.main;

import android.app.Activity;
import android.support.annotation.NonNull;

import java.util.List;

import news.agoda.com.sample.entity.NewsEntity;

/**
 * Created by dvaletin on 11.12.16.
 * Copyright (c) 2016 android_challenge. All rights reserved.
 */

public interface MainView {

    void populateAdapterData(@NonNull List<NewsEntity> newsItemList);

    Activity getActivity();
}

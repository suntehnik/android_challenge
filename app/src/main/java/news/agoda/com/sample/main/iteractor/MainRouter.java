package news.agoda.com.sample.main.iteractor;

import android.app.Activity;
import android.support.annotation.NonNull;

import news.agoda.com.sample.entity.NewsEntity;

/**
 * Created by dvaletin on 11.12.16.
 * Copyright (c) 2016 android_challenge. All rights reserved.
 */
public interface MainRouter {

    void onNavigateToDetails(@NonNull Activity activity, @NonNull NewsEntity newsEntity);
}

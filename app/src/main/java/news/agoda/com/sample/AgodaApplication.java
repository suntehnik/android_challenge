package news.agoda.com.sample;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by dvaletin on 11.12.16.
 * Copyright (c) 2016 android_challenge. All rights reserved.
 */

public class AgodaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}

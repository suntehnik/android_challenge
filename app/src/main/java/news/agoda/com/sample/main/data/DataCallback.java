package news.agoda.com.sample.main.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by dvaletin on 10.12.16.
 * Copyright (c) 2016 android_challenge. All rights reserved.
 */


/**
 * DataCallback interface is used to provide result of data manager work.
 *
 * @param <T>
 */
public interface DataCallback<T> {

    /**
     * Should be called on successful loading of collection of data
     * @param data - collection of items if type T
     */
    void onDataLoaded(@NonNull List<T> data);

    /**
     * Should be called in case of any error occurred during data loading
     * @param error - exception error
     */
    void onError(Exception error);
}

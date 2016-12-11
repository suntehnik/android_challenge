package news.agoda.com.sample.main.data;

/**
 * Created by dvaletin on 10.12.16.
 * Copyright (c) 2016 android_challenge. All rights reserved.
 */


/**
 * Generic interface defining abstract data source responsible for loading data of any type.
 * @param <T>
 */
public interface DataSource<T> {

    /**
     * Loading data asynchronously.
     * @param callback - used to post results of data loading or error
     */
    void loadDataAsync(DataCallback<T> callback);
}

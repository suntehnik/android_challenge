package news.agoda.com.sample.main.iteractor;

import android.app.Activity;
import android.support.annotation.NonNull;

import news.agoda.com.sample.R;
import news.agoda.com.sample.detail.DetailFragment;
import news.agoda.com.sample.entity.NewsEntity;

/**
 * Created by dvaletin on 10.12.16.
 * Copyright (c) 2016 android_challenge. All rights reserved.
 */


/**
 * Simple class to provide navigation from main activity to detail actitivy.
 * Will be refactored in the future to support fragment interactions.
 */
public class FragmentMainRouterImpl implements MainRouter {

    public FragmentMainRouterImpl() {
    }

    /**
     * Called when list item clicked. Used to navigate user to details view
     *
     * @param activity   source activity
     * @param newsEntity item which details need to display
     */

    @Override
    public void onNavigateToDetails(@NonNull final Activity activity, @NonNull final NewsEntity newsEntity) {
        String title = newsEntity.getTitle();
        String articleUrl = newsEntity.getArticleUrl();
        String summary = newsEntity.getSummary();
        String imageUrl = null;
        if (newsEntity.getMediaEntity().size() > 0) {
            imageUrl = newsEntity.getMediaEntity().get(0).getUrl();
        }

        DetailFragment detailFragment = DetailFragment.newInstance(title, articleUrl, summary, imageUrl);
        activity.getFragmentManager().beginTransaction()
                .replace(R.id.detailContainer, detailFragment)
                .addToBackStack(title)
                .commit();
    }
}

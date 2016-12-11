package news.agoda.com.sample.detail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import news.agoda.com.sample.R;

import static news.agoda.com.sample.detail.Constants.PARAM_IMAGE_URL;
import static news.agoda.com.sample.detail.Constants.PARAM_STORY_URL;
import static news.agoda.com.sample.detail.Constants.PARAM_SUMMARY;
import static news.agoda.com.sample.detail.Constants.PARAM_TITLE;

/**
 * News detail view
 */
public class DetailViewActivity extends Activity {
    private              String storyURL        = null;



    public static void start(final Activity activity, final String title, final String articleUrl, final String summary, @Nullable  final String imageUrl) {
        Intent intent = new Intent(activity, DetailViewActivity.class);
        intent.putExtra(PARAM_STORY_URL, articleUrl);
        intent.putExtra(PARAM_SUMMARY, summary);
        intent.putExtra(PARAM_TITLE, title);
        intent.putExtra(PARAM_IMAGE_URL, imageUrl);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        storyURL = extras.getString(PARAM_STORY_URL);
        String title = extras.getString(PARAM_TITLE);
        String summary = extras.getString(PARAM_SUMMARY);
        String imageURL = extras.getString(PARAM_IMAGE_URL);

        TextView titleView = (TextView) findViewById(R.id.title);
        DraweeView imageView = (DraweeView) findViewById(R.id.news_image);
        TextView summaryView = (TextView) findViewById(R.id.summary_content);

        Button fullStory = (Button) findViewById(R.id.full_story_link);

        try {
            Uri.parse(storyURL);
        } catch (Exception ignore) {
            fullStory.setEnabled(false);
        }

        titleView.setText(title);
        summaryView.setText(summary);

        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                                                  .setImageRequest(ImageRequest.fromUri(imageURL))
                                                  .setOldController(imageView.getController()).build();
        imageView.setController(draweeController);
    }

    public void onFullStoryClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(storyURL));
        startActivity(intent);
    }

}
package news.agoda.com.sample.detail;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class DetailFragment extends Fragment {

    private String storyURL;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(final String title, final String articleUrl, final String summary, @Nullable final String imageUrl) {
        Bundle args = new Bundle();
        args.putString(PARAM_STORY_URL, articleUrl);
        args.putString(PARAM_SUMMARY, summary);
        args.putString(PARAM_TITLE, title);
        args.putString(PARAM_IMAGE_URL, imageUrl);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_detail, container, false);


        if (getArguments() != null) {
            Bundle extras = getArguments();
            storyURL = extras.getString(PARAM_STORY_URL);
            String title = extras.getString(PARAM_TITLE);
            String summary = extras.getString(PARAM_SUMMARY);
            String imageURL = extras.getString(PARAM_IMAGE_URL);

            TextView titleView = (TextView) rootView.findViewById(R.id.title);
            DraweeView imageView = (DraweeView) rootView.findViewById(R.id.news_image);
            TextView summaryView = (TextView) rootView.findViewById(R.id.summary_content);

            Button fullStory = (Button) rootView.findViewById(R.id.full_story_link);

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

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}

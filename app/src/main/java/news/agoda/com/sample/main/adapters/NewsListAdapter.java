package news.agoda.com.sample.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import java.util.List;

import news.agoda.com.sample.R;
import news.agoda.com.sample.entity.MediaEntity;
import news.agoda.com.sample.entity.NewsEntity;

public class NewsListAdapter extends ArrayAdapter<NewsEntity> {
    public NewsListAdapter(Context context, int resource, List<NewsEntity> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_news, parent, false);
            viewHolder.newsTitle = (TextView) convertView.findViewById(R.id.news_title);
            viewHolder.imageView = (DraweeView) convertView.findViewById(R.id.news_item_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String thumbnailURL;
        String newsTitle;
        NewsEntity newsEntity = getItem(position);

        if (newsEntity == null) {
            newsTitle = "";
            thumbnailURL = "";
        } else {
            newsTitle = newsEntity.getTitle();
            List<MediaEntity> mediaEntityList = newsEntity.getMediaEntity();
            thumbnailURL = mediaEntityList.size() > 0 ? mediaEntityList.get(0).getUrl() : "";
        }

        viewHolder.newsTitle.setText(newsTitle);
        DraweeController draweeController = Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequest.fromUri(
                thumbnailURL)).setOldController(viewHolder.imageView.getController()).build();
        viewHolder.imageView.setController(draweeController);
        return convertView;
    }

    private static class ViewHolder {
        TextView   newsTitle;
        DraweeView imageView;
    }
}

package news.agoda.com.sample.entity;

import android.support.annotation.Nullable;

import org.json.JSONObject;

/**
 * This class represents a media item
 */
public class MediaEntity {
    @Nullable
    private String url;
    @Nullable
    private String format;
    private int height;
    private int width;
    @Nullable
    private String type;
    @Nullable
    private String subType;
    @Nullable
    private String caption;
    @Nullable
    private String copyright;

    public MediaEntity(JSONObject jsonObject) {
        url = jsonObject.optString("url");
        format = jsonObject.optString("format");
        height = jsonObject.optInt("height");
        width = jsonObject.optInt("width");
        type = jsonObject.optString("type");
        subType = jsonObject.optString("subtype");
        caption = jsonObject.optString("caption");
        copyright = jsonObject.optString("copyright");
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    @Nullable
    public String getFormat() {
        return format;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Nullable
    public String getType() {
        return type;
    }

    @Nullable
    public String getSubType() {
        return subType;
    }

    @Nullable
    public String getCaption() {
        return caption;
    }

    @Nullable
    public String getCopyright() {
        return copyright;
    }

}

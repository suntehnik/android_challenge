package news.agoda.com.sample;

import android.test.AndroidTestCase;
import android.text.TextUtils;

import junit.framework.Assert;

import org.json.JSONObject;

/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Gnu Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


public class MediaEntityTest extends AndroidTestCase {
    private static final String TEST_JSON = "{\n"+
            "          \"url\": \"http://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-thumbStandard.jpg\",\n"+
            "          \"format\": \"Standard Thumbnail\",\n"+
            "          \"height\": 75,\n"+
            "          \"width\": 75,\n"+
            "          \"type\": \"image\",\n"+
            "          \"subtype\": \"photo\",\n"+
            "          \"caption\": \"People eating at the Brave Horse Tavern on the Amazon campus in Seattle in June.\",\n"+
            "          \"copyright\": \"Matthew Ryan Williams for The New York Times\"\n"+
            "        }";

    private MediaEntity entity;

    @Override
    public void setUp() throws Exception {
        entity = new MediaEntity(new JSONObject(TEST_JSON));
    }

    @Override
    public void tearDown() throws Exception {

    }

    public void testGetUrl() throws Exception {
        Assert.assertFalse(TextUtils.isEmpty(entity.getUrl()));
    }

    public void testGetFormat() throws Exception {
        Assert.assertFalse(TextUtils.isEmpty(entity.getFormat()));
    }

    public void testGetHeight() throws Exception {
        Assert.assertEquals(75, entity.getHeight());
    }

    public void testGetWidth() throws Exception {
        Assert.assertEquals(75, entity.getWidth());
    }

    public void testGetType() throws Exception {
        Assert.assertEquals("image", entity.getType());
    }

    public void testGetSubType() throws Exception {
        Assert.assertEquals("photo", entity.getSubType());
    }

    public void testGetCaption() throws Exception {
        Assert.assertFalse(TextUtils.isEmpty(entity.getCaption()));
    }

    public void testGetCopyright() throws Exception {
        Assert.assertFalse(TextUtils.isEmpty(entity.getCopyright()));
    }

}
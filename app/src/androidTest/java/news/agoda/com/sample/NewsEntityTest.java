package news.agoda.com.sample;

import android.text.TextUtils;

import junit.framework.TestCase;

import org.json.JSONObject;

import news.agoda.com.sample.entity.NewsEntity;
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


public class NewsEntityTest extends TestCase {
    private static final String TEST_JSON = "{\n" +
            "      \"section\": \"Business Day\",\n" +
            "      \"subsection\": \"\",\n" +
            "      \"title\": \"Work Policies May Be Kinder, but Brutal Competition Isn’t\",\n" +
            "      \"abstract\": \"Top-tier employers may be changing their official policies in a nod to work-life balance, but brutal competition remains an inescapable component of workers’ daily lives.\",\n" +
            "      \"url\": \"http://www.nytimes.com/2015/08/18/business/work-policies-may-be-kinder-but-brutal-competition-isnt.html\",\n" +
            "      \"byline\": \"By NOAM SCHEIBER\",\n" +
            "      \"item_type\": \"Article\",\n" +
            "      \"updated_date\": \"2015-08-17T22:10:02-5:00\",\n" +
            "      \"created_date\": \"2015-08-17T22:10:04-5:00\",\n" +
            "      \"published_date\": \"2015-08-18T04:00:00-5:00\",\n" +
            "      \"material_type_facet\": \"News\",\n" +
            "      \"kicker\": \"\"," +
            "\"multimedia\": [\n" +
            "        {\n" +
            "          \"url\": \"http://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-thumbStandard.jpg\",\n" +
            "          \"format\": \"Standard Thumbnail\",\n" +
            "          \"height\": 75,\n" +
            "          \"width\": 75,\n" +
            "          \"type\": \"image\",\n" +
            "          \"subtype\": \"photo\",\n" +
            "          \"caption\": \"People eating at the Brave Horse Tavern on the Amazon campus in Seattle in June.\",\n" +
            "          \"copyright\": \"Matthew Ryan Williams for The New York Times\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"url\": \"http://static01.nyt.com/images/2015/08/18/business/18EMPLOY/18EMPLOY-thumbLarge.jpg\",\n" +
            "          \"format\": \"thumbLarge\",\n" +
            "          \"height\": 150,\n" +
            "          \"width\": 150,\n" +
            "          \"type\": \"image\",\n" +
            "          \"subtype\": \"photo\",\n" +
            "          \"caption\": \"People eating at the Brave Horse Tavern on the Amazon campus in Seattle in June.\",\n" +
            "          \"copyright\": \"Matthew Ryan Williams for The New York Times\"\n" +
            "        }" +
            "]" +
            "}";

    private NewsEntity entity;

    public void setUp() throws Exception {
        super.setUp();
        entity = new NewsEntity(new JSONObject(TEST_JSON));

    }

    public void tearDown() throws Exception {

    }

    public void testGetTitle() throws Exception {
        assertFalse(TextUtils.isEmpty(entity.getTitle()));
    }

    public void testGetSummary() throws Exception {
        assertFalse(TextUtils.isEmpty(entity.getSummary()));
    }

    public void testGetArticleUrl() throws Exception {
        assertFalse(TextUtils.isEmpty(entity.getArticleUrl()));
    }

    public void testGetByline() throws Exception {
        assertFalse(TextUtils.isEmpty(entity.getByline()));
    }

    public void testGetPublishedDate() throws Exception {
        assertFalse(TextUtils.isEmpty(entity.getPublishedDate()));
    }

    public void testGetMediaEntity() throws Exception {
        assertNotNull(entity.getMediaEntity());
        assertEquals(2, entity.getMediaEntity().size());
    }

}
package news.agoda.com.sample;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import static junit.framework.Assert.assertTrue;

public class APIAvailabilityTest {

    @Test public void testAvailability() throws Exception {
        URLConnection connection = new URL("http://www.mocky.io/v2/573c89f31100004a1daa8adb").openConnection();
        InputStream response = connection.getInputStream();

        StringBuilder buffer = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response, Charset.defaultCharset()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                buffer.append(line);
            }
        }

        assertTrue(buffer.length() > 0);
    }
}

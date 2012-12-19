package info.ishared.reading.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Seven
 * Date: 12-12-5
 * Time: PM2:54
 */
public class HttpHelper {
    private static HttpHelper instance = new HttpHelper();

    public static HttpHelper getInstance() {
        if (instance == null) instance = new HttpHelper();
        return instance;
    }

    private HttpHelper() {
    }

    public String getHtmlContent(String url,String encoder) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            List<String> lines = IOUtils.readLines(entity.getContent(), encoder);
            for (String line : lines) {
                stringBuffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
    public String getHtmlContent(String url) {
        return this.getHtmlContent(url,"utf-8");
    }
}

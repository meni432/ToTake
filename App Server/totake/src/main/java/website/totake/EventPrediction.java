package website.totake;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.IOException;

public class EventPrediction {
    private static EventPrediction ourInstance = new EventPrediction();

    public static EventPrediction getInstance() {
        return ourInstance;
    }

    private CloseableHttpClient mHttpClient;

    private EventPrediction() {
        mHttpClient = HttpClientBuilder.create().build();
    }

    public void sendEvent(JSONObject json) {

        mHttpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost(Defaults.PIO_EVENT_SERVER + "/events.json?accessKey=" + Defaults.PIO_ACCESS_KEY);
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            CloseableHttpResponse closeableHttpResponse = mHttpClient.execute(request);
            System.out.println(closeableHttpResponse.getStatusLine());
            System.out.println(closeableHttpResponse);
        } catch (Exception ex) {
            // handle exception here
            ex.printStackTrace();
        } finally {
            try {
                mHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package eddu.skku.map.pa2test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;

public class Subactivity extends AppCompatActivity {

    String keyword;
    String str;
    String str1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subactivity);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Intent intent = getIntent();
                    keyword = intent.getStringExtra("keyword");
                    str = getNaverSearch(keyword);
                    String[] lists= str.split("\n");
                    str1 = lists[1];

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView searchResult2 = (TextView) findViewById(R.id.searchResult2);
                            Intent intent = new Intent();
                            intent.putExtra("result",str1);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });thread.start();
    }


    public String getNaverSearch(String keyword) {

        String clientId = "aXk8IvioKagNRcMmMtq0";
        String clientSecret = "M1ztNGqQJJ";

        StringBuffer sb = new StringBuffer();

        try {

            String text = URLEncoder.encode(keyword, "UTF-8");

            String apiURL = "https://openapi.naver.com/v1/search/image.xml?query=" + text + "&display=1" + "&start=1";

            URL url = new URL(apiURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Naver-Client-Id", clientId);
            conn.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            String tag;

            xpp.setInput(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            xpp.next();
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        tag = xpp.getName(); //태그 이름 얻어오기

                        if (tag.equals("title")) ; //첫번째 검색 결과
                        else if (tag.equals("link")) {
                            xpp.next();
                            sb.append(xpp.getText().replaceAll("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>", ""));
                            sb.append("\n");

                        }
                        break;
                }
                eventType = xpp.next();
            }

        } catch (Exception e) {
            return e.toString();
        }

        return sb.toString();
    }
}
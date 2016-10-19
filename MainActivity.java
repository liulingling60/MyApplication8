package com.ll.dell.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    String str = "http://newsapi.sina.cn/?resource=feed&channel=news_tuijian&offset=0&pullDirection=down&pullTimes=1&loadingAdTimestamp=0&behavior=auto&lastTimeStamp=1476708282&listCount=21&length=20&deviceId=be97fe8f2db8acc0&from=6054195012&weiboUid=&weiboSuid=&imei=00000000000000&wm=b207&chwm=12010_0002&oldChwm=&osVersion=4.4.4&connectionType=2&resolution=480x800&city=&deviceModel=Genymotion__generic__4.4.4&location=0.0%2C0.0&link=&mac=08%3A00%3A27%3Ad2%3Aa5%3A19&ua=Genymotion-4.4.4__sinanews__5.4.1__android__4.4.4&cmd_mac=&urlSign=b2dd8cccb3&rand=686";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        getData();
    }

    public void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(str);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    if (con.getResponseCode() == 200) {
                        InputStream in =con.getInputStream();
                        byte[] buff = new byte[1024];
                        int len = 0;
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        while ((len = in.read(buff)) !=-1) {
                            bos.write(buff,0,len);
                        }
                        final String str= new String(bos.toByteArray());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(str);

                            }
                        });
                    }

            }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }){}.start();
    }
}

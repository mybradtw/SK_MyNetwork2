package tw.brad.sk_network2;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyService2 extends Service {
    public static final int CMD_GOTO_Sakura = 1;
    public static final int CMD_GET_Sakura_Image = 2;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int cmd = intent.getIntExtra("cmd", -1);
        switch (cmd){
            case CMD_GOTO_Sakura:
                gotoSakura();
                break;
            case CMD_GET_Sakura_Image:
                getSakuraImage();
                break;
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void gotoSakura(){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL("https://www.sakura.com.tw");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;
                    while ( (line = reader.readLine()) != null){
                        Log.v("brad", line);
                    }
                    reader.close();
                }catch (Exception e){
                    Log.v("brad", e.toString());
                }

            }
        }.start();
    }

    private void getSakuraImage(){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL("https://www.sakura.com.tw/Uploads/Post/contents/Electrolux_2018%E5%91%A8%E5%B9%B4%E6%85%B6pc_1.jpg");

//                    URL url = new URL("https://www.sakura.com.tw/images/footer_logo.png");

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();

                    InputStream in = conn.getInputStream();
                    MainAPp.bmp = BitmapFactory.decodeStream(in);

                    Intent intent = new Intent("sakura");
                    //intent.putExtra("img", bmp);
                    sendBroadcast(intent);

                }catch (Exception e){
                    Log.v("brad", "xxx");
                    Log.v("brad", e.toString());
                }

            }
        }.start();
    }


}

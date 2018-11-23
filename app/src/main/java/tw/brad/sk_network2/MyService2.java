package tw.brad.sk_network2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyService2 extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    private void gotoSakura(){
        try {
            URL url = new URL("https://www.sakura.com.tw");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ( (line = reader.readLine()) != null){
                Log.v("brad", line);
            }

            reader.close();


        }catch (Exception e){
            Log.v("brad", e.toString());
        }
    }


}

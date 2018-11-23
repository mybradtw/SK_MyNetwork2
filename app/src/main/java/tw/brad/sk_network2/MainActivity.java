package tw.brad.sk_network2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ConnectivityManager cmgr;
    private MyReceiver myReceiver;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img);

        cmgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction("sakura");
        registerReceiver(myReceiver, filter);
    }

    @Override
    public void finish() {
        super.finish();
        unregisterReceiver(myReceiver);
    }

    private boolean isConnectNetwork(){
        NetworkInfo info = cmgr.getActiveNetworkInfo();
        boolean isConnected = info != null &&
                info.isConnectedOrConnecting();
        return  isConnected;
    }


    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                case ConnectivityManager.CONNECTIVITY_ACTION:
                    Log.v("brad", "network = " + isConnectNetwork());
                    break;
                case "sakura":
                    showImage(intent);
                    break;

            }
        }
    }

    private void showImage(Intent intent){
        //Bitmap bmp = (Bitmap)intent.getParcelableExtra("img");
        img.setImageBitmap(MainAPp.bmp);
    }

    public void test1(View view) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("key", (int)(Math.random()*49+1));
        startService(intent);
    }
    public void test2(View view) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    public void test3(View view) {
        Intent intent = new Intent(this, MyService2.class);
        intent.putExtra("cmd", MyService2.CMD_GOTO_Sakura);
        startService(intent);
    }

    public void test4(View view) {
        Intent intent = new Intent(this, MyService2.class);
        intent.putExtra("cmd", MyService2.CMD_GET_Sakura_Image);
        startService(intent);
    }


}

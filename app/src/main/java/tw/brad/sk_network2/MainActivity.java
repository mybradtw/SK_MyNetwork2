package tw.brad.sk_network2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ConnectivityManager cmgr;
    private MyReceiver myReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cmgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
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
            Log.v("brad", "network = " + isConnectNetwork());
        }
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
    }

    public void test4(View view) {

    }

}

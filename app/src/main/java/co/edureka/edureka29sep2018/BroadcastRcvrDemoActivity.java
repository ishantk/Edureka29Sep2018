package co.edureka.edureka29sep2018;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class BroadcastRcvrDemoActivity extends AppCompatActivity {

    Button btn;
    MyBroadcastReceiver myRcvr;
    YourBroadcastReceiver yourRcvr;

    void initViews(){

        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // To send a user defined event we need to create intent and broadcast it
                Intent intent = new Intent("anything");
                LocalBroadcastManager.getInstance(BroadcastRcvrDemoActivity.this).sendBroadcast(intent);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_rcvr_demo);
        initViews();

        registerYourRcvr();
    }

    void registerMyRcvr(){

        myRcvr = new MyBroadcastReceiver();

        // Its list of system level events
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BOOT_COMPLETED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        // we are looking for data w.r.t. package (app ids)
        filter.addDataScheme("package");

        // Subscribing the events for MyBroadcastReceiver
        registerReceiver(myRcvr,filter); // registerReceiver is an API
        Log.i("MyBroadcastReceiver","MyBroadcastReceiver Registered");
    }

    void registerYourRcvr(){

        yourRcvr = new YourBroadcastReceiver();

        // Its list of user defined events
        IntentFilter filter = new IntentFilter();
        filter.addAction("anything");
        filter.addAction("image.downloaded");
        filter.addAction("this.is.my.action");

        // Subscribing the events for YourBroadcastReceiver
        LocalBroadcastManager.getInstance(this).registerReceiver(yourRcvr,filter); // LocalBroadcastManager and registerReceiver is an API
        Log.i("YourBroadcastReceiver","YourBroadcastReceiver Registered");
    }


    // 1. BroadcastReceiver listening to System Level Events
    class MyBroadcastReceiver extends BroadcastReceiver{

        // Only 1 LifeCycle Method i.e. onReceive
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            String packageName = intent.getData().getSchemeSpecificPart(); // Getting Application ID


            if(action.equals(Intent.ACTION_PACKAGE_ADDED)){
                Log.i("MyBroadcastReceiver","PACKAGE_ADDED: "+packageName);
            }

            if(action.equals(Intent.ACTION_PACKAGE_REMOVED)){
                Log.i("MyBroadcastReceiver","PACKAGE_REMOVED: "+packageName);
            }

        }
    }

    class YourBroadcastReceiver extends BroadcastReceiver{

        // Only 1 LifeCycle Method i.e. onReceive
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();


            if(action.equals("anything")){
                Log.i("MyBroadcastReceiver","Anything Received");
            }

            if(action.equals("this.is.my.action")){
                Log.i("MyBroadcastReceiver","This is My Action Received");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // No more listening to System Events : UnSubscribed
        unregisterReceiver(myRcvr);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(yourRcvr);
    }
}

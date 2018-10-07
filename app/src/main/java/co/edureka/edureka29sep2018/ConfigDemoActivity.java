package co.edureka.edureka29sep2018;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ConfigDemoActivity extends AppCompatActivity {

    // Configuration change shall destroy and re create the same activity
    // State of Activity i.e. data will be lost if it is destroyed and re created

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_demo);
        Log.i("ConfigDemoActivity","==onCreate==");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ConfigDemoActivity","==onDestroy==");
    }

    // Rather than destroying and re creating the activity, now onConfigurationChanged will be executed !!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i("ConfigDemoActivity", "==onConfigurationChanged LANDSCAPE==");
        }else{
            Log.i("ConfigDemoActivity", "==onConfigurationChanged PORTRAIT==");
        }
    }
}

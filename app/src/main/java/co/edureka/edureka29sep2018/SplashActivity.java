package co.edureka.edureka29sep2018;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import co.edureka.edureka29sep2018.ui.AddUserActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        TextView txtTitle = findViewById(R.id.textView);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.alpha_anim);
        txtTitle.startAnimation(animation);

        // After 3 secs send a message with id 101 to Handler Object
        handler.sendEmptyMessageDelayed(101,3000);

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 101){
                Intent intent = new Intent(SplashActivity.this,AddUserActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
}

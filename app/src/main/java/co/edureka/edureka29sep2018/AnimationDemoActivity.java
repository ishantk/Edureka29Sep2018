package co.edureka.edureka29sep2018;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AnimationDemoActivity extends AppCompatActivity {

    EditText eTxt;
    Button btn;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_demo);

        eTxt = findViewById(R.id.editText);
        btn = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);

        Animation alphaAnim = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        Animation rotateAnim = AnimationUtils.loadAnimation(this,R.anim.rotate_anim);
        eTxt.startAnimation(alphaAnim);
        btn.startAnimation(rotateAnim);


        AnimationDrawable animationDrawable = (AnimationDrawable)imageView.getBackground();
        animationDrawable.start();

        // HW: Write an App to create an animated SlideShow along with Audio being played !!
    }
}

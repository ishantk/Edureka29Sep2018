package co.edureka.edureka29sep2018;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Date;

public class DynamicLayoutActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    RelativeLayout.LayoutParams layoutParams;

    Button btnClickMe;

    void initViews(){

        btnClickMe = new Button(this);
        btnClickMe.setText("Click Me !!");
        btnClickMe.setBackgroundColor(Color.RED);

        relativeLayout = new RelativeLayout(this);
        relativeLayout.setBackgroundColor(Color.CYAN);

        layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        relativeLayout.addView(btnClickMe,layoutParams);

        setContentView(relativeLayout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        btnClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                Toast.makeText(DynamicLayoutActivity.this,"Today is: "+date.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

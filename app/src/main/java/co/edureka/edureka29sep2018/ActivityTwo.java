package co.edureka.edureka29sep2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener{

    // Declare references to the Views
    EditText eTxtName, eTxtAge;
    Button btnBack;

    // initialize the views
    void initViews(){
        eTxtName = findViewById(R.id.editTextName);
        eTxtAge = findViewById(R.id.editTextAge);

        btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(this);

        // Receive Intent By A1
        Intent rcvdIntent = getIntent();
        //String name = rcvdIntent.getStringExtra("keyName");
        //int iAge = rcvdIntent.getIntExtra("keyAge",0);

        //Bundle rcvdBundle = rcvdIntent.getBundleExtra("keyBundle");
        //String name = rcvdBundle.getString("keyName");
        //int iAge = rcvdBundle.getInt("keyAge");

        //User user = (User)rcvdIntent.getSerializableExtra("keyUser");

        //eTxtName.setText(user.name);
        //eTxtAge.setText(String.valueOf(user.age)); // on EditText/Views we always set and get the data as String

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initViews();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonBack){

            // Send the data back to the A1
            String name = eTxtName.getText().toString();
            String age = eTxtAge.getText().toString();

            // data here, contains no action. It is only meant to hold data
            Intent data = new Intent();
            data.putExtra("keyName",name);
            data.putExtra("keyAge",age);

            setResult(201,data);

            // Kills the activity
            finish();
        }
    }
}

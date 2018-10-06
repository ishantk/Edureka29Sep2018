package co.edureka.edureka29sep2018;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityOne extends AppCompatActivity implements View.OnClickListener{

    // Declare references to the Views
    EditText eTxtName, eTxtAge;
    Button btnSubmit;

    // initialize the views
    void initViews(){
        eTxtName = findViewById(R.id.editTextName);
        eTxtAge = findViewById(R.id.editTextAge);

        btnSubmit = findViewById(R.id.buttonSubmit);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(101);

        initViews();

        // This is only * conditions applied, when we have the data in the Bundle
        if(savedInstanceState!=null){
            String name = savedInstanceState.getString("keyName");
            int age = savedInstanceState.getInt("keyAge",0);
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.buttonSubmit){

            //String name = eTxtName.getText().toString();
            //String age = eTxtAge.getText().toString();

            //int iAge = Integer.parseInt(age);

             // We must start a new Activity here !!
            // To do that we shall use API called Intent, which will have action !!

            // Implicit Intent
            //Intent intent = new Intent("co.edureka.edureka29sep2018.activitytwo");
            //startActivity(intent);

            // Explicit Intent
            // we need not to create any <intent-filter> tage with action and category in AndroidManifest.xml file
            //Intent intent = new Intent(ActivityOne.this,ActivityTwo.class);

            // Forward Passing: Passing the data from A1 to A2 (Works for both implicit and explicit intent)
            // 1. Put the data directly into the Intent, before we start A2
            //intent.putExtra("keyName",name); // key can be any name of your choice and should always be a String
            //intent.putExtra("keyAge",iAge);

            //2. Put the data in Bundle and pass the Bundle in the Intent
            //Bundle bundle = new Bundle(); // Create Bundle Object
            //bundle.putString("keyName",name);
            //bundle.putInt("keyAge",iAge);

            //intent.putExtra("keyBundle",bundle);

            //3. Custom Object : User
            // Pass the entire object from Activity1 to Activity2
            //User user = new User(name,iAge);
            //intent.putExtra("keyUser",user);


            //startActivity(intent);


            // Backward Passing: Passing the data from A2 to A1 on request of A1 (Works for both implicit and explicit intent)
            Intent intent = new Intent(ActivityOne.this,ActivityTwo.class);

            // We are starting a new Activity to get data back into this activity
            // requestCode can be any number of your choice
            startActivityForResult(intent,101);
        }

    }

    // onActivityResult is executed when A2 has done setResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 101 && resultCode == 201){
            String name = data.getStringExtra("keyName");
            String age = data.getStringExtra("keyAge");

            eTxtName.setText(name);
            eTxtAge.setText(age);
        }
    }

    // Use Case:
    // IFF UI was in the background and due to some reasons, lets say freeing some resource, Android killed it

    // onRestoreInstanceState: which is executed before onResume is called.
    // * But this method call is suppose to come up if Activity was killed in the background.
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("ActivityOne","==onRestoreInstanceState==");

        // This is only * conditions applied, when we have the data in the Bundle
        if(savedInstanceState!=null){
            String name = savedInstanceState.getString("keyName");
            int age = savedInstanceState.getInt("keyAge",0);
        }
    }

    // onSaveInstanceState: which is executed before onStop is called. It will be executed 100 percent!!
    // We can save information written in our Activity's UI or any other details which we want
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i("ActivityOne","==onSaveInstanceState==");

        outState.putString("keyName","John");
        outState.putInt("keyAge",30);

    }

    // InstanceState is any data linked in your Activity eg: name, phone and email of User who is registering
}

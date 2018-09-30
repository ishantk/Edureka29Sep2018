package co.edureka.edureka29sep2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Date;

// AppCompatActivity is an API to create Activity i.e. UI
public class MainActivity extends AppCompatActivity {

    // Declare Reference to the View here !!
    WebView web; // web is a ref variable which will point to WebView Object

    // onCreate is an overrided method which serves as a constructor to us
    // is executed when object of activity is created by Android System
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // calling parent's method ? Parent will maintain state of Activity

        // Layout is set on Activity
        //setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_table);
        Log.i("MainActivity","==onCreate==");

        //System.out.print("MainActivity - onCreate");

        setContentView(R.layout.activity_linear);

        // findViewById will create the Object for us based on id we give as input
        web = findViewById(R.id.webView); // Inversion Of Control (IOC)
        // should be executed after setContentView

        WebViewClient client = new WebViewClient();
        web.setWebViewClient(client); // make WebView a Client
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("https://www.ndtv.com/"); // to load the URL we need Internet Permission

    }


    // onStart is executed when Object of Activity is in the Task
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity","==onStart==");
    }

    // onResume is a state when Activity will become Visible to User and User is interacting with it
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity","==onResume==");
    }

    // Use Case 1 ** Whenever a new Activity is launched -> onCreate -> onStart -> onResume **

    // Use Case 2 ** When a new Activity2 is launched from existing Activity1, and it covers Activity1 fully **
    //            ** Activity goes in background
    //            ** Activity1 -> onCreate -> onStart -> onResume | Activity2 -> onCreate -> onStart -> onResume
    //            ** Activity2 covered Activity1 fully !!
    //            ** Activity1 -> onPause -> onStop

    // Use Case 3 ** When a new Activity2 is launched from existing Activity1, and it covers Activity1 partially **
    //            ** Activity1 -> onCreate -> onStart -> onResume | Activity2 -> onCreate -> onStart -> onResume
    //            ** Activity2 covered Activity1 partially !!
    //            ** Activity1 -> onPause

    // Use Case 4 ** Whenever user presses back key to come out of Activity
    //            ** onCreate -> onStart -> onResume
    //            ** onPause -> onStop -> onDestroy


    // When Activity is fully covered by some other Activity
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity","==onStop==");
    }

    // When Activity is partially covered by some other Activity
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity","==onPause==");
    }

    // When user presses the back key or we execute finish() method in our code
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity","==onDestroy==");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity","==onRestart==");
    }

    // Event Handling - 1
    // method name can be any name of your choice !! eg: clickHandler here
    // We need to pass an input i.e. View API
    public void clickHandler(View view){

        Date date = new Date();
        String dateTime = date.toString();

        Toast.makeText(this,"Today is: "+dateTime,Toast.LENGTH_LONG).show();

    }
}

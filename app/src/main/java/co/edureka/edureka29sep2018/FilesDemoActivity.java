package co.edureka.edureka29sep2018;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class FilesDemoActivity extends AppCompatActivity
        implements View.OnClickListener,
        SharedPreferences.OnSharedPreferenceChangeListener{


    EditText eTxtData;
    Button btnSubmit;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    void initViews(){
        eTxtData  = findViewById(R.id.editTextData);
        btnSubmit = findViewById(R.id.buttonSubmit);

        btnSubmit.setOnClickListener(this);

        // will create data.xml file in Application Sandbox -> /data/data/co.edureka.edureka29sep2018/shared_prefs/data.xml
        preferences = getSharedPreferences("data",MODE_PRIVATE);
        editor = preferences.edit();

        // Register a Listener to handle Changes in SharePreferences
        preferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_demo);
        initViews();
    }

    void saveDataInInternalFile(){

        // In Internal Storage we CANNOT create folders/directories programatically using File API

        try{
            // FileOutputStream writes the data in the form of bytes
            FileOutputStream outputStream = openFileOutput("data.txt",MODE_PRIVATE);
            String data = eTxtData.getText().toString();
            outputStream.write(data.getBytes());
            outputStream.close();

            Toast.makeText(this,"Data Saved in Internal File..",Toast.LENGTH_LONG).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void readDataFromInternalFile(){

        try{
            // FileInputStream reads the data in the form of bytes
            FileInputStream inputStream = openFileInput("data.txt");
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line = bufferedReader.readLine();

            eTxtData.setText(line);

            bufferedReader.close();
            reader.close();
            inputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    void saveDataInExternalFile(){

        // In External Storage we can create folders/directories programatically using File API

        try{
            String fileName = "data.txt";
            // Environment.getExternalStorageDirectory().getPath() -> Path of SDCard
            String path = Environment.getExternalStorageDirectory().getPath()+"/"+fileName;

            Log.i("FileDemoActivity","PATH: "+path);

            //File file = new File(Environment.getExternalStorageDirectory().getPath()+"/MyFolder");
            //file.mkdir();

            // FileOutputStream writes the data in the form of bytes
            FileOutputStream outputStream = new FileOutputStream(path);
            String data = eTxtData.getText().toString();
            outputStream.write(data.getBytes());
            outputStream.close();

            Toast.makeText(this,"Data Saved in External File..",Toast.LENGTH_LONG).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    void readDataFromExternalFile(){

        try{

            String fileName = "data.txt";
            // Environment.getExternalStorageDirectory().getPath() -> Path of SDCard
            String path = Environment.getExternalStorageDirectory().getPath()+"/"+fileName;

            // FileInputStream reads the data in the form of bytes
            FileInputStream inputStream = new FileInputStream(path);
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line = bufferedReader.readLine();

            eTxtData.setText(line);

            bufferedReader.close();
            reader.close();
            inputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void saveDataInSharedPrefs(){
        String data = eTxtData.getText().toString();
        editor.putString("keyQuote",data);
        editor.putInt("keyRating",5);
        //editor.commit(); // save the data in same thread
        editor.apply(); // save the data in a background thread\
        Toast.makeText(this,"Data Saved in Shared Prefs..",Toast.LENGTH_LONG).show();
    }

    void readDataFromSharedPrefs(){
        String data = preferences.getString("keyQuote","NA");
        int rating = preferences.getInt("keyRating",0);

        eTxtData.setText(data+" "+rating);

    }

    @Override
    public void onClick(View v) {
        //saveDataInInternalFile();
        //readDataFromInternalFile();
        //saveDataInExternalFile();
        //readDataFromExternalFile();


        //saveDataInSharedPrefs();
        readDataFromSharedPrefs();
    }

    // If you update the same key with a different value, we will be able to handle that change !!
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("keyQuote")){
            //...
        }
    }
}

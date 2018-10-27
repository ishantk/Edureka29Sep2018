package co.edureka.edureka29sep2018.ui;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import co.edureka.edureka29sep2018.R;
import co.edureka.edureka29sep2018.model.User;
import co.edureka.edureka29sep2018.model.Util;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener{

    EditText eTxtName, eTxtPhone, eTxtEmail;
    Button btnSubmit;
    User user;

    ContentResolver resolver;

    ArrayList<User> users;

    void initViews(){
        eTxtName = findViewById(R.id.editTextName);
        eTxtPhone = findViewById(R.id.editTextPhone);
        eTxtEmail = findViewById(R.id.editTextEmail);

        btnSubmit = findViewById(R.id.buttonSubmit);
        btnSubmit.setOnClickListener(this);

        user = new User();

        resolver = getContentResolver();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        initViews();
    }

    void insertInDB(){

        // HashMap, with key as Column Name and Value as Data to be inserted at that Column
        ContentValues values = new ContentValues();
        values.put(Util.COL_NAME, user.name);
        values.put(Util.COL_PHONE, user.phone);
        values.put(Util.COL_EMAIL, user.email);

        Uri uri = resolver.insert(Util.URI_TAB_USER,values);
        Toast.makeText(this,user.name+" Added in Table at ID: "+uri.getLastPathSegment(),Toast.LENGTH_LONG).show();
        clearFields();
    }

    void retrieveFromDB(){

        String[] projection = {Util.COL_ID,Util.COL_NAME,Util.COL_PHONE,Util.COL_EMAIL};
        Cursor cursor = resolver.query(Util.URI_TAB_USER,projection,null,null,null);
        users = new ArrayList<>();
        // Iterate in cursor
        while (cursor.moveToNext()){

            User user = new User();

            user.id = cursor.getInt(cursor.getColumnIndex(Util.COL_ID));
            user.name = cursor.getString(cursor.getColumnIndex(Util.COL_NAME));
            user.email = cursor.getString(cursor.getColumnIndex(Util.COL_PHONE));
            user.phone = cursor.getString(cursor.getColumnIndex(Util.COL_EMAIL));

            Log.i("User",user.toString());

            users.add(user); // Display this ArrayList on ListView or RecyclerView as per your requirements
        }

        cursor.close();

    }

    void updateInDB(){

        ContentValues values = new ContentValues();
        values.put(Util.COL_NAME, user.name);
        values.put(Util.COL_PHONE, user.phone);
        values.put(Util.COL_EMAIL, user.email);

        // id is hard coded but can go dynamic
        int id = 1;
        String where = Util.COL_ID+" = "+id;

        int i = resolver.update(Util.URI_TAB_USER,values,where,null);

        Toast.makeText(this,user.name+" Updated in Table: "+i,Toast.LENGTH_LONG).show();
        clearFields();
    }

    void deleteFromDB(){

        int id = 1;
        String where = Util.COL_ID+" = "+id;

        int i = resolver.delete(Util.URI_TAB_USER,where,null);
        Toast.makeText(this,id+" Deleted from Table: "+i,Toast.LENGTH_LONG).show();
    }

    void clearFields(){
        eTxtName.setText("");
        eTxtPhone.setText("");
        eTxtEmail.setText("");
    }

    @Override
    public void onClick(View v) {

        /*
        //1. Get the data into Object
        user.name = eTxtName.getText().toString();
        user.phone = eTxtPhone.getText().toString();
        user.email = eTxtEmail.getText().toString();

        //2. Insert Data int Table in DB
        insertInDB();
        */

        //retrieveFromDB();

        /*user.name = eTxtName.getText().toString();
        user.phone = eTxtPhone.getText().toString();
        user.email = eTxtEmail.getText().toString();

        updateInDB();*/


        deleteFromDB();
    }
}

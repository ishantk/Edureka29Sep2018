package co.edureka.edureka29sep2018;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<Book> books;

    ProgressDialog progressDialog;

    StringBuffer response;

    RequestQueue requestQueue;
    StringRequest stringRequest;

    BookReceiver bookReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        getSupportActionBar().setTitle("All Books");

        requestQueue = Volley.newRequestQueue(this);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        books = new ArrayList<>();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");

        response = new StringBuffer();

        if(isInternetConnected()){
            //1.
            //BooksFetchTask task = new BooksFetchTask();
            //task.execute();

            //2.
            //fetchBooks();

            //3.

            bookReceiver = new BookReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("co.edureka.booksfetched");

            LocalBroadcastManager.getInstance(this).registerReceiver(bookReceiver,intentFilter);

            Intent intent = new Intent(AllBooksActivity.this,BooksFetchService.class);
            startService(intent); // Service will start in the background for us

        }else{
            Toast.makeText(this,"Please Connect to Internet and Try Again !!",Toast.LENGTH_LONG).show();
        }
    }

    boolean isInternetConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo !=null && networkInfo.isConnected());
    }

    class BookReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals("co.edureka.booksfetched")){
                String data = intent.getStringExtra("keyResponse");
                response.append(data);
                parseJSON();
            }
        }
    }

    //2. To fetch JSON data from a WebService - Volley/OkHttp/Retrofit (Explore OkHttp)
    // No need to create separate threads. Volley has internally implemented AsyncTask Model !!
    void fetchBooks(){

        progressDialog.show();

        String url = "http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2";

        stringRequest = new StringRequest(Request.Method.GET, url,
        new Response.Listener<String>() {
            @Override
            public void onResponse(String res) {
                Log.i("AllBooksActivity",res);
                response.append(res);
                parseJSON();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest); //To Send Request to the Server
    }


    //1. To fetch JSON data from a WebService - AsyncTask
    class BooksFetchTask extends AsyncTask{

        @Override
        protected void onPreExecute() { // runs before doInBackground in the same AllBooksActivity thread
            progressDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {  // runs as a different thread than  AllBooksActivity thread

            try{

                URL url = new URL("http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2");

                // Sending the Request to the Server
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

                // Reading the Response from the Server
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader buffer = new BufferedReader(reader);

                String line = "";
                while((line = buffer.readLine()) != null){
                    response.append(line);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {  // runs after doInBackground in the same AllBooksActivity thread
            Toast.makeText(AllBooksActivity.this,"Response: "+response.toString(),Toast.LENGTH_LONG).show();
            Log.i("AllBooksActivity",response.toString());
            parseJSON();
        }
    }

    void parseJSON(){

        try{

            // Convert String into JSON Object
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("bookstore");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jObj = jsonArray.getJSONObject(i);

                Book book = new Book();
                book.price = jObj.getString("price");
                book.name = jObj.getString("name");
                book.author = jObj.getString("author");

                books.add(book);
                adapter.add(book.toString());
            }

            listView.setAdapter(adapter);

            progressDialog.dismiss();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*class BooksFetchThread extends Thread{
        @Override
        public void run() {

        }
    }*/
}

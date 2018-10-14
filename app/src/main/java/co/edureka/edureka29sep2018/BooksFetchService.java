package co.edureka.edureka29sep2018;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class BooksFetchService extends IntentService {

    StringBuffer response = new StringBuffer();

    public BooksFetchService() {
        super("BooksFetchService");
    }

    // onHandleIntent is executing in the background the way doInBackground was executed
    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {

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

                Log.i("BooksFetchService",response.toString());

                Intent intent1 = new Intent("co.edureka.booksfetched");
                intent1.putExtra("keyResponse",response.toString());

                LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


}

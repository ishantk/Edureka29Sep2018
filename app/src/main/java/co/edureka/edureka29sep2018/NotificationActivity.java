package co.edureka.edureka29sep2018;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NotificationActivity extends AppCompatActivity {

    // Logging is the better way of understanding the life cycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        showNotification("NotificationActivity","==onCreate==");
    }

    void showNotification(String title, String text){

        // 1. Create NotificationManager
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        String channelId = "myChannelId";
        int notificationId = 101; // any number of your choice

        //2. Create NotificationChannel for Verions >=Oreo

        // NotificationChannel is an API which we need to link with NotificationManager only for Android Versions greater than equal to Oreo
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId,"Channel",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        //3. Create NotificationBuilder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,channelId);
        builder.setSmallIcon(R.drawable.appicon);
        builder.setContentTitle(title);
        builder.setContentText(text);

        // Event Handling for Notifications
        Intent intent = new Intent(NotificationActivity.this,ActivityOne.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,301,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        // We can create a Big Text Style Notification
        // This notification can support Action Buttons
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("This is Big Text"));
        builder.addAction(android.R.drawable.ic_menu_add,"Add",pendingIntent);
        builder.addAction(android.R.drawable.ic_menu_delete,"Delete",pendingIntent);

        //4. Create Notification from NotificationBuilder
        Notification notification = builder.build();

        //5. Show the Notification
        notificationManager.notify(notificationId,notification);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showNotification("NotificationActivity","==onStart==");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showNotification("NotificationActivity","==onResume==");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showNotification("NotificationActivity","==onPause==");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showNotification("NotificationActivity","==onStop==");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showNotification("NotificationActivity","==onDestroy==");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        showNotification("NotificationActivity","==onSaveInstanceState==");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        showNotification("NotificationActivity","==onRestoreInstanceState==");
    }

    public void clickHandler(View view){
        showNotification("This is Title","This is text");
    }
}

package co.edureka.edureka29sep2018;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SensorDemoActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager sensorManager;
    Sensor sensor;

    TextView txtData;

    void initViews(){
        txtData = findViewById(R.id.textViewData);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        //sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // Accelerometer Sensor
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_demo);
        initViews();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        /*float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float cal = ( (x*x)+(y*y)+(z*z) ) / (SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH);
        if(cal>3){
            txtData.setText("Device Shaken !!\nCoordinates "+x+" : "+y+" : "+z);
            sensorManager.unregisterListener(this);

            // Request LocationManager or GoogleApiClient to get Location coordinates
            // Use SMSManager API to send SMS

        }else{
            txtData.setText("Coordinates "+x+" : "+y+" : "+z);
        }*/

        float value = event.values[0];

        if(value == 90){
            txtData.setText("North");
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void clickHandler(View view){
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        sensorManager.unregisterListener(this);
    }
}

package co.edureka.edureka29sep2018;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class DateTimePickerActivity extends AppCompatActivity implements View.OnClickListener{


    // Reference to the Views
    TextView txtDateTime;
    Button btnPick;

    // DatePickerDialog
    DatePickerDialog datePickerDialog;
    DatePickerDialog.OnDateSetListener onDateSetListener;

    TimePickerDialog timePickerDialog;
    TimePickerDialog.OnTimeSetListener onTimeSetListener;

    void initViews(){
        txtDateTime = findViewById(R.id.textViewDateTime);
        btnPick = findViewById(R.id.buttonPick);
        btnPick.setOnClickListener(this);
    }

    void showDatePickerDialog(){

        // Event Handler for DatePickerDialog
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtDateTime.setText(dayOfMonth+"/"+(month+1)+"/"+year); // months -> 0 to 11
            }
        };

        // Get the System Date : To be displayed on DatePickerDialog
        Calendar calendar = Calendar.getInstance();
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        int mm = calendar.get(Calendar.MONTH);
        int yy = calendar.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(this,onDateSetListener,yy,mm,dd);
        datePickerDialog.show(); // Display the Dialog
    }

    void showTimePickerDialog(){

        // Event Handler for DatePickerDialog
        onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                txtDateTime.setText(hourOfDay+" : "+minute);
            }
        };

        // Get the System Time : To be displayed on DatePickerDialog
        Calendar calendar = Calendar.getInstance();
        int hh = calendar.get(Calendar.HOUR_OF_DAY);
        int mm = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(this,onTimeSetListener,hh,mm,true);
        timePickerDialog.show(); // Display the Dialog
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_picker);
        initViews();
    }

    @Override
    public void onClick(View v) {
        // Having id we can handle Button Clicks for various buttons
        int id = v.getId();

        if(id == R.id.buttonPick){
            //showDatePickerDialog();
            showTimePickerDialog();
        }
    }
}

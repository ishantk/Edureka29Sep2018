package co.edureka.edureka29sep2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

public class ViewsDemoActivity extends AppCompatActivity {

    // Declare Reference to Views Here
    Spinner spinner; // spinner is a ref variable which will point to the object of Spinner
    ArrayAdapter<String> adapter;

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> autoAdapter;


    // User Defined Method, cab be any name of your choice
    void initViews(){
        spinner = findViewById(R.id.spinnerCity);

        //android.R.layout.simple_spinner_dropdown_item -> PreDefined Layout file with 1 TextView
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item);
        adapter.add("--Select City--"); //0
        adapter.add("Bengaluru");       //1
        adapter.add("Pune");            //2
        adapter.add("Hyderabad");       //3
        adapter.add("Delhi");           //4
        adapter.add("Chandigarh");      //5

        spinner.setAdapter(adapter);

        // Set an Event Handler on Spinner
        // Using Anonymous Class Concept !!
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String city = adapter.getItem(position);
                Toast.makeText(ViewsDemoActivity.this,"You Selected: "+city,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    void initAutoComplete(){

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        autoAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item);

        autoAdapter.add("Shoes");           //0
        autoAdapter.add("Shirts");          //1
        autoAdapter.add("Handkerchiefs");   //2
        autoAdapter.add("BagPacks");        //3
        autoAdapter.add("HandBags");        //4
        autoAdapter.add("Jewellery");       //5

        autoCompleteTextView.setAdapter(autoAdapter);

        autoCompleteTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views_demo);
        initViews(); // executing after setContentView
        initAutoComplete();
    }
}

package co.edureka.edureka29sep2018;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MyLocationActivity extends AppCompatActivity { //implements LocationListener {

    TextView txtLocation;
    Button btnFetch;

    LocationManager locationManager;

    double latitude;
    double longitude;

    ProgressDialog progressDialog;

    StringBuffer fetchedAddress;

    // Accesses WebServices of Google !! eg: Location, YouTube, Ads etc..
    GoogleApiClient apiClient;
    GoogleApiClient.Builder builder; // Builder will build GoogleApiClient

    // Anonymous Class to ConnectionCallbacks Interface
    GoogleApiClient.ConnectionCallbacks connectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {

        // When we will be connected to Google's Location Service
        @Override
        public void onConnected(@Nullable Bundle bundle) {

            if (ActivityCompat.checkSelfPermission(MyLocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MyLocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MyLocationActivity.this,"Please Grant Permissions in Settings.",Toast.LENGTH_LONG).show();
            }else {

                //FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(MyLocationActivity.this);
                //Location location = client.getLastLocation().getResult();

                Location location = LocationServices.FusedLocationApi.getLastLocation(apiClient);
                txtLocation.setText(location.getLatitude()+" : "+location.getLongitude());
            }

        }

        @Override
        public void onConnectionSuspended(int i) {

        }
    };

    // Anonymous Class to ConnectionFailedListener Interface
    GoogleApiClient.OnConnectionFailedListener connectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Toast.makeText(MyLocationActivity.this,"Connection Not established. Try Again Later.",Toast.LENGTH_LONG).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);
        initViews();
        initGoogleClient();
    }

    void initGoogleClient(){

        builder = new GoogleApiClient.Builder(this);
        builder.addConnectionCallbacks(connectionCallbacks);
        builder.addOnConnectionFailedListener(connectionFailedListener);
        builder.addApi(LocationServices.API); // Accessing Location Services of Google

        apiClient = builder.build();
    }

    void initViews() {

        txtLocation = findViewById(R.id.textViewLocation);
        btnFetch = findViewById(R.id.buttonFetch);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Location...");

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initLocation();
                apiClient.connect(); // Requesting Google Service and creating a connection !!
            }
        });
    }

    // You wish to get Location Changes again and again !!
    void fetchLocationUpdates(){

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setFastestInterval(5000);
        locationRequest.setInterval(10000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"Please grant permissions in Settings",Toast.LENGTH_LONG).show();
        }else {
            LocationServices.FusedLocationApi.requestLocationUpdates(apiClient, locationRequest, new com.google.android.gms.location.LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    // Handle Location Changes
                }
            });
        }
    }

    /*void initLocation() {

        progressDialog.show();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"Please grant permissions in Settings",Toast.LENGTH_LONG).show();
        }else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 5, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) { // our updated or current location

        latitude = location.getLatitude();
        longitude = location.getLongitude();

        txtLocation.setText("Location is: "+latitude+" : "+longitude);

        // Would like to fetch location just once !!
        locationManager.removeUpdates(this);

        new FetchAddressTask().execute();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // No more location change required
        //locationManager.removeUpdates(this);

    }

    // Reverse Geocoding : Can be Time Consuming Task so putting it in AsyncTask
    class FetchAddressTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {

            // Write Code to fetch Address for Location
            // Reverse Geocoding : Fetch Textual Address from Latitude and Longitude

            try {
                Geocoder geocoder = new Geocoder(getApplicationContext());
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 2);

                if(addresses!=null && addresses.size()>0){
                    Address address = addresses.get(0);
                    fetchedAddress = new StringBuffer();

                    for(int i=0;i<=address.getMaxAddressLineIndex();i++){
                        fetchedAddress.append(address.getAddressLine(i)+"\n");
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {

            txtLocation.setText("Location is: "+latitude+" : "+longitude+"\nAddress:\n"+fetchedAddress.toString());

            progressDialog.dismiss();
        }
    }
}


// Google Maps Code:
/*
package co.edureka.mymapsapp;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/*
1. Create Google Map Activity
2. Go to https://console.developers.google.com/ (Provided in google_maps_api.xml)
3. Create a project in the developer console
4. Enable Library -> google maps sdk
5. Create an api key under credentials and restrict the same with your SHA-1 Key
6. Copy API Key by Google and put it in google_maps_api.xml
*/

// Use LocationManager or GoogleApiClient to fetch current location and show it on Google Maps ??

/*public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        // We are requesting google maps web service to load google maps images for us
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
/*
    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;

        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        LatLng delhi = new LatLng(28.5275198,77.0688987);
        LatLng bengaluru = new LatLng(12.9542946,77.4908522);

        mMap.addMarker(new MarkerOptions().position(delhi).title("Marker in Delhi").snippet("This is Snippet").alpha(0.7f));

        //BitmapDescriptor bm = BitmapDescriptorFactory.fromResource(R.drawable.todo);
        mMap.addMarker(new MarkerOptions().position(bengaluru).title("Marker in Benagluru").snippet("This is Snippet").icon(BitmapDescriptorFactory.fromResource(R.drawable.todo)));

        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                // -> marker.setIcon();
                //marker.setPosition(); -> Change LatLng

                return false;
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

            }
        });

        mMap.setTrafficEnabled(true);

        // mMap.clear(); // Remove all the markers -> And Re Add New Markers

        // Switching the maps to this location
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(delhi));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(delhi,13));
    }
}
*/


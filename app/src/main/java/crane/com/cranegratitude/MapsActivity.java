package crane.com.cranegratitude;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback /*, GoogleMap.OnMarkerClickListener*/ {

    private GoogleMap mMap;

    private int crane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = this.getIntent();
        crane = intent.getIntExtra("crane", 1);
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //mMap.moveCamera(CameraUpdateFactory.zoomTo(17.0f));

        if (crane == 1) {
            // Show the closest habitat
            LatLng tokyo = new LatLng(35.681547, 139.755117);
            mMap.addMarker(new MarkerOptions().position(tokyo).title("Closest habitat").icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tokyo, 15.0f));

            LatLng myLocation = new LatLng(35.681298, 139.766247);
            mMap.addMarker(new MarkerOptions().position(myLocation).title("My location"));
        } else {
            // let the user select the location where he saw cranes
            LatLng tokyo = new LatLng(35.681547, 139.755117);
            final MarkerOptions mo = new MarkerOptions().position(tokyo).draggable(true).title("Share location");
            mMap.addMarker(mo).showInfoWindow();
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tokyo, 17.0f));

            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                @Override
                public void onInfoWindowClick(Marker arg0) {
                    Intent intent = new Intent(getBaseContext(), SubmitData.class);
                    intent.putExtra("latitude", mo.getPosition().latitude);
                    intent.putExtra("longitude", mo.getPosition().longitude);

                    // Starting the  Activity
                    startActivity(intent);
                }
            });
        }
    }

    /** Called when the user clicks a marker. * /
    /*@Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        //Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        /*if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }* /

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        //return false;
        if (crane == 2) {
            Intent intent = new Intent(MapsActivity.this, SubmitData.class);
            intent.putExtra("latitude", marker.getPosition().latitude);
            intent.putExtra("longitude", marker.getPosition().longitude);
            startActivity(intent);
            return true;
        }
        return false;
    } */

}

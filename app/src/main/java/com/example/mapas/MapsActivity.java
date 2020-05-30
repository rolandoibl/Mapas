package com.example.mapas;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int REQUEST_CODE_LOCATION = 880;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Permisos para localizacion
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
            }, REQUEST_CODE_LOCATION);
        }

        //Permitir mi localización
        mMap.setMyLocationEnabled(true);
        //Activar botones de zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);

        //Marcador de Leonardita
        LatLng leonardita = new LatLng(19.325765816730893, -99.18266576076913);
        mMap.addMarker(new MarkerOptions().position(leonardita).title("Leonardita").snippet("Punto de reunión"));

        //Marcador parada de Ingenieria
        LatLng paradaIng = new LatLng(19.33072866342843,-99.18447636309251);
        mMap.addMarker(new MarkerOptions().position(paradaIng).title("Parada de ingeniería").snippet("Punto de reunión"));

        //Marcador de Metro CU
        LatLng metroCU = new LatLng(19.324404562590658,-99.17392790618068);
        mMap.addMarker(new MarkerOptions().position(metroCU).title("Metro CU").snippet("Destino").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        //Marcador de Metrobús CU
        LatLng metrobusCU = new LatLng(19.32410640186133,-99.18772296632872);
        mMap.addMarker(new MarkerOptions().position(metrobusCU).title("Metrobús CU").snippet("Destino").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        //Marcador de Metro Copilco
        LatLng metroCopilco = new LatLng(19.336061453071494,-99.17706653969756);
        mMap.addMarker(new MarkerOptions().position(metroCopilco).title("Metro Copilco").snippet("Destino").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        //Enofoque del mapa al inicio de la actividad
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(leonardita,14));
    }
}
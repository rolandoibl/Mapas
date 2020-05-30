package com.example.mapas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    //LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    private static final int REQUEST_CODE_LOCATION = 100;
    private FusedLocationProviderClient fusedLocationClient;
    private Button btnBuscar;
    private TextView edtCoordenadas;
    private LocationManager ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBuscar = findViewById(R.id.btnBuscar);
        edtCoordenadas = findViewById(R.id.edtCoordenadas);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        validaGPS();
        btnBuscar.setOnClickListener(onClickBuscar);
    }

    View.OnClickListener onClickBuscar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Buscar();
        }
    };

    private void validaGPS() {

        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ubicacion == null) {
            Toast.makeText(this, "Su equipo no cuenta con ninguún proveedor de Localización", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Se requiere que el GPS se encuentre encendido", Toast.LENGTH_SHORT).show();
            if (!ubicacion.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Intent intentGPS = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intentGPS);
            }
        }
    }


    private void Buscar() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
            }, REQUEST_CODE_LOCATION);

            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location!=null){
                        edtCoordenadas.setText(String.valueOf(location.getLatitude()));
                    }
                    if(location==null){
                        Toast.makeText(getApplicationContext(),"Un error poco común",Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),"Parece que hubo un error",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
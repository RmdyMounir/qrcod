package com.example.qrcodetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity<LoginActivity> extends AppCompatActivity {

    private static final int REQUEST_ID_READ_WRITE_PERMISSION = 0;
    Button camera;
    android.widget.ImageView ImageView;
    Button take_it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        camera = (Button) findViewById(R.id.take_it);
        camera.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
        private void askPermissionAndCaptureVideo () {

            // With Android Level >= 23, you have to ask the user
            // for permission to read/write data on the device.
            if (android.os.Build.VERSION.SDK_INT >= 30) {

                // Check if we have read/write permission
                int readPermission = ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE);
                int writePermission = ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (writePermission != PackageManager.PERMISSION_GRANTED ||
                        readPermission != PackageManager.PERMISSION_GRANTED) {
                    // If don't have permission so prompt the user.
                    this.requestPermissions(
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_ID_READ_WRITE_PERMISSION
                    );
                    return;
                }

            }
        }
    }
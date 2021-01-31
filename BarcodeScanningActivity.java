package com.example.qrcodetest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.Barcode;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.common.InputImage;


import java.io.File;
import java.io.IOException;
import java.util.List;


public class BarcodeScanningActivity extends AppCompatActivity {

    private static Object Context;
    private static Object Uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void scanBarcodes(InputImage image) {
        // [START set_detector_options]
        BarcodeScannerOptions options =
                new BarcodeScannerOptions.Builder()
                        .setBarcodeFormats(
                                Barcode.FORMAT_QR_CODE,
                                Barcode.FORMAT_AZTEC)
                        .build();
        BarcodeScanner scanner = BarcodeScanning.getClient();
        Task<List<Barcode>> result = scanner.process(image).addOnSuccessListener(new OnSuccessListener<List<Barcode>>() {
            @Override
            public void onSuccess(List<Barcode> barcodes) {
                for (Barcode barcode : barcodes) {
                    Rect bounds = barcode.getBoundingBox();
                    Point[] corners = barcode.getCornerPoints();

                    String rawValue = barcode.getRawValue();

                    int valueType = barcode.getValueType();

                    switch (valueType) {
                        case Barcode.TYPE_WIFI:
                            String ssid = barcode.getWifi().getSsid();
                            String password = barcode.getWifi().getPassword();
                            int type = barcode.getWifi().getEncryptionType();
                            break;
                        case Barcode.TYPE_URL:
                            String title = barcode.getUrl().getTitle();
                            String url = barcode.getUrl().getUrl();
                            break;
                    }
                }
                ;

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }

                });


    }
}
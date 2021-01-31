package com.example.qrcodetest;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.google.mlkit.vision.common.InputImage;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MLKitVisionImage {
    private ByteBuffer byteBuffer;
    private int rotationDegrees;

    private void imageFromBitmap(Bitmap bitmap){
        int rotationDegree = 0;
        InputImage image  = InputImage.fromBitmap(bitmap, rotationDegree);

    }

    InputImage image = InputImage.fromByteBuffer(byteBuffer,
            /* image width */ 480,
            /* image height */ 360,
            rotationDegrees,
            InputImage.IMAGE_FORMAT_NV21 // or IMAGE_FORMAT_YV12
    );
    private void imageFromPath(Context context, Uri uri){
        InputImage image;
        try {
            image = InputImage.fromFilePath(context,uri);
        }catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}

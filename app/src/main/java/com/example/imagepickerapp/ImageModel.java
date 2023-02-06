package com.example.imagepickerapp;

import android.net.Uri;

import java.net.URI;

public class ImageModel {
    String imagename;
    Uri imageUri;

    public ImageModel(String imagename, Uri imageUri) {
        this.imagename = imagename;
        this.imageUri = imageUri;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}

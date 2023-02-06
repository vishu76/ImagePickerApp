package com.example.imagepickerapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ExtendedFloatingActionButton addbtn;
    private ImageView imagecover;
    public Uri imageuri;
    String title;
    ArrayList<ImageModel> imageModelArrayList = new ArrayList<>();
    ImageAdapter adapter;
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        addbtn = findViewById(R.id.add_image);
        imagecover = findViewById(R.id.imagecover);
        getSupportActionBar().setTitle("Image Picker");
        recyclerView.setHasFixedSize(true);
        adapter = new ImageAdapter(imageModelArrayList, MainActivity.this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        try {
            Intent intent = getIntent();
            if (intent != null) {
                String finaltitle = intent.getStringExtra("finaltitle");
                Uri finalUri = Uri.parse(intent.getStringExtra("finalimageUri"));
               // imageModelArrayList.add(new ImageModel(finaltitle, finalUri));
                addbtn.setVisibility(View.VISIBLE);
              //  adapter.notifyDataSetChanged();
                imagecover.setImageURI(finalUri);

            }

        } catch (NullPointerException e) {

        }

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    Uri outputUri = result.getData().getData();
                  /*  imageModelArrayList.add(new ImageModel(title, outputUri));
                    adapter.notifyDataSetChanged();*/
                    imagecover.setImageURI(outputUri);


//                    Intent intent=new Intent(CaptureImage.this,MainActivity.class);
//                    intent.putExtra("finalimageUri", outputUri.toString());
//                    intent.putExtra("finaltitle",title);
//                    startActivity(intent);
//                    finish();

                    /*switch (result.getResultCode()) {
                        case 200:

                            break;
                    }*/
                }
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                requestpermission();
                ImagePicker.with(MainActivity.this)
//                        .crop()                    //Crop image(Optional), Check Customization for more option
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080,1080) //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageuri = data.getData();
        title = data.getDataString();
        try {
            if (!imageuri.equals("") || title.length() != 0) {
                /*Intent dsPhotoEditorIntent = new Intent(this, DsPhotoEditorActivity.class);
                dsPhotoEditorIntent.setData(imageuri);
                dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Vishu Editor");
*//*
                int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION, DsPhotoEditorActivity.TOOL_CROP,
                        DsPhotoEditorActivity.TOOL_FRAME,
                        DsPhotoEditorActivity.TOOL_ROUND,
                        DsPhotoEditorActivity.TOOL_EXPOSURE,
                        DsPhotoEditorActivity.TOOL_CONTRAST,
                        DsPhotoEditorActivity.TOOL_VIGNETTE,
                        DsPhotoEditorActivity.TOOL_SATURATION,
                        DsPhotoEditorActivity.TOOL_SHARPNESS,
                        DsPhotoEditorActivity.TOOL_WARMTH,
                        DsPhotoEditorActivity.TOOL_PIXELATE,
                        DsPhotoEditorActivity.TOOL_DRAW,
                        DsPhotoEditorActivity.TOOL_STICKER,
                        DsPhotoEditorActivity.TOOL_TEXT
                };
                dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);*//*
//        startActivityForResult(dsPhotoEditorIntent, 200);
                activityResultLauncher.launch(dsPhotoEditorIntent);*/
                Intent intent =new Intent(MainActivity.this,CaptureImage.class);
                intent.putExtra("imageUri",imageuri.toString());
                intent.putExtra("title",title);
                startActivity(intent);
                finish();

//                imageModelArrayList.add(new ImageModel(title, uri));
//                adapter.notifyDataSetChanged();
//                Intent intent = new Intent(MainActivity.this, CaptureImage.class);
//                intent.putExtra("imageUri", imageuri.toString());
//                intent.putExtra("title", title);
//                startActivity(intent);
//                finish();

            }

        } catch (NullPointerException e) {
//            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            Log.e("Mainpage", String.valueOf(e));
        }
       /* if (!uri.equals("") || title.length() != 0) {
            imageModelArrayList.add(new ImageModel(title, uri));
            adapter.notifyDataSetChanged();

        }*/

        /*
//        Toast.makeText(this, ""+uri, Toast.LENGTH_SHORT).show();
//        imageModelArrayList.add(new ImageModel("Image Name",uri));
//        imagecover.setImageURI(uri);

    }*/
    }

}
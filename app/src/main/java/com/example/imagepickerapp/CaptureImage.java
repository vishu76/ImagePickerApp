package com.example.imagepickerapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
import com.yalantis.ucrop.UCrop;
import java.io.File;
import java.net.URI;
import java.util.UUID;
public class CaptureImage extends AppCompatActivity {

   public Uri myUri;
   public String title;
//   ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_image);

        String desi_uri=new StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString();
        UCrop.Options options=new UCrop.Options();
        UCrop.of(myUri,Uri.fromFile(new File(getCacheDir(),desi_uri)))
                .withOptions(options)
                .withAspectRatio(0,0)
                .useSourceImageAspectRatio()
                .withMaxResultSize(2000,2000)
                .start(CaptureImage.this);

/*
        activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if ( result.getResultCode() == RESULT_OK) {
                    Uri outputUri = result.getData().getData();
                    Intent intent=new Intent(CaptureImage.this,MainActivity.class);
                    intent.putExtra("finalimageUri", outputUri.toString());
                    intent.putExtra("finaltitle",title);
                    startActivity(intent);
                    finish();
                    */
/*switch (result.getResultCode()) {
                        case 200:

                            break;
                    }*//*

                }

            }
        });
*/
        Intent intent=getIntent();
        myUri = Uri.parse(intent.getStringExtra("imageUri"));
        title = intent.getStringExtra("title");
//        Intent dsPhotoEditorIntent = new Intent(this,DsPhotoEditorActivity.class);
//        dsPhotoEditorIntent.setData(myUri);
//        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Vishu Editor");
        /*int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION, DsPhotoEditorActivity.TOOL_CROP,
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
        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);*/
//        startActivityForResult(dsPhotoEditorIntent, 200);
//        activityResultLauncher.launch(dsPhotoEditorIntent);
    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case 200:
//                    Uri outputUri = data.getData();
//                    Intent intent=new Intent(CaptureImage.this,MainActivity.class);
//                    intent.putExtra("finalimageUri", outputUri.toString());
//                    intent.putExtra("finaltitle",title);
//                    startActivity(intent);
//                    finish();
//                    break;
//            }
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RESULT_OK &&requestCode==UCrop.REQUEST_CROP) {
            final Uri resulturi = UCrop.getOutput(data);
            Intent intent=new Intent(CaptureImage.this,MainActivity.class);
            intent.putExtra("finalimageUri", resulturi.toString());
            intent.putExtra("finaltitle",title);
            startActivity(intent);
            finish();

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(CaptureImage.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
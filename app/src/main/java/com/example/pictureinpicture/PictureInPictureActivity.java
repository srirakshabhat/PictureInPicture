package com.example.pictureinpicture;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Rational;
import android.view.View;
import com.example.pictureinpicture.databinding.ActivityPictureInPictureBinding;

@RequiresApi(api = Build.VERSION_CODES.O)
public class PictureInPictureActivity extends AppCompatActivity {

   private ActivityPictureInPictureBinding binding;

   private String url;

   private  PictureInPictureParams.Builder pipBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_picture_in_picture);

        binding.setHandler(this);

        url = getIntent().getStringExtra("url");

        binding.videoView.setVideoPath((url));
        binding.videoView.requestFocus();
        binding.videoView.start();
    }

    public void enterPipMode(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /* If you want aspect ratios other than default */
            Rational aspectRatio = new Rational(binding.videoView.getWidth(), binding.videoView.getHeight());
            pipBuilder = new PictureInPictureParams.Builder();
            pipBuilder.setAspectRatio(aspectRatio).build();
            enterPictureInPictureMode(pipBuilder.build());
        }
    }

    @Override
    public void onUserLeaveHint() {
        if (!isInPictureInPictureMode()) {
            /*Default height and width for screen*/
            pipBuilder.build();
            enterPictureInPictureMode(pipBuilder.build());
        }
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
       binding.setInPictureMode(isInPictureInPictureMode);
    }

    @Override
    public void onNewIntent(Intent i) {
        super.onNewIntent(i);
        updatePipMode();
    }

    private void updatePipMode() {
        binding.videoView.setVideoPath((url));
        binding.videoView.requestFocus();
    }
}
package com.example.pictureinpicture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import com.example.pictureinpicture.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements VideoAdapter.onNameClick{

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        setAdapterAndPopulateList();
    }
    private void setAdapterAndPopulateList() {
        List<VideoModel> videoList = new ArrayList<>();

        videoList.add(new VideoModel("The Smoking Tire Video", "https://storage.googleapis.com/6003dd064c17c1b24e471f7b/95da7f5e-f721-4add-8f82-a699ccfddeaf.mp4"));
        videoList.add(new VideoModel("The Smoking Tire Video", "https://www.dropbox.com/s/0x2ke57h7wv49ll/Sample_512x288.mp4"));
        videoList.add(new VideoModel("The Curling window", "https://www.dropbox.com/s/0x2ke57h7wv49ll/Sample_512x288.mp4"));
        videoList.add(new VideoModel("The Joy", "https://www.dropbox.com/s/0x2ke57h7wv49ll/Sample_512x288.mp4"));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        VideoAdapter adapter = new VideoAdapter(videoList, this,MainActivity.this);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onUrlClick(String url) {
        startActivity(new Intent(this, PictureInPictureActivity.class).putExtra("url", url));
    }
}
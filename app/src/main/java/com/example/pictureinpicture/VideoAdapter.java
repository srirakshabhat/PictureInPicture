package com.example.pictureinpicture;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pictureinpicture.databinding.ItemVideoBinding;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final List<VideoModel> videoList;
    private final Context context;
    private final onNameClick onNameClick;

    public VideoAdapter(List<VideoModel> videoList, Context context, onNameClick  onNameClick) {
        this.videoList = videoList;
        this.context = context;
        this.onNameClick= onNameClick;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVideoBinding videoBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_video, parent, false);
        return new VideoViewHolder(videoBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoModel videoModel = videoList.get(position);
        holder.videoBinding.setModel(videoModel);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNameClick.onUrlClick(videoList.get(position).getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        ItemVideoBinding videoBinding;
        public VideoViewHolder(@NonNull ItemVideoBinding itemView) {
            super(itemView.getRoot());
            videoBinding = itemView;
        }
    }

    public interface onNameClick{
        void onUrlClick(String url);
    }
}

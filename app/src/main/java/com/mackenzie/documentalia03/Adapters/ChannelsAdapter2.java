package com.mackenzie.documentalia03.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mackenzie.documentalia03.Interfaces.ChannelClickListener;
import com.mackenzie.documentalia03.Models.ChannelList;
import com.mackenzie.documentalia03.R;

public class ChannelsAdapter2 extends ListAdapter<ChannelList, ChannelsAdapter2.ViewHolder> {

    private Context context;
    private OnItemClickListener listener;

    public ChannelsAdapter2(Context context, OnItemClickListener listener) {
        super(new DiffUtil.ItemCallback<ChannelList>() {
            @Override
            public boolean areItemsTheSame(@NonNull ChannelList oldItem, @NonNull ChannelList newItem) {
                return oldItem.getChannel().getName().equals(newItem.getChannel().getName());
            }

            @Override
            public boolean areContentsTheSame(@NonNull ChannelList oldItem, @NonNull ChannelList newItem) {
                return true;
            }
        });
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ChannelList channel = getItem(position);

        holder.titleView.setText(channel.getChannel().getName());
        holder.subtitleView.setText(String.format("%s - %s", channel.getCountryName(), channel.getCommunityName()));

        String imageUrl = channel.getChannel().getLogo();
        Glide.with(context).load(imageUrl).placeholder(R.mipmap.ic_launcher).fallback(R.mipmap.ic_launcher).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClickListener(channel);
                // channelClickListener.onClick(channel);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleView;
        TextView subtitleView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageViewPoster);
            this.titleView = itemView.findViewById(R.id.textViewTitle);
            // this.subtitleView = itemView.findViewById(R.id.channel_description);
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(ChannelList channelList);
    }

}

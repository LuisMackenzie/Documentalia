package com.mackenzie.documentalia03.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mackenzie.documentalia03.Activities.ChannelsView;
import com.mackenzie.documentalia03.Activities.MainActivity;
import com.mackenzie.documentalia03.Fragments.ChannelsFragment;
import com.mackenzie.documentalia03.Interfaces.ChannelClickListener;
import com.mackenzie.documentalia03.Models.Channels;
import com.mackenzie.documentalia03.Models.Urls;
import com.mackenzie.documentalia03.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChannelsAdapter extends RecyclerView.Adapter<ChannelsAdapter.ViewHolder> {

    private List<Channels> channelsList;
    private int layout;
    private List<Urls> urlsList;
    private ChannelClickListener channelClickListener;
    private Context context;

    public ChannelsAdapter(List<Channels> channelsList, List<Urls> urlsList, int layout, ChannelClickListener channelClickListener) {
        this.channelsList = channelsList;
        this.urlsList = urlsList;
        this.layout = layout;
        this.channelClickListener = channelClickListener;
    }

    public ChannelsAdapter(List<Channels> channelsList, int layout, ChannelClickListener channelClickListener) {
        this.channelsList = channelsList;
        this.layout = layout;
        this.channelClickListener = channelClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(channelsList.get(position), channelClickListener);
        holder.setClickListener(new ChannelClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (!isLongClick) {

                    // launchView(view, position);
                    abrirExoplayer(position);
                    // Toast.makeText(context, "#" + position + " - " + channelsList.get(position), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "#" + position + " - " +
                            channelsList.get(position) + " " +
                            "(Long Click)", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return channelsList.size();
    }

    public void abrirExoplayer(int selection) {
        Intent intent = new Intent(context.getApplicationContext(), ChannelsView.class);
        // Inicializamos el bundle
        Bundle bun = new Bundle();
        // agregamos un dato tipo String que recogemos de et1
        bun.putInt("selection", selection);
        // agregamos el bundle al intent
        intent.putExtra("bundle", bun);
        context.startActivity(intent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        // Elementos de UI para rellenar
        public TextView textViewName;
        public ImageView iv_Poster;
        private CardView cardView;
        // public List<Urls> url2;
        private ChannelClickListener listener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewTitle);
            iv_Poster = itemView.findViewById(R.id.imageViewPoster);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnClickListener(this);
            cardView.setOnLongClickListener(this);

        }

        public void bind(final Channels channels, final ChannelClickListener listener) {
            // Procesamos datos a renderizar
            textViewName.setText(channels.getName());
            Picasso.get().load(channels.getPoster()).fit().into(iv_Poster);
            // iv_Poster.setImageResource(movie.getPoster());
            // Glide.with(context).load(imageUrl).placeholder(R.mipmap.ic_launcher).fallback(R.mipmap.ic_launcher).into(holder.imageView);


            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // listener.onClick(channels, url, getAdapterPosition());
                    listener.onClick();
                }
            });*/

        }

        /*public interface ItemClickListener {
            void onClick(View view, int position, boolean isLongClick);
        }*/

        public void setClickListener (ChannelClickListener clickListener) {
            this.listener = clickListener;
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            listener.onClick(view, getAdapterPosition(), true);
            return true;
            // return false;
        }
    }

}

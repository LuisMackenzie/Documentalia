package com.mackenzie.documentalia03.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mackenzie.documentalia03.Interfaces.DocClickListener;
import com.mackenzie.documentalia03.Models.Documental;
import com.mackenzie.documentalia03.Models.Urls;
import com.mackenzie.documentalia03.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DocAdapter extends RecyclerView.Adapter<DocAdapter.ViewHolder> {

    private List<Documental> documentals;
    private int layout;
    private List<Urls> urlsList;
    private DocClickListener docClickListener;
    private Context context;

    public DocAdapter(List<Documental> documentals,  List<Urls> urlsList, int layout, DocClickListener docClickListener) {
        this.documentals = documentals;
        this.layout = layout;
        this.urlsList = urlsList;
        this.docClickListener = docClickListener;
    }

    public DocAdapter(List<Documental> documentals, int layout, DocClickListener itemClickListener) {
        this.documentals = documentals;
        this.layout = layout;
        this.docClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(documentals.get(position), docClickListener);
        holder.setClickListener(new DocClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(context, "#" + position + " - " +
                            documentals.get(position) + " " +
                            "(Long Click)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "#" + position + " - " +
                            documentals.get(position), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return documentals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        // Elementos de UI para rellenar
        public TextView textViewName;
        public ImageView iv_Poster;
        private CardView cardView;
        private DocClickListener listener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.textViewTitle);
            iv_Poster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnClickListener(this);
            cardView.setOnLongClickListener(this);

        }

        public void bind(final Documental documental, final DocClickListener listener) {
            // Procesamos datos a renderizar
            textViewName.setText(documental.getName());
            Picasso.get().load(documental.getPoster()).fit().into(iv_Poster);
            // iv_Poster.setImageResource(movie.getPoster());

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(documental, getAdapterPosition());
                }
            });*/

        }

        public void setClickListener (DocClickListener clickListener) {
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
        }
    }

}

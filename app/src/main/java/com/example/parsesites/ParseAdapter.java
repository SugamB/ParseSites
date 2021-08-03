package com.example.parsesites;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ParseAdapter extends RecyclerView.Adapter<ParseAdapter.ViewHolder> {
    private static final String TAG = "ParseAdapter";
    private ArrayList<ParseItem> parseItems;
    private Context context;
    private Integer limit;

    public ParseAdapter(ArrayList<ParseItem> parseItems, Context context, Integer limit){
        this.parseItems = parseItems;
        this.context = context;
        this.limit=limit;
    }

    @NonNull
    @Override
    public ParseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapter.ViewHolder holder, final int position) {
        ParseItem parseItem = parseItems.get(position);
        holder.textView.setText(parseItem.getTitle());
        holder.textView2.setText(parseItem.getImdb());
        holder.textView3.setText(parseItem.getGenre());
        Picasso.get().load(parseItem.getImgUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(parseItems.size() > limit){
            return limit;
        }
        else
        {
            return parseItems.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        TextView textView2;
        TextView textView3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.imageView);
            textView= itemView.findViewById(R.id.textView);
            textView2= itemView.findViewById(R.id.textView2);
            textView3= itemView.findViewById(R.id.textView3);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ParseItem parseItem = parseItems.get(position);

            Intent intent = new Intent(context, GalleryActivity.class);
            intent.putExtra("image",parseItem.getImgUrl());
            intent.putExtra("title",parseItem.getTitle());
            intent.putExtra("httplink",parseItem.getHttplink());
            intent.putExtra("imdb",parseItem.getImdb());
            context.startActivity(intent);

        }
    }
}

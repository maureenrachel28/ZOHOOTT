package com.example.movietime.ui.movieList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movietime.R;
import com.example.movietime.interfaces.MovieClickListener;
import com.example.movietime.model.MovieData;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private ArrayList<MovieData> moviesList;
    MovieClickListener clickListener;
    private Context context;
    MovieListAdapter(Context context) {
        moviesList = new ArrayList<>();
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MovieData movieData = moviesList.get(position);
        holder.genre.setText(movieData.getGenre());
        holder.name.setText(movieData.getName());
        holder.yearOfRelease.setText(movieData.getYear() + "");
        holder.lang.setText(movieData.getLanguage());
        holder.rating.setText(String.format("%s", movieData.getRating()));
        Glide.with(holder.context).load(movieData.getImageUrl()).placeholder(R.drawable.ic_action_name).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.movieClicked(movieData);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void setMoviesList(ArrayList<MovieData> moviesList) {
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, genre, yearOfRelease, lang, rating;
        ImageView imageView;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            name = itemView.findViewById(R.id.movieName);
            genre = itemView.findViewById(R.id.genre);
            yearOfRelease = itemView.findViewById(R.id.releaseYear);
            lang = itemView.findViewById(R.id.lang);
            rating = itemView.findViewById(R.id.rating);
            imageView = itemView.findViewById(R.id.image);

        }
    }
}

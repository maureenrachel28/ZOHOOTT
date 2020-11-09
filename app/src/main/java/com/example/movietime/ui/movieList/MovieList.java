package com.example.movietime.ui.movieList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.movietime.R;
import com.example.movietime.interfaces.MovieClickListener;
import com.example.movietime.model.MovieData;
import com.example.movietime.ui.movieList.filter.FilterFab;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class MovieList extends AppCompatActivity implements View.OnClickListener, MovieClickListener, RatingDialogListener {
    private RecyclerView recyclerView;
    private MovieListModel model;
    private ArrayList<String> sortOptions = new ArrayList<>();
    private FloatingActionButton filterFab;
    private Boolean isAdmin;
    private FilterFab filterFabFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        model = new MovieListModel();
        Intent intent = getIntent();
        initAdminMode(intent.getBooleanExtra("isAdmin",false));
        initRecyclerView();
        initFilter();
    }

    private void initAdminMode(Boolean isAdmin) {
        if (isAdmin) {
            findViewById(R.id.addNew).setOnClickListener(this);
        } else {
            findViewById(R.id.addNew).setVisibility(View.GONE);
        }
    }

    private void initFilter() {
        filterFab = findViewById(R.id.filterFab);
        filterFabFragment = new FilterFab();
        filterFabFragment.setParentFab(filterFab);
        filterFab.setOnClickListener(this);
        filterFabFragment.selectedGenres.observeForever(new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> selectedGenres) {
                model.filterGnere(selectedGenres);
            }
        });
        filterFabFragment.selectedLanguages.observeForever(new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> selectedLanguages) {
                model.filterLanguage(selectedLanguages);
            }
        });
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.movieList);
        final MovieListAdapter adapter = new MovieListAdapter(this);
        adapter.clickListener = this;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        model.getMovies().observeForever(new Observer<ArrayList<MovieData>>() {
            @Override
            public void onChanged(ArrayList<MovieData> movieData) {
                adapter.setMoviesList(movieData);
            }
        });
        model.setupData();
        initSort();
    }
    private void initSort() {
        sortOptions.add("Rating");
        sortOptions.add("Time Added");
        sortOptions.add("Year of release");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sortOptions);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sortSpinner = findViewById(R.id.sortSpinner);
        sortSpinner.setAdapter(arrayAdapter);
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                model.sortWith(sortOptions.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                model.sortWith(sortOptions.get(0));
            }
        });
        sortSpinner.setSelection(1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.filterFab:
                filterFabFragment.show(getSupportFragmentManager(),filterFabFragment.getTag());
                break;
            case R.id.addNew:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Add Movie");
                final View customLayout = getLayoutInflater().inflate(R.layout.add_new_movie, null);
                builder.setView(customLayout);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MovieData movieData = new MovieData();
                        EditText name = customLayout.findViewById(R.id.movieName);
                        EditText url = customLayout.findViewById(R.id.imageUrl);
                        EditText lang = customLayout.findViewById(R.id.Language);
                        EditText year = customLayout.findViewById(R.id.year);
                        EditText genre = customLayout.findViewById(R.id.genre);
                        movieData.setImageUrl(url.getText().toString());
                        movieData.setName(name.getText().toString());
                        movieData.setLanguage(lang.getText().toString());
                        movieData.setYear(Integer.parseInt(year.getText().toString()));
                        movieData.setGenre(genre.getText().toString());
                        Date currentTime = Calendar.getInstance().getTime();
                        movieData.setAddedTime(currentTime.getTime());
                        movieData.setId((long) Math.random());
                        model.addMovie(movieData);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    @Override
    public void movieClicked(MovieData movieData) {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                .setNegativeButtonText("Cancel")
                .setNeutralButtonText("Later")
                .setNoteDescriptions(Arrays.asList("Very Bad", "Not good", "Quite ok", "Very Good", "Excellent !!!"))
                .setDefaultRating(2)
                .setTitle("Rate the Movie")
                .setDescription("Please select some stars and give your feedback")
                .setCommentInputEnabled(false)
                .setDefaultComment(movieData.getId() + "")
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .create(this)
                .show();
    }

    @Override
    public void onNegativeButtonClicked() {
    }

    @Override
    public void onNeutralButtonClicked() {
    }

    @Override
    public void onPositiveButtonClicked(int i, String s) {
        model.updateMovie(s,i);
    }
}
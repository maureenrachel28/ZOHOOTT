package com.example.movietime.ui.movieList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movietime.assemblers.DatabaseAssembler;
import com.example.movietime.interfaces.Database;
import com.example.movietime.model.MovieData;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MovieListModel {
    private MutableLiveData<ArrayList<MovieData>> movies;
    Database database;
    private ArrayList<String> selectedLanguages = new ArrayList<>();;
    public LiveData<ArrayList<MovieData>> getMovies() {
        return movies;
    }
    private ArrayList<String> selectedGenres = new ArrayList<>();
    private String currentSort;

    MovieListModel() {
        movies = new MutableLiveData<>();
        database = DatabaseAssembler.getInstance();
    }

    void setupData() {
        movies.setValue(database.getAllMovies());
    }

    public void sortWith(String sort) {
        this.currentSort = sort;
        applyFilterAndSort();
    }

    public void filterGnere(ArrayList<String> selectedGenres) {
        this.selectedGenres = selectedGenres;
        applyFilterAndSort();
    }

    public void filterLanguage(ArrayList<String> selectedLanguages) {
        this.selectedLanguages = selectedLanguages;
        applyFilterAndSort();
    }
    private void applyFilterAndSort() {
        ArrayList<MovieData> sortedMovies = database.getAllMovies();
        switch (currentSort) {
            case "Rating":
                Collections.sort(sortedMovies, MovieData.sortByRating);
                break;
            case "Time Added":
                Collections.sort(sortedMovies, MovieData.sortTimeAdded);
                break;
            case "Year of release":
                Collections.sort(sortedMovies, MovieData.sortByYear);
                break;
        }
        if (!selectedGenres.isEmpty()) {
            ArrayList<MovieData> tempMovies = new ArrayList<>();
            for (MovieData movieData: sortedMovies) {
                if (selectedGenres.indexOf(movieData.getGenre()) != -1) {
                    tempMovies.add(movieData);
                }
            }
            sortedMovies = tempMovies;
        }
        if (!selectedLanguages.isEmpty()) {
            ArrayList<MovieData> tempMovies = new ArrayList<>();
            for (MovieData movieData: sortedMovies) {
                if ( selectedLanguages.indexOf(movieData.getLanguage())!= -1 ) {
                    tempMovies.add(movieData);
                }
            }
            sortedMovies = tempMovies;
        }
        movies.setValue(sortedMovies);
    }

    public void updateMovie(String id, int rating) {

        /* database.updateRating(id,rating)
*/
    }

    public void addMovie(MovieData movieData) {
        database.addMovie(movieData);
    }

}

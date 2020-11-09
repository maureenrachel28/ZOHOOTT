package com.example.movietime.interfaces;

import com.example.movietime.model.MovieData;
import com.example.movietime.model.User;

import java.util.ArrayList;

public interface Database {
    ArrayList<User> getAllUsers();
    User getUser(String id);
    void addUser(User user);
    ArrayList<MovieData> getAllMovies();
    void addMovie(MovieData movieData);
    void updateRating(String id, int currentRating);
    void initMovies();
}

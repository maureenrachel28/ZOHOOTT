package com.example.movietime.Database;

import com.example.movietime.interfaces.Database;
import com.example.movietime.model.MovieData;
import com.example.movietime.model.User;

import java.util.ArrayList;

public class SQLDatabase implements Database {
    @Override
    public ArrayList<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUser(String id) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public ArrayList<MovieData> getAllMovies() {
        return null;
    }

    @Override
    public void addMovie(MovieData movieData) {

    }

    @Override
    public void updateRating(String id, int currentRating) {
/* for movie in movies where movie.id == id {
               movie.totalRating += rating
               movie.ratingcount++;
               database.updateRating()
        }
*/
    }

    @Override
    public void initMovies() {

    }
}

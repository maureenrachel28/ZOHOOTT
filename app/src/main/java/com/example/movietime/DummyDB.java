package com.example.movietime;

import com.example.movietime.interfaces.Database;
import com.example.movietime.model.MovieData;
import com.example.movietime.model.User;

import java.util.ArrayList;

public class DummyDB implements Database {
    private ArrayList<User> users;
    private ArrayList<MovieData> movies;
    public DummyDB() {
        users = new ArrayList<>();
        movies = new ArrayList<>();
        for (int i = 0; i<10; i++) {
            User user = new User();
            user.setAdmin(true);
            user.setUsername(String.valueOf(i));
            user.setPassword(String.valueOf(i));
            users.add(user);
        }
        User user = new User();
        user.setAdmin(true);
        user.setUsername("admin");
        user.setPassword("admin");
        users.add(user);
        initMovies();
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUser(String id) {
        for(User user: users) {
            if (user.getUsername().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public ArrayList<MovieData> getAllMovies() {
        return movies;
    }

    @Override
    public void addMovie(MovieData movieData) {
        movies.add(movieData);
    }

    @Override
    public void updateRating(String id, int currentRating) {

    }

    @Override
    public void initMovies() {
        for (int i = 0; i<10; i++) {
            int year = (int) (Math.random() * (2020 - 2000 + 1) + 2000);
            MovieData movieData = new MovieData();
            movieData.setAddedTime((long) Math.random()*1000000);
            movieData.setGenre("Animation");
            movieData.setImageUrl("https://lumiere-a.akamaihd.net/v1/images/open-uri20150422-12561-13brs4i_abd7aa3a.jpeg?region=0%2C0%2C1000%2C1502");
            movieData.setLanguage("English");
            movieData.setRatingCount(10);
            movieData.setTotalRating((long) (Math.random()*100));
            movieData.setYear(year);
            movieData.setName("BOLT " + i);
            movieData.setId((long) (Math.random()*1000000));
            movies.add(movieData);


        }
    }
}

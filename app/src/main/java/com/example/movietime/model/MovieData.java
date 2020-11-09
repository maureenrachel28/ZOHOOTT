package com.example.movietime.model;

import java.util.Comparator;

public class MovieData {
    long id;
    String name, language, genre;
    String imageUrl;
    long addedTime, totalRating;
    int ratingCount, year;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public long getAddedTime() {
        return addedTime;
    }

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return (double) totalRating/ratingCount;
    }

    public int getYear() {
        return year;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public long getTotalRating() {
        return totalRating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public void setTotalRating(long totalRating) {
        this.totalRating = totalRating;
    }

    public void setAddedTime(long addedTime) {
        this.addedTime = addedTime;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static Comparator<MovieData> sortByRating = new Comparator<MovieData>() {
        @Override
        public int compare(MovieData movieData1, MovieData movieData2) {
            return (int) (movieData1.getRating() - movieData2.getRating()) * 1000;
        }
    };
    public static Comparator<MovieData> sortTimeAdded = new Comparator<MovieData>() {
        @Override
        public int compare(MovieData movieData1, MovieData movieData2) {
            return (int) (movieData1.getAddedTime() - movieData2.getAddedTime())*1000;
        }
    };
    public static Comparator<MovieData> sortByYear = new Comparator<MovieData>() {
        @Override
        public int compare(MovieData movieData1, MovieData movieData2) {
            return (int) (movieData1.getYear() - movieData2.getYear());
        }
    };
}

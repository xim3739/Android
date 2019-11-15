package com.example.example_11_3_spinner;

public class MySpinnerData {

    private String movieName;
    private int movieImageID;

    public MySpinnerData(String movieName, int movieImageID) {
        this.movieName = movieName;
        this.movieImageID = movieImageID;
    }

    public int getMovieImageID() {
        return movieImageID;
    }

    public void setMovieImageID(int movieImageID) {
        this.movieImageID = movieImageID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}

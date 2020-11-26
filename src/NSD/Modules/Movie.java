package NSD.Modules;

import java.util.HashMap;

public class Movie {

    private int movieID;
    private String movieName;

    public Movie() {

        movieID = 0;
        movieName = "";

    }

    public Movie(int id, String name, HashMap<Integer, Rating> ratings) {

        movieID = id;
        movieName = name;

    }

    public int getMovieID() {
        return movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}

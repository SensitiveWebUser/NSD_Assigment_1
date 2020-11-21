package NSD.Modules;

import java.util.HashMap;

public class Movie {

    private int movieID;
    private String movieName;
    private HashMap<Integer, Rating> ratings;

    public Movie(){

        movieID = 0;
        movieName = "";
        ratings = new HashMap<Integer, Rating>();

    }

    public Movie(int id, String name, HashMap<Integer, Rating> ratings){

        movieID = id;
        movieName = name;
        this.ratings = ratings;

    }

    public int getMovieID(){
        return movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setRatings(HashMap<Integer, Rating> newRatings){
        ratings = newRatings;
    }

    public boolean addToRatings(int keyid, Rating rating){

        try{
            ratings.put(keyid, rating);
        }catch (Exception e){
            return false;
        }

        return true;

    }



}

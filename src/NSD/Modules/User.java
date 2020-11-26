package NSD.Modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {

    private int userID;
    private int userAge;
    private String userZipCode;
    private String userOccupation;
    private char userGender;
    private final HashMap<Integer, Rating> ratings;

    public User() {
        userID = 0;
        userOccupation = "";
        ratings = new HashMap<Integer, Rating>();
    }

    public User(int id, String name) {
        userID = 0;
        userOccupation = "";
        ratings = new HashMap<Integer, Rating>();
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserZipCode() {
        return userZipCode;
    }

    public void setUserZipCode(String userZipCode) {
        this.userZipCode = userZipCode;
    }

    public String getUserOccupation() {
        return userOccupation;
    }

    public void setUserOccupation(String userOccupation) {
        this.userOccupation = userOccupation;
    }

    public char getUserGender() {
        return userGender;
    }

    public void setUserGender(char userGender) {
        this.userGender = userGender;
    }

    public boolean addToRatings(int keyid, Rating rating) {

        try {
            ratings.put(keyid, rating);
        } catch (Exception e) {
            return false;
        }

        return true;

    }

    public double getAvarageMovieReview() {

        double result = 0;
        try {

            for (Map.Entry<Integer, Rating> entry : ratings.entrySet()) {
                Rating rating = entry.getValue();
                result += rating.getRating();
            }

            result = result / ratings.size();

        } catch (Exception e) {
            return -1;
        }

        return result;
    }

    public int getMovieReview(int movieID) {
        if (ratings.get(movieID) != null) {
            int result = ratings.get(movieID).getRating();
            return result;
        }
        return -1;
    }

    public ArrayList<Integer> getAllReviewMovies() {

        ArrayList<Integer> result = new ArrayList<>();

        for (Map.Entry<Integer, Rating> entry : ratings.entrySet()) {
            result.add(entry.getKey());
        }

        result = result;

        return result;
    }

}

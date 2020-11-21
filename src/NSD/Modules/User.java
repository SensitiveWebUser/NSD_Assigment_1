package NSD.Modules;

import java.util.HashMap;
import java.util.Map;

public class User {

    private int userID;
    private String userName;
    private HashMap<Integer, Rating> ratings;

    public User(){
        userID = 0;
        userName = "";
        ratings = new HashMap<Integer, Rating>();
    }

    public User(int id, String name){
        userID = 0;
        userName = "";
        ratings = new HashMap<Integer, Rating>();
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean addToRatings(int keyid, Rating rating){

        try{
            ratings.put(keyid, rating);
        }catch (Exception e){
            return false;
        }

        return true;

    }

    public float getAvarageMovieReview(){

        float result = 0;
        try{

            for (Map.Entry<Integer, Rating> entry : ratings.entrySet()) {
                Rating rating = entry.getValue();
                result += rating.getRating();
            }

            result = result / ratings.size();

        }catch (Exception e){
            return -1;
        }

        return result;
    }

}

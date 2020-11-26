package NSD.Main;

import NSD.Modules.Movie;
import NSD.Modules.Rating;
import NSD.Modules.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DataImport {

    File file = null;
    FileInputStream in = null;
    Scanner scan = null;

    private HashMap<Integer, Movie> movies = new HashMap<>();
    private HashMap<Integer, User> users = new HashMap<>();


    public void importAll() {

        movies = importMovie();
        users = importUsers();
        importRatings();

    }

    public HashMap<Integer, Movie> getMovies() {
        return movies;
    }

    public HashMap<Integer, User> getUsers() {
        return users;
    }

    private HashMap<Integer, User> importUsers() {

        HashMap<Integer, User> temp = null;
        User user;

        try {
            ArrayList<String> inputStr = setFileInput("users.dat");

            temp = new HashMap<Integer, User>();

            for (int x = 0; x < inputStr.stream().count(); x++) {

                user = new User();
                if (x > 1) {
                    user.setUserID(Integer.parseInt(inputStr.get(x)));
                } else {
                    user.setUserID(1);
                }
                user.setUserAge(Integer.parseInt(inputStr.get(x + 1)));
                user.setUserGender(inputStr.get(x + 2).charAt(0));
                user.setUserOccupation(inputStr.get(x + 3));
                user.setUserZipCode(inputStr.get(x + 4));

                temp.put(user.getUserID(), user);
                x += 4;

            }


        } catch (Exception e) {
            return null;
        }

        return temp;
    }

    private HashMap<Integer, Movie> importMovie() {

        HashMap<Integer, Movie> temp = null;
        Movie movie;

        try {
            ArrayList<String> inputStr = setFileInput("movies.dat");

            temp = new HashMap<Integer, Movie>();

            for (int x = 0; x < inputStr.stream().count(); x++) {

                movie = new Movie();
                movie.setMovieID(Integer.parseInt(inputStr.get(x)));
                movie.setMovieName(inputStr.get(x + 1));
                temp.put(movie.getMovieID(), movie);
                x += 23;
            }

        } catch (Exception e) {
            return null;
        }

        return temp;
    }

    private void importRatings() {

        Rating rating;

        try {

            ArrayList<String> inputStr = setFileInput("ratings.dat");

            for (int x = 0; x < inputStr.stream().count(); x++) {

                rating = new Rating();
                rating.setMovieId(Integer.parseInt(inputStr.get(x + 1)));
                rating.setRating(Integer.parseInt(inputStr.get(x + 2)));
                rating.setTime(Integer.parseInt(inputStr.get(x + 3)));

                int userID = Integer.parseInt(inputStr.get(x));
                users.get(userID).addToRatings(rating.getMovieId(), rating);

                x += 3;

            }

        } catch (Exception e) {
            return;
        }

    }

    private ArrayList<String> setFileInput(String filename) throws BadFileException {

        try {

            file = null;
            in = null;
            scan = null;

            String result = "";

            file = new File(filename);
            in = new FileInputStream(file);
            scan = new Scanner(in);

            return parseInput();

        } catch (FileNotFoundException e) {
            throw new BadFileException(filename + " not Found", e);
        } catch (RuntimeException e) {
            System.out.println("Sorry for the incontinence, however and Error has happened :(.");
            return null;
        }

    }

    private ArrayList<String> parseInput() {

        ArrayList<String> result = new ArrayList<String>();

        while (scan.hasNext()) {
            String[] temp = scan.nextLine().split("::|\t|\\|");
            for (String s : temp) {
                result.add(s);
            }
        }
        scan.close();
        return result;
    }
}

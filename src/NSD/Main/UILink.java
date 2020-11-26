package NSD.Main;

import NSD.Modules.Movie;
import NSD.Modules.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class UILink {

    private HashMap<Integer, Movie> movies = new HashMap<>();
    private HashMap<Integer, User> users = new HashMap<>();

    public void printOut(String output) {
        System.out.println(output);
    }

    public int inputToInt() {

        Scanner _Scanner = new Scanner(System.in);
        String input = _Scanner.nextLine();
        int result;
        try {
            result = Integer.parseInt(input);
        } catch (Exception e) {
            printOut("Bad input");
            return -1;
        }
        return result;

    }

    public String inputToStr() {

        Scanner _Scanner = new Scanner(System.in);
        String input;
        try {
            input = _Scanner.nextLine();
        } catch (Exception e) {
            printOut("Bad input");
            return "";
        }
        return input;

    }

    public void mainMenu() {

        try {

            int userOption;
            String result = "";
            while (true) {

                printOut("\n===Welcome to the main menu===");

                printOut(" 1) Load data");
                printOut("\n 2) Get users average reviews");
                printOut(" 3) Get user average review");

                printOut("\n 4) Get movies average reviews");
                printOut(" 5) Get movie average review");

                printOut("\n 6) Compare to users reviews");
                printOut(" 7) Export last result to file");
                printOut(" 8) Exit");

                printOut("\n==============================");

                userOption = inputToInt();

                switch (userOption) {

                    case 1:
                        DataImport dataIn = new DataImport();
                        dataIn.importAll();
                        movies = dataIn.getMovies();
                        users = dataIn.getUsers();
                        result = "data loaded :).";
                        printOut(result);
                        break;
                    case 2:
                        result = getAllUserAverage();
                        break;
                    case 3:
                        result = getUserAverage(0, true);
                        break;
                    case 4:
                        result = getAllMovieAverage();
                        break;
                    case 5:
                        result = getMovieAverage(0, true);
                        break;

                    case 6:
                        result = getTwoUsersMach();
                        break;

                    case 7:
                        if (!result.equals("")) {
                            DataExport dataOut = new DataExport();
                            dataOut.exportResult(result);
                            printOut("Result outputted");
                        } else {
                            printOut("No result to output");
                        }
                        result = "";
                        break;

                    case 8:
                        printOut("Hope to see you soon :).");
                        System.exit(0);
                        break;

                    default:

                }

            }
        } catch (RuntimeException e) {
            printOut("\nError! Please try again");
            mainMenu();
        }

    }

    private String getAllUserAverage() {
        String output = "";

        if (users.size() <= 0)
            return "Please load data";

        for (int x = 1; x <= users.size(); x++) {
            output += getUserAverage(x, false);
        }
        return output;
    }

    private String getUserAverage(int id, boolean isSingle) {

        try {
            if (isSingle) {
                if (users.size() <= 0) {
                    printOut("Data needs to be loaded");
                    return "Data needs to be loaded";
                }

                printOut("\n You can choose from 1 - " + users.size());
                int input = inputToInt();
                if (input <= 0) {
                    printOut("Bad Input");
                    return "Bad Input";
                } else {
                    id = input;
                }
            }
            User user = users.get(id);
            double rating = user.getAvarageMovieReview();
            rating = Math.round(rating * 100.0) / 100.0;
            String output;
            output = "\nUser with ID: " + user.getUserID() + " Has average rating of: " + rating;
            printOut(output);
            return output;
        } catch (Exception e) {
            printOut("User couldn't be found.");
            return "User couldn't be found.";
        }
    }

    private String getAllMovieAverage() {
        String output = "";

        if (movies.size() <= 0)
            return "Please load data";

        for (int x = 1; x <= movies.size(); x++) {
            output += getMovieAverage(x, false);
        }
        return output;
    }

    private String getMovieAverage(int id, boolean isSingle) {

        try {
            if (isSingle) {
                if (movies.size() <= 0) {
                    printOut("Data needs to be loaded");
                    return "Data needs to be loaded";
                }

                printOut("\n You can choose from 1 - " + movies.size());
                int input = inputToInt();
                if (input <= 0) {
                    printOut("Bad Input");
                    return "Bad Input";
                } else {
                    id = input;
                }
            }
            Movie movie = movies.get(id);

            AtomicInteger result = new AtomicInteger();
            AtomicInteger reviews = new AtomicInteger();
            result.set(0);
            reviews.set(0);

            if (!(users.size() <= 0)) {

                users.forEach((key, value) -> {

                    int rate = value.getMovieReview(movie.getMovieID());

                    if (!(rate <= -1)) {
                        reviews.addAndGet(1);
                        result.addAndGet(rate);
                    }

                });
            } else {
                return "Please load data";
            }

            double rating = result.get() / reviews.get();
            rating = Math.round(rating * 100.0) / 100.0;
            String output;
            output = "\nMovie with ID: " + movie.getMovieID() + " Movie Name: " + movie.getMovieName() + " Has average rating of: " + rating;
            printOut(output);
            return output;
        } catch (Exception e) {
            printOut("User couldn't be found.");
            return "User couldn't be found.";
        }

    }

    private String getTwoUsersMach() {

        if (users.size() <= 0) {
            printOut("Bad input");
            return "Data needs to be loaded";
        }

        String output = "";

        User userOne;
        User userTwo;

        printOut("\n Who would you like user one to be? You can choose from 1 - " + users.size());
        int result = inputToInt();
        if (!(result <= 0 || result > users.size())) {
            userOne = users.get(result);
        } else {
            printOut("Bad input");
            return "Bad input";
        }

        printOut("\n Who would you like user two to be? You can choose from 1 - " + users.size());
        result = inputToInt();
        if (!(result <= 0 || result > users.size()) && result != userOne.getUserID()) {
            userTwo = users.get(result);
        } else {
            printOut("Bad input");
            return "Bad input";
        }

        ArrayList<Integer> userOneReviews = userOne.getAllReviewMovies();

        ArrayList<Integer> bothHave = new ArrayList<>();

        for (int r : userOneReviews) {
            if (!(userTwo.getMovieReview(r) <= -1)) {
                bothHave.add(r);
            }
        }

        if (bothHave.size() <= 0) {
            output = "";
            for (int id : bothHave) {
                output += "\nUser 1 gave " + movies.get(id).getMovieName() + " a : " + userOne.getMovieReview(id) + " where user 2 gave: " + userTwo.getMovieReview(id);
            }
        } else {
            output = "No common reviewed movies";
        }


        printOut(output);

        return output;
    }
}

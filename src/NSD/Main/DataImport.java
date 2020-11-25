package NSD.Main;

import NSD.Modules.*;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class DataImport {

    File file = null;
    FileInputStream in = null;
    Scanner scan = null;

    private HashMap<Integer, Movie> movies = new HashMap<Integer, Movie>();
    private HashMap<Integer, User> users = new HashMap<Integer, User>();


    public void importAll(){

        importMovie();
        users = importUsers();
        importRatings();

    }

    private HashMap<Integer, User> importUsers(){

        //TODO: Fix custom exception :)
        HashMap<Integer, User> temp = null;

        User user;

        try{
            String[] inputStr = setFileInput("users.dat");

            for(String s : inputStr){
                System.out.println("This a input clean :" + s);
            }

            temp = new HashMap<Integer, User>();

            for(int x = 0; x < Arrays.stream(inputStr).count(); x++){

                user = new User();
                if(x > 1){
                    user.setUserID(Integer.parseInt(inputStr[(x)]));
                }else {
                    user.setUserID(1);
                }
                user.setUserAge(Integer.parseInt(inputStr[(x + 1)]));
                user.setUserGender( inputStr[(x + 2)].charAt(0));
                user.setUserOccupation(inputStr[(x + 3)]);
                user.setUserZipCode(inputStr[(x + 4)]);

                temp.put(x,user);
                x += 4;
            }

            temp.forEach((key, value) -> {
              System.out.println("ID: " + value.getUserID() + " Age: " + value.getUserAge() + " Gen: " + value.getUserGender() + " Oc: "+ value.getUserOccupation() + " Zip: "+ value.getUserZipCode());
            });

        } catch (Exception e){
            return null;
        }

        return temp;
    }

    private HashMap<Integer, Movie> importMovie(){
        //TODO: add code
        return null;
    }

    private void importRatings(){

        Rating rating;

        try{

            String[] inputStr  = setFileInput("ratings.dat");

            for(int x = 0; x < Arrays.stream(inputStr).count(); x++){

                rating = new Rating();
                rating.setMovieId(Integer.parseInt(inputStr[x + 1]));
                rating.setRating(Integer.parseInt(inputStr[x + 2]));
                rating.setTime(Integer.parseInt(inputStr[x + 3]));
                users.get(Integer.parseInt(inputStr[x])).addToRatings(rating.getMovieId(), rating);

                x += 3;
            }

        } catch (Exception e){
        }

    }

    private String[] setFileInput(String filename) throws BadFileException {

        try{

            file = null;
            in = null;
            scan = null;

            String result = "";

            file = new File(filename);
            in = new FileInputStream(file);
            scan = new Scanner(in);

            return parseInput();

        } catch (FileNotFoundException e) {
            throw new BadFileException( filename + " not Found", e);
        }catch (RuntimeException e){
            System.out.println("Sorry for the incontinence, however and Error has happened :(.");
            return null;
        }

    }

    private String[] parseInput() {
        return scan.nextLine().split("::|\t|\\|");
    }
}

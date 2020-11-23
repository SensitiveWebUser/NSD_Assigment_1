package NSD.Main;

import NSD.Modules.*;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class DataImport {

    File file = null;
    FileInputStream in = null;
    BufferedInputStream bufIn = null;

    public void importAll(){

        HashMap<Integer, Rating> ratings = importRatings();
        importUsers(ratings);

    }

    private HashMap<Integer, User> importUsers(HashMap<Integer, Rating> ratings){

        //TODO: Fix custom exception :)
        HashMap<Integer, User> temp = null;

        User user;

        try{

            setFileInput("users.dat");

            String inputRaw = "";
            for(int i : bufIn.readAllBytes()){

                if(i == 10){
                    i = 124;
                }

                if(i != 13) {
                    inputRaw += ((char)i);
                }

            }

            String[] inputStr = parseInput(inputRaw);

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

    private HashMap<Integer, Rating> importRatings(){

        HashMap<Integer, Rating> temp = null;

        Rating rating;

        try{

            setFileInput("ratings.dat");

            String inputRaw = "";
            for(int i : bufIn.readAllBytes()){
                    inputRaw += ((char)i);
            }

            String[] inputStr = parseInput(inputRaw);

            for(String s : inputStr){
                System.out.println("This a input clean :" + s);
            }

            temp = new HashMap<Integer, Rating>();

            for(int x = 0; x < Arrays.stream(inputStr).count(); x++){

                rating = new Rating();



                temp.put(x,rating);
                x += 4;
            }

            temp.forEach((key, value) -> {
                //System.out.println("ID: " + value.getUserID() + " Age: " + value.getUserAge() + " Gen: " + value.getUserGender() + " Oc: "+ value.getUserOccupation() + " Zip: "+ value.getUserZipCode());
            });

        } catch (Exception e){
            return null;
        }

        return temp;
    }

    private string setFileInput(String filename) throws BadFileException {

        try{

            file = null;
            in = null;
            bufIn = null;

            file = new File(filename);
            in = new FileInputStream(file);
            bufIn = new BufferedInputStream(in);
        } catch (FileNotFoundException e) {
            throw new BadFileException( filename + " not Found", e);
        }catch (RuntimeException e){
            System.out.println("Sorry for the incontinence, however and Error has happened :(.");
        }

    }

    private String[] parseInput(String input) {
        return input.split("::|\t|\\|");
    }
}

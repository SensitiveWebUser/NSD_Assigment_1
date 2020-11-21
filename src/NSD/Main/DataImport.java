package NSD.Main;

import NSD.Modules.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class DataImport {

    private FileInputStream in = null;

    public void importAll(){
        importUsers();
    }

    private HashMap<Integer, User> importUsers(){

        //TODO: Fix custom exception :)

        HashMap<Integer, User> temp = null;
        try{
            in = new FileInputStream(new File("users.data"));
            ObjectInputStream ois = new ObjectInputStream(in);
            Object object = ois.readObject();
            String[] inputStr = in.toString().split("\\|");
            temp = new HashMap<Integer, User>();


        }catch (BadFileException e){
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return temp;
        }
        return temp;
    }

    private Movie[] importMovie(){
        //TODO: add code
        return null;
    }

    private Rating[] importRatings(){
        //TODO: add code
        return null;
    }

}

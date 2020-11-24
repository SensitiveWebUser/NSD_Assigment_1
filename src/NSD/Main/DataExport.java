package NSD.Main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class DataExport {

    public void exportResult(String exportText){

        try{
            String time = LocalDateTime.now().toString();
            FileOutputStream file = new FileOutputStream(time + ".txt");
            file.write(exportText.getBytes());

        }catch (IOException e){
            System.out.println("Error! Unable to export.");
        }

    }

}

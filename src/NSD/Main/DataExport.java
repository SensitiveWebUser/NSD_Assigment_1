package NSD.Main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class DataExport {

    public void exportResult(String exportText) {

        try {
            String time = LocalDate.now().toString() + " " + LocalTime.now().getHour() + LocalTime.now().getMinute() + LocalTime.now().getSecond();
            FileOutputStream file = new FileOutputStream(time + ".txt");
            file.write(exportText.getBytes());
            file.close();

        } catch (IOException e) {
            System.out.println("Error! Unable to export.");
        }

    }

}

package NSD.Main;

import java.io.FileNotFoundException;

public class BadFileException extends FileNotFoundException {
	
	public BadFileException(String input, FileNotFoundException err) {
		super("Error Has happened :(. Error Message: " + err);
	}


}

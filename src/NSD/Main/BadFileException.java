package NSD.Main;

import java.io.FileNotFoundException;

public class BadFileException extends FileNotFoundException {
	
	public BadFileException(String message, Throwable err){
		System.out.println(message + " Error message: " + err);
	}


}

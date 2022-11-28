package tn.esprit.spring.exceptions;

public class ApiException extends Exception{

    private final String message;

    public ApiException(String message){
        super(message);
        this.message = message;
    }

	public String getMessage() {
		return message;
	}
    
}
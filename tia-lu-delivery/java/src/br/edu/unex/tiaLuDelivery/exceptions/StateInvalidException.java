package br.edu.unex.tiaLuDelivery.exceptions;


public class StateInvalidException extends RuntimeException {
    public StateInvalidException(String message) {
        super(message);
    }

    public StateInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

package org.patrick;

public class CustomException extends Exception {
    
    private static final long serialVersionUID = 6177289857068719098L;

    /**
     * Constructor for an CustomException.
     *
     * @param message The error message the user is supposed to receive
     */
    public CustomException(String message) {
        super(message);
    }
}
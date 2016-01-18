package com.github.noobymatze.bikerental.business.administration.boundary;

/**
 *
 * @author matthias
 */
public class OnlyOneAddressLeftException extends Exception {

    public OnlyOneAddressLeftException(String message) {
        super(message);
    }
    
}

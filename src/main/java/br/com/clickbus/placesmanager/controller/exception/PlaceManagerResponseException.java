package br.com.clickbus.placesmanager.controller.exception;

public class PlaceManagerResponseException extends RuntimeException {
    public PlaceManagerResponseException(String message) {
        super(message);
    }
}

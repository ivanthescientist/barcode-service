package com.isc.barcode.exception;

public class BarcodeNotFoundException extends RuntimeException {
    public BarcodeNotFoundException(String message) {
        super(message);
    }
}

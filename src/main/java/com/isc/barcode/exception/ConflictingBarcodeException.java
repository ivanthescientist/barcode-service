package com.isc.barcode.exception;

public class ConflictingBarcodeException extends RuntimeException {
    public ConflictingBarcodeException(String message) {
        super(message);
    }
}

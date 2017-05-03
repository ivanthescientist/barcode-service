package com.isc.barcode.service;

import com.isc.barcode.exception.InvalidBarcodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarcodeService {
    private final BarcodeCache cache;
    private final BarcodeStorage storage;
    private final BarcodeValidator validator;

    @Autowired
    public BarcodeService(BarcodeCache cache, BarcodeStorage storage, BarcodeValidator validator) {
        this.cache = cache;
        this.storage = storage;
        this.validator = validator;
    }

    public boolean add(String barcode) {
        if(!validator.isValid(barcode)) {
            throw new InvalidBarcodeException(barcode);
        }

        if(isPresent(barcode)) {
            return false;
        }

        return cache.add(barcode) && storage.add(barcode);
    }

    public boolean isPresent(String barcode) {
        if(!validator.isValid(barcode)) {
            throw new InvalidBarcodeException(barcode);
        }

        return cache.isPresent(barcode) && storage.isPresent(barcode);
    }
}

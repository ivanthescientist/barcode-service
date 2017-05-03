package com.isc.barcode.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class BarcodeValidator {
    public boolean isValid(String barcode) {
        return barcode.length() >= 13 && barcode.length() <= 25 && StringUtils.isAlphanumeric(barcode);
    }
}

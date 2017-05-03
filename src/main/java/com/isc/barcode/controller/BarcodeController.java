package com.isc.barcode.controller;


import com.isc.barcode.exception.BarcodeNotFoundException;
import com.isc.barcode.exception.ConflictingBarcodeException;
import com.isc.barcode.exception.InvalidBarcodeException;
import com.isc.barcode.service.BarcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BarcodeController {
    private final BarcodeService barcodeService;

    @Autowired
    public BarcodeController(BarcodeService barcodeService) {
        this.barcodeService = barcodeService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/barcode/{barcode}")
    public void addBarcode(@PathVariable String barcode) {
        if(!barcodeService.add(barcode)) {
            throw new ConflictingBarcodeException(barcode);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/barcode/{barcode}")
    public void getBarcode(@PathVariable String barcode) {
        if(!barcodeService.isPresent(barcode)) {
            throw new BarcodeNotFoundException(barcode);
        }
    }

    @ExceptionHandler(ConflictingBarcodeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void conflictingBarcodeExceptionHandler() {}

    @ExceptionHandler(BarcodeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void barcodeNotFoundExceptionHandler() {}

    @ExceptionHandler(InvalidBarcodeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void invalidBarcodeExceptionHandler() {}
}

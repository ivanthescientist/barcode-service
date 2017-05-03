package com.isc.barcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(BarcodeApplicationConfiguration.class)
public class BarcodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BarcodeApplication.class, args);
    }
}

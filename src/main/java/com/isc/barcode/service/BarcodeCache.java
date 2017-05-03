package com.isc.barcode.service;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class BarcodeCache {
    private BloomFilter<CharSequence> cache;
    private String cacheSavePath;
    private File cacheFile;

    public BarcodeCache(@Value("${cache.save.path}") String cacheSavePath) {
        this.cacheSavePath = cacheSavePath;
    }

    @PostConstruct
    public void init() {
        cacheFile = new File(cacheSavePath);

        try {
            cache = BloomFilter.readFrom(new FileInputStream(new File(cacheSavePath)), Funnels.stringFunnel(Charsets.UTF_8));
        } catch (IOException exception) {
            cache = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), Integer.MAX_VALUE / 4);
        }
    }

    @PreDestroy
    public void terminate() throws Exception {
        cache.writeTo(new FileOutputStream(cacheFile));
    }

    public synchronized boolean isPresent(String barcode) {
        return cache.mightContain(barcode);
    }
    public synchronized boolean add(String barcode) {
        return cache.put(barcode);
    }
}


package com.ciphertech.backend.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.ciphertech.backend.model.Cipher;

import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class DataLoader {
    
    private List<Cipher> cipher_list;
        
    @PostConstruct
    public void loadData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        
        // Load Ciphers from ciphers.json file before app startup
        ClassPathResource cipherResource = new ClassPathResource("data/ciphers.json");
        this.cipher_list = objectMapper.readValue(
            cipherResource.getInputStream(), 
            new TypeReference<List<Cipher>>() {}
        );
        
    }
    
    public List<Cipher> getCiphers() {
        return cipher_list;
    }
}
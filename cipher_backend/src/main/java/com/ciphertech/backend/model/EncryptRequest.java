package com.ciphertech.backend.model;

public class EncryptRequest {
    private Long cipherId;
    private String plainText;
    private String key;
    
    // Constructors
    public EncryptRequest() {}
    
    public EncryptRequest(String plainText, String key, Long cipherId) {
        this.cipherId = cipherId;
        this.plainText = plainText;
        this.key = key;
    }
    
    // Getters
    public Long getCipherId () {
        return cipherId;
    }
    public String getPlainText() {
        return plainText;
    }
    
    public String getKey() {
        return key;
    }
}
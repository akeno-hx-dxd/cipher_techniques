package com.ciphertech.backend.model;

public class EncryptResult {
    private Long cipherId;
    private String plainText;
    private String key;
    private String cipherType;
    private String cipherText;
    
    // Constructors
    public EncryptResult() {}
    
    public EncryptResult(String plainText, String key, String cipherType, Long cipherId) {
        this.cipherId = cipherId;
        this.plainText = plainText;
        this.key = key;
        this.cipherType = cipherType;
    }
    public EncryptResult(String plainText, String key, String cipherType, Long cipherId, String cipherText) {
        this.cipherId = cipherId;
        this.plainText = plainText;
        this.key = key;
        this.cipherType = cipherType;
        this.cipherText = cipherText;
    }
    
    // Getters and setters
    public String getPlainText() {
        return plainText;
    }
    
    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public String getCipherType() {
        return cipherType;
    }
    
    public void setCipherType(String cipherType) {
        this.cipherType = cipherType;
    }

    public Long getCipherId() {
        return cipherId;
    }

    public void setCipherId(Long cipherId) {
        this.cipherId = cipherId;
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }
}
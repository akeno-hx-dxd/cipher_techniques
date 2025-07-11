package com.ciphertech.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//models import
import com.ciphertech.backend.model.Cipher;
import com.ciphertech.backend.model.EncryptRequest;
import com.ciphertech.backend.model.EncryptResult;
//services import
import com.ciphertech.backend.service.DataLoader;

//utils import
import com.ciphertech.backend.util.ApiResponse;

// import com.ciphertech.backend.util.CipherUtil.*;

@RestController
@RequestMapping("/api/v1/cipher")
public class CipherController {
    
    @Autowired
    private DataLoader cipher_list;

    @GetMapping("/techniques")
    public ApiResponse<List<Cipher>> getAllCiphers(){
        try {
            List<Cipher> ciphers = cipher_list.getCiphers();
            if(ciphers.isEmpty()){
                return ApiResponse.error("No Ciphers found");
            }
            return ApiResponse.success("Ciphers retrieved successfully", ciphers);
        } catch (Exception e) {
            return ApiResponse.error("Failed to retrieved ciphers: "+ e.getMessage());
        }
    }
    
    @GetMapping("/techniques/{id}")
    public ApiResponse<Cipher> getCipherById(@PathVariable Long id) {
        try {
            Cipher found_cipher = cipher_list.getCiphers().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    
            if(found_cipher == null){
                return ApiResponse.error("Cipher not found with id: "+ id);
            }
            return ApiResponse.success("Cipher retrieved successfully", found_cipher);

        } catch (Exception e) {
            return ApiResponse.error("Failed to retrieved cipher: "+ e.getMessage());
        }
    }

    @PostMapping("/encrypt")
    public ResponseEntity<ApiResponse<EncryptResult>> encryptText(@RequestBody EncryptRequest cipher_req){
        try {

            if(cipher_req.getCipherId() == null || cipher_req.getPlainText() == null || cipher_req.getKey() == null){
                return ResponseEntity.status(400).body(ApiResponse.error("Missing required body parameters"));
            }
            Cipher cipher = cipher_list.getCiphers().stream()
                            .filter(c -> c.getId().equals(cipher_req.getCipherId()))
                            .findFirst()
                            .orElse(null);
            
            if(cipher == null){
                return ResponseEntity.status(404).body(ApiResponse.error("Cipher not found with id: "+ cipher_req.getCipherId()));
            }

            EncryptResult result = new EncryptResult(
                cipher_req.getPlainText(),
                cipher_req.getKey(), 
                cipher.getName(),
                cipher_req.getCipherId());
    
            switch (cipher.getName()) {
                case "caesar cipher":
                    break;
    
                case "playfair cipher":
                    break;
    
                case "one-time-pad cipher":
                    break;
    
                case "vigenere cipher":
                    break;
    
                case "hill cipher":
                    break;
    
                default:
                    return ResponseEntity.status(404).body(ApiResponse.error("Cipher not found with id: "+cipher_req.getCipherId()));
            }
            return ResponseEntity.ok(ApiResponse.success("Demo Result",result));
        } catch(Exception e){
            return ResponseEntity.status(500).body(ApiResponse.error("Failed to retrieved cipher: "+ e.getMessage()));
        }
    }
}

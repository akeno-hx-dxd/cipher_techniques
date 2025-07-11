package com.ciphertech.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//models import
import com.ciphertech.backend.model.Cipher;

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

    // @PostMapping("/encrypt/{cipher_id}")
    // public ApiResponse<EncryptResult> encryptText(@PathVariable Long cipher_id){
    //     try {
    //         Cipher cipher = cipher_list.getCiphers().stream()
    //                         .filter(c -> c.getId().equals(cipher_id))
    //                         .findFirst()
    //                         .orElse(null);
            
    //         if(cipher == null){
    //             return ApiResponse.error("Cipher not found with id: "+ cipher_id);
    //         }
    
    //         switch (cipher.getName()) {
    //             case "caesar cipher":
    //                 break;
    
    //             case "playfair cipher":
    //                 break;
    
    //             case "one-time-pad cipher":
    //                 break;
    
    //             case "vigenere cipher":
    //                 break;
    
    //             case "hill cipher":
    //                 break;
    
    //             default:
    //                 return ApiResponse.error("Cipher not found with id: "+cipher_id);
    //         }
    //     } 
    // }
}

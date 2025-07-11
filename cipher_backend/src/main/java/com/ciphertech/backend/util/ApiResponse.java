package com.ciphertech.backend.util;

public class ApiResponse<T>{
    private boolean success;
    private String message;
    private T data;

    //constructors
    public ApiResponse(){}

    public ApiResponse(boolean success, String message){
        this.success = success;
        this.message = message;
        this.data = null;
    }

    public ApiResponse(boolean success, String message, T data){
        this.success = success;
        this.message = message;
        this.data = data;
    }
    
    // static factory methods

    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<>(true, "Success", data);
    }

    public static <T> ApiResponse<T> success(String message,T data){
    return new ApiResponse<>(true, message, data);
    }

    
    public static <T> ApiResponse<T> error(String message){
    return new ApiResponse<>(true, message, null);
    }

    public static <T> ApiResponse<T> error(String message,T data){
    return new ApiResponse<>(true, message, data);
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean getSuccsess(){
        return success;
    }
}
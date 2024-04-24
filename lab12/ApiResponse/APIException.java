package com.example.lab12.ApiResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class APIException extends RuntimeException {
    public APIException(String message){
        super(message);
    }
}

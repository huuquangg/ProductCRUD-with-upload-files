package com.cpyproject2spring.demo2.entities;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ResponseMassage {
    private HttpStatus status;
    private String message;
    private Object data;

    public ResponseMassage() {
    }

}
